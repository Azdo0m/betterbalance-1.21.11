package net.azdo0m.betterbalance.mixin.anvil;

import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin extends ItemCombinerScreen<AnvilMenu> {
    public AnvilScreenMixin() { super(null, null, null, null); }
    @Redirect(method = "renderLabels", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/AnvilMenu;getCost()I"))
    private int betterbalance$bypassLimitOnlyForRepairs(AnvilMenu menu) {
        int realCost = menu.getCost();
        var secondItem = menu.getSlot(1).getItem();
        boolean isBook = secondItem.is(Items.ENCHANTED_BOOK);
        if (realCost >= 40 && !isBook) {
            return 1;
        }
        return realCost;
    }

    @Redirect(method = "renderLabels", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;"))
    private MutableComponent betterbalance$showRealNumber(String key, Object[] args) {
        int realCost = this.menu.getCost();
        var secondItem = this.menu.getSlot(1).getItem();
        if (realCost >= 40 && !secondItem.is(Items.ENCHANTED_BOOK)) {
            args[0] = realCost;
        }
        return Component.translatable(key, args);
    }
}
