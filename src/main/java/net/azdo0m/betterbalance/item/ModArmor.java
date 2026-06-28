package net.azdo0m.betterbalance.item;

import net.azdo0m.betterbalance.BetterBalance;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Function;

import static net.azdo0m.betterbalance.item.ModItems.register;

public class ModArmor {
    public static void registerModArmors() {
        BetterBalance.LOGGER.info("Registering Mod Armors for " + BetterBalance.MOD_ID);
    }

    //HELMET
    public static final Item OCEANITE_HELMET = register(
            "armor/oceanite_inventory_helmet",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterial.OCEANITE, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(ModArmorMaterial.BASE_DURABILITY))
    );

    //CHESTPLATE
    public static final Item OCEANITE_CHESTPLATE = register(
            "armor/oceanite_inventory_chestplate",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterial.OCEANITE, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(ModArmorMaterial.BASE_DURABILITY))
    );

    //LEGGINGS
    public static final Item OCEANITE_LEGGINGS = register(
            "armor/oceanite_inventory_leggings",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterial.OCEANITE, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(ModArmorMaterial.BASE_DURABILITY))
    );
    //BOOTS
    public static final Item OCEANITE_BOOTS = register(
            "armor/oceanite_inventory_boots",
            Item::new,
            new Item.Properties().humanoidArmor(ModArmorMaterial.OCEANITE, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(ModArmorMaterial.BASE_DURABILITY))
    );
}

