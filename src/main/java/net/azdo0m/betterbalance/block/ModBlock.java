package net.azdo0m.betterbalance.block;
import net.azdo0m.betterbalance.BetterBalance;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import java.util.function.Function;

public class ModBlock {
    public static void registerModBlock() {
        BetterBalance.LOGGER.info("Registering Mod Blocks for " + BetterBalance.MOD_ID);
    }

    // BLOCKS
    public static final Block OCEANITE_BLOCK = register(
            "oceanite_block",
            Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)
                    .mapColor(MapColor.COLOR_CYAN)
                    .requiresCorrectToolForDrops()
    );

// INGREDIENTS
//                    .mapColor(MapColor.COLOR_CYAN)
//                    .strength(50.0F, 1200.0F)
//                    .sound(SoundType.NETHERITE_BLOCK)
//                    .requiresCorrectToolForDrops()

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(properties.setId(blockKey));

        ResourceKey<Item> itemKey = keyOfItem(name);
        BlockItem blockItem = new BlockItem(block, new Item.Properties()
                .setId(itemKey)
                .useBlockDescriptionPrefix()
        );
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }
    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(BetterBalance.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BetterBalance.MOD_ID, name));
    }
}