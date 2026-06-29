package net.azdo0m.betterbalance.itemgroup;

import net.azdo0m.betterbalance.block.ModBlock;
import net.azdo0m.betterbalance.item.ModArmor;
import net.azdo0m.betterbalance.item.ModItems;
import net.azdo0m.betterbalance.item.ModOceanite;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public class ModItemsGroups {
    public static void registerModItemsGroup(){

        // BLOCKS
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Items.NETHERITE_BLOCK, ModBlock.OCEANITE_BLOCK);
        });

        // INGREDIENTS
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.NETHERITE_INGOT, ModItems.OCEANITE_INGOT);
        });

        // COMBAT
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.addAfter(Items.NETHERITE_BOOTS, ModArmor.OCEANITE_HELMET);
            entries.addAfter(ModArmor.OCEANITE_HELMET, ModArmor.OCEANITE_CHESTPLATE);
            entries.addAfter(ModArmor.OCEANITE_CHESTPLATE, ModArmor.OCEANITE_LEGGINGS);
            entries.addAfter(ModArmor.OCEANITE_LEGGINGS, ModArmor.OCEANITE_BOOTS);

            entries.addAfter(Items.NETHERITE_SWORD, ModOceanite.OCEANITE_SWORD);
            entries.addAfter(Items.NETHERITE_SPEAR, ModOceanite.OCEANITE_SPEAR);
            entries.addAfter(Items.NETHERITE_AXE, ModOceanite.OCEANITE_AXE);
        });

        // TOOLS
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.addAfter(Items.NETHERITE_HOE, ModOceanite.OCEANITE_SHOVEL);
            entries.addAfter(ModOceanite.OCEANITE_SHOVEL, ModOceanite.OCEANITE_PICKAXE);
            entries.addAfter(ModOceanite.OCEANITE_PICKAXE, ModOceanite.OCEANITE_AXE);
            entries.addAfter(ModOceanite.OCEANITE_AXE, ModOceanite.OCEANITE_HOE);
        });
    }
}
