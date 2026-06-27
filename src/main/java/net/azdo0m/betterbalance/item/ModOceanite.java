package net.azdo0m.betterbalance.item;

import net.azdo0m.betterbalance.BetterBalance;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.minecraft.world.item.Items.registerBlock;

public class ModOceanite {
    public static void registerOceanite() {
        BetterBalance.LOGGER.info("Registering Oceanite Items for " + BetterBalance.MOD_ID);
    }
        //BLOCKS
    public static final Item OCEANITE_BLOCK = register("oceanite_block");

        // INGREDIENTS
    public static final Item OCEANITE_INGOT = register("oceanite_ingot");
        // ARMOR
    public static final Item OCEANITE_HELMET = register("armor/oceanite_inventory_helmet");
    public static final Item OCEANITE_CHESTPLATE = register("armor/oceanite_inventory_chestplate");
    public static final Item OCEANITE_LEGGINGS = register("armor/oceanite_inventory_leggings");
    public static final Item OCEANITE_BOOTS = register("armor/oceanite_inventory_boots");
        // WEAPONS
    public static final Item OCEANITE_SWORD = register("weapons-tools/oceanite_sword");
    public static final Item OCEANITE_SPEAR = register("weapons-tools/oceanite_spear");
        // TOOLS
    public static final Item OCEANITE_PICKAXE = register("weapons-tools/oceanite_pickaxe");
    public static final Item OCEANITE_AXE = register("weapons-tools/oceanite_axe");
    public static final Item OCEANITE_SHOVEL = register("weapons-tools/oceanite_shovel");
    public static final Item OCEANITE_HOE = register("weapons-tools/oceanite_hoe");

    // Creation
    private static Item register(String name) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BetterBalance.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ITEM, key, new Item(new Item.Properties().setId(key)));
    }
}



