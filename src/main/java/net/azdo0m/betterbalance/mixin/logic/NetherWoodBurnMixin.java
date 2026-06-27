package net.azdo0m.betterbalance.mixin.logic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.commands.arguments.blocks.BlockStateArgument.getBlock;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class NetherWoodBurnMixin {

    @Inject(method = "onPlace", at = @At("HEAD"))
    private void betterbalance$burnAndSchedule(Level world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        if (!world.isClientSide() && world.dimension() == Level.NETHER) {
            BlockState state = (BlockState) (Object) this;
            if (betterbalance$isCombustible(state) && !betterbalance$isNetherSafe(state)) {
                betterbalance$spreadFireAround(world, pos);
                world.scheduleTick(pos, state.getBlock(), 5);
            }
        }
    }
    @Inject(method = "tick", at = @At("HEAD"))
    private void betterbalance$reigniteTick(ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (world.dimension() == Level.NETHER) {
            BlockState state = (BlockState) (Object) this;
            if (betterbalance$isCombustible(state) && !betterbalance$isNetherSafe(state)) {
                betterbalance$spreadFireAround(world, pos);

                if (random.nextFloat() < 0.10f) {
                    world.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
                } else {
                    world.scheduleTick(pos, state.getBlock(), 5);
                }
            }
        }
    }
    @Unique
    private static void betterbalance$spreadFireAround(Level world, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.relative(dir);
            if (world.isEmptyBlock(neighborPos)) {
                world.setBlockAndUpdate(neighborPos, Blocks.FIRE.defaultBlockState());
            }
        }
    }
    @Unique
    private static boolean betterbalance$isCombustible(BlockState state) {
        return state.is(BlockTags.LOGS) ||
                state.is(BlockTags.PLANKS) ||
                state.is(BlockTags.LEAVES) ||
                state.is(Blocks.CRAFTING_TABLE) ||
                state.is(BlockTags.FENCES) ||
                state.is(BlockTags.WOODEN_DOORS) ||
                state.is(BlockTags.WOODEN_STAIRS) ||
                state.is(BlockTags.WOODEN_TRAPDOORS) ||
                state.is(BlockTags.FENCE_GATES) ||
                state.is(Blocks.BOOKSHELF) ||
                state.is(BlockTags.WOODEN_SHELVES) ||
                state.is(Blocks.MOSS_BLOCK) ||
                state.is(Blocks.MOSS_CARPET);
    }
    @Unique
    private static boolean betterbalance$isNetherSafe(BlockState state) {

        String blockId = state.getBlock().toString();
        if (blockId.contains("crimson") || blockId.contains("warped")) {
            return true;
        }

        return state.is(Blocks.CRIMSON_PLANKS) || state.is(Blocks.WARPED_PLANKS) ||
                state.is(Blocks.CRIMSON_STEM) || state.is(Blocks.WARPED_STEM) ||
                state.is(Blocks.CRIMSON_HYPHAE) || state.is(Blocks.WARPED_HYPHAE) ||
                state.is(Blocks.CRIMSON_NYLIUM) || state.is(Blocks.WARPED_NYLIUM) ||
                state.is(Blocks.NETHER_WART_BLOCK) || state.is(Blocks.WARPED_WART_BLOCK) ||
                state.is(Blocks.NETHER_BRICK_FENCE);
    }
}