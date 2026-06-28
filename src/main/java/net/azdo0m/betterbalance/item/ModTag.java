package net.azdo0m.betterbalance.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTag {
    public static class Items {
        public static final TagKey<Item> OCEANITE_INGOTS =
                TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("betterbalance", "oceanite_ingots"));
    }
}
