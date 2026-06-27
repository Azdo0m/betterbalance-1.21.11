package net.azdo0m.betterbalance.mixin.items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ToolDurabilityMixin {

    @Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
    private void betterbalance$overrideMaxDamage(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        var item = stack.getItem();
        if (item == Items.IRON_PICKAXE || item == Items.IRON_SWORD || item == Items.IRON_AXE || item == Items.IRON_SHOVEL || item == Items.IRON_HOE) {
            cir.setReturnValue(1000);
        }
        else if (item == Items.GOLDEN_PICKAXE || item == Items.GOLDEN_SWORD || item == Items.GOLDEN_AXE || item == Items.GOLDEN_SHOVEL || item == Items.GOLDEN_HOE) {
            cir.setReturnValue(500);
        }
        else if (item == Items.COPPER_PICKAXE || item == Items.COPPER_SWORD || item == Items.COPPER_AXE || item == Items.COPPER_SHOVEL || item == Items.COPPER_HOE) {
            cir.setReturnValue(250);
        }
    }
    // gold like iron
    @Inject(method = "isCorrectToolForDrops", at = @At("HEAD"), cancellable = true)
    private void betterbalance$goldMinesLikeIron(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        var item = stack.getItem();
        if (item == Items.GOLDEN_PICKAXE || item == Items.GOLDEN_AXE || item == Items.GOLDEN_SHOVEL || item == Items.GOLDEN_HOE) {
            if (state.is(BlockTags.NEEDS_IRON_TOOL) || state.is(BlockTags.NEEDS_STONE_TOOL)) {
                cir.setReturnValue(true);
            }
        }
    }
}
