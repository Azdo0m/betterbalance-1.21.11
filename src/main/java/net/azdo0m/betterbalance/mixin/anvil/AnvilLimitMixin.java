package net.azdo0m.betterbalance.mixin.anvil;

import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilMenu.class)
public abstract class AnvilLimitMixin extends ItemCombinerMenu {

    public AnvilLimitMixin() { super(null, 0, null, null, null); }

    @Shadow @Final private DataSlot cost;

    @Redirect(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/DataSlot;get()I"))
    private int betterbalance$conditionalLimit(DataSlot instance) {
        int actualCost = instance.get();
        ItemStack secondItem = this.inputSlots.getItem(1);
        boolean isPureRepair = !secondItem.is(Items.ENCHANTED_BOOK) && !secondItem.isEnchanted();
        if (isPureRepair) {
            return 0;
        }
        return actualCost;
    }

//recovery
    @Inject(method = "mayPickup", at = @At("HEAD"), cancellable = true)
    private void betterbalance$allowPickup(Player player, boolean present, CallbackInfoReturnable<Boolean> cir) {
        ItemStack secondItem = this.inputSlots.getItem(1);
        boolean isEnchanting = secondItem.is(Items.ENCHANTED_BOOK) || secondItem.isEnchanted();
        if (isEnchanting && this.cost.get() >= 40 && !player.getAbilities().instabuild) {
            cir.setReturnValue(false);
            return;
        }
        cir.setReturnValue(player.getAbilities().instabuild || player.experienceLevel >= this.cost.get());
    }
}