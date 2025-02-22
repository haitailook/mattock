package cn.tlgjx.mattock;

import cn.tlgjx.mattock.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mattock implements ModInitializer {
	public static final String MOD_ID = "mattock";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	//public Mattock(ToolMaterials toolMaterials, float v, float v1, Item.Settings fireproof) {}

	@Override
	public void onInitialize() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
			content.addAfter(Items.NETHERITE_AXE,ModItems.WOODEN_MATTOCK);
			content.addAfter(ModItems.WOODEN_MATTOCK,ModItems.STONE_MATTOCK);
			content.addAfter(ModItems.STONE_MATTOCK,ModItems.IRON_MATTOCK);
			content.addAfter(ModItems.IRON_MATTOCK,ModItems.GOLDEN_MATTOCK);
			content.addAfter(ModItems.GOLDEN_MATTOCK,ModItems.DIAMOND_MATTOCK);
			content.addAfter(ModItems.DIAMOND_MATTOCK,ModItems.NETHERITE_MATTOCK);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.addAfter(Items.WOODEN_HOE,ModItems.WOODEN_MATTOCK);
			content.addAfter(Items.STONE_HOE,ModItems.STONE_MATTOCK);
			content.addAfter(Items.IRON_HOE,ModItems.IRON_MATTOCK);
			content.addAfter(Items.GOLDEN_HOE,ModItems.GOLDEN_MATTOCK);
			content.addAfter(Items.DIAMOND_HOE,ModItems.DIAMOND_MATTOCK);
			content.addAfter(Items.NETHERITE_HOE,ModItems.NETHERITE_MATTOCK);
		});


		//LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
	}
}