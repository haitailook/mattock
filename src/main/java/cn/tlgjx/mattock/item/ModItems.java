package cn.tlgjx.mattock.item;

import cn.tlgjx.mattock.Mattock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModItems {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModItems.class);
    // 注册物品初始化
    public static final Item WOODEN_MATTOCK = registerModItem("wooden_mattock", new cn.tlgjx.mattock.item.custom.Mattock(ToolMaterials.WOOD, 6.0f, -3.2f, new Item.Settings().fireproof()));
    public static final Item STONE_MATTOCK = registerModItem("stone_mattock", new cn.tlgjx.mattock.item.custom.Mattock(ToolMaterials.STONE, 7.0f, -3.2f, new Item.Settings().fireproof()));
    public static final Item IRON_MATTOCK = registerModItem("iron_mattock", new cn.tlgjx.mattock.item.custom.Mattock(ToolMaterials.IRON, 6.0f, -3.1f, new Item.Settings().fireproof()));
    public static final Item GOLDEN_MATTOCK = registerModItem("golden_mattock", new cn.tlgjx.mattock.item.custom.Mattock(ToolMaterials.GOLD, 6.0f, -3.0f, new Item.Settings().fireproof()));
    public static final Item DIAMOND_MATTOCK = registerModItem("diamond_mattock", new cn.tlgjx.mattock.item.custom.Mattock(ToolMaterials.DIAMOND, 5.0f, -3.0f, new Item.Settings().fireproof()));
    public static final Item NETHERITE_MATTOCK = registerModItem("netherite_mattock", new cn.tlgjx.mattock.item.custom.Mattock(ToolMaterials.NETHERITE, 5.0f, -3.0f, new Item.Settings().fireproof()));

// 注册方法
    private static Item registerModItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Mattock.MOD_ID, name), item
        );
    }

    // 初始化入口方法
    public static void registerModItems() {
    }
}