package net.azdo0m.betterbalance.mixin.logic;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "getAvailableEnchantmentResults", at = @At("RETURN"), cancellable = true)
    private static void betterbalance$removeAllMending(int level, ItemStack stack, Stream<?> possibilities, CallbackInfoReturnable<List<EnchantmentInstance>> cir) {
        List<EnchantmentInstance> originalList = cir.getReturnValue();
        if (originalList == null || originalList.isEmpty()) return;
        List<EnchantmentInstance> filteredList = new ArrayList<>();

        for (EnchantmentInstance instance : originalList) {
            if (!instance.enchantment().is(Enchantments.MENDING)) {
                filteredList.add(instance);
            }
        }
        cir.setReturnValue(filteredList);
    }
}