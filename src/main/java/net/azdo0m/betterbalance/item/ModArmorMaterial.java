package net.azdo0m.betterbalance.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public class ModArmorMaterial {
    public static final ArmorMaterial OCEANITE = new ArmorMaterial(
            37, // durabilité (copie netherite)
            Map.of(
                    ArmorType.HELMET, 3,
                    ArmorType.CHESTPLATE, 8,
                    ArmorType.LEGGINGS, 6,
                    ArmorType.BOOTS, 3
            ),
            15, // enchantability
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            0.0F, //toughtness
            0.1F, //knockback resistance
            Ingredient.of(ModTags.Items.OCEANITE_INGOT), //placeholder
            EquipmentAssets.NETHERITE //placeholder
    );
}