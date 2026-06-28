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
            "oceanite_helmet",
            Item::new,
            new Item.Properties().humanoidArmor(OceaniteArmorMaterial.INSTANCE, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(OceaniteArmorMaterial.BASE_DURABILITY))
    );

    //CHESTPLATE
    public static final Item OCEANITE_CHESTPLATE = register(
            "oceanite_chestplate",
            Item::new,
            new Item.Properties().humanoidArmor(OceaniteArmorMaterial.INSTANCE, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(OceaniteArmorMaterial.BASE_DURABILITY))
    );

    //LEGGINGS
    public static final Item OCEANITE_LEGGINGS = register(
            "oceanite_leggings",
            Item::new,
            new Item.Properties().humanoidArmor(OceaniteArmorMaterial.INSTANCE, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(OceaniteArmorMaterial.BASE_DURABILITY))
    );
    //BOOTS
    public static final Item OCEANITE_BOOTS = register(
            "oceanite_boots",
            Item::new,
            new Item.Properties().humanoidArmor(OceaniteArmorMaterial.INSTANCE, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(OceaniteArmorMaterial.BASE_DURABILITY))
    );
}

