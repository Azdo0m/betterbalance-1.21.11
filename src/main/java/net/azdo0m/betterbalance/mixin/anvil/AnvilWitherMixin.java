package net.azdo0m.betterbalance.mixin.anvil;

import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.core.registries.Registries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Witherstar (MENDING)
@Mixin(AnvilMenu.class)
public abstract class AnvilWitherMixin extends ItemCombinerMenu {
    @Shadow
    @Final
    private DataSlot cost;
    public AnvilWitherMixin() { super(null, 0, null, null, null); }

    @Inject(method = "createResult", at = @At("RETURN"))
    private void betterbalance$injectWitherMending(CallbackInfo ci) {
        ItemStack left = this.inputSlots.getItem(0);
        ItemStack right = this.inputSlots.getItem(1);

        if (right.is(Items.NETHER_STAR) && !left.isEmpty()) {
            var lookup = this.player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            var mending = lookup.getOrThrow(Enchantments.MENDING);

            if (mending.value().canEnchant(left)) {
                //if (EnchantmentHelper.getItemEnchantmentLevel(mending, left) == 0) {
                    ItemStack result = left.copy();
                    result.enchant(mending, 1);
                    this.resultSlots.setItem(0, result);
                    this.cost.set(40);

                    this.broadcastChanges();
                //}
            }
        }
    }
}