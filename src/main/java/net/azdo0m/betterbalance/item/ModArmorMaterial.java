package net.azdo0m.betterbalance.item;

import net.azdo0m.betterbalance.BetterBalance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.azdo0m.betterbalance.item.ModArmor;

import java.util.Map;

public class ModArmorMaterial {
    public static void registerModArmorMaterial(){
        BetterBalance.LOGGER.info("Registering Mod Armors Material for " + BetterBalance.MOD_ID);
    }

    public static final int BASE_DURABILITY = 37;
    public static final TagKey<Item> REPAIR_OCEANITE_ARMOR = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BetterBalance.MOD_ID, "repair_oceanite_armor"));
    public static final ResourceKey<EquipmentAsset> OCEANITE_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(BetterBalance.MOD_ID, "oceanite"));

    public static final ArmorMaterial OCEANITE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    ArmorType.HELMET, 3,
                    ArmorType.CHESTPLATE, 8,
                    ArmorType.LEGGINGS, 6,
                    ArmorType.BOOTS, 3
            ),
            15, // enchantmentValue
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            3F, //toughtness
            0.1F, //knockback resistance
            REPAIR_OCEANITE_ARMOR,
            OCEANITE_ARMOR_MATERIAL_KEY
    );
}