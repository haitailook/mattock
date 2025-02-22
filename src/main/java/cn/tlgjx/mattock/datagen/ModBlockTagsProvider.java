package cn.tlgjx.mattock.datagen;

//import com.besson.tutorial.block.ModBlocks;
import cn.tlgjx.mattock.tag.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // 将原版镐和斧能挖掘的方块合并到我们创建的tag中
        getOrCreateTagBuilder(ModBlockTags.MATTOCK_MINEABLE)
                .forceAddTag(BlockTags.SHOVEL_MINEABLE)
                .forceAddTag(BlockTags.HOE_MINEABLE)
                .forceAddTag(BlockTags.AXE_MINEABLE);
    }
}