package cn.tlgjx.mattock.item.custom;

import cn.tlgjx.mattock.tag.ModBlockTags;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class Mattock extends AxeItem {

    public Mattock(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return state.isIn(ModBlockTags.MATTOCK_MINEABLE) ? this.miningSpeed : 1.0F;
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        return state.isIn(ModBlockTags.MATTOCK_MINEABLE);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult originalResult = super.useOnBlock(context);
        if (originalResult.isAccepted()) return originalResult;

        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(pos);

        boolean isSneaking = player != null && player.isSneaking();
        ActionResult toolResult = isSneaking ?
                tryCreatePath(context, state, world, pos) :
                tryTillLand(context, state, world, pos);

        if (toolResult.isAccepted() && player != null) {
            context.getStack().damage(1, player, p -> p.sendToolBreakStatus(context.getHand()));
        }
        return toolResult;
    }

    // 耕地逻辑
    private ActionResult tryTillLand(ItemUsageContext context, BlockState state, World world, BlockPos pos) {
        if (context.getSide() == Direction.DOWN) return ActionResult.PASS;

        // 判断是否为泥土或草方块
        if (state.isOf(Blocks.DIRT) || state.isOf(Blocks.GRASS_BLOCK)) {
            BlockState tillState = Blocks.FARMLAND.getDefaultState();
            if (world.isAir(pos.up())) {
                world.playSound(
                        context.getPlayer(), pos,
                        SoundEvents.ITEM_HOE_TILL,
                        SoundCategory.BLOCKS, 1.0F, 1.0F
                );
                world.setBlockState(pos, tillState, Block.NOTIFY_ALL);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(context.getPlayer(), tillState));
                return ActionResult.success(world.isClient);
            }
        }
        return ActionResult.PASS;
    }

    // 土径逻辑
    private ActionResult tryCreatePath(ItemUsageContext context, BlockState state, World world, BlockPos pos) {
        if (state.isIn(BlockTags.DIRT)) {
            BlockState pathState = Blocks.DIRT_PATH.getDefaultState();
            if (world.isAir(pos.up())) {
                world.playSound(
                        context.getPlayer(), pos,
                        SoundEvents.ITEM_SHOVEL_FLATTEN,
                        SoundCategory.BLOCKS, 1.0F, 1.0F
                );
                world.setBlockState(pos, pathState, Block.NOTIFY_ALL);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(context.getPlayer(), pathState));
                return ActionResult.success(world.isClient);
            }
        }
        return ActionResult.PASS;
    }
}
