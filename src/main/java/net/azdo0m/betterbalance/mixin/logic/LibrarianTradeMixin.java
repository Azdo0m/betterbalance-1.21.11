package net.azdo0m.betterbalance.mixin.logic;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Optional;

@Mixin(VillagerTrades.EnchantBookForEmeralds.class)
public class LibrarianTradeMixin {

    @Inject(method = "getOffer", at = @At("RETURN"), cancellable = true)
    private void betterbalance$removeMendingTrade(ServerLevel level, Entity entity, RandomSource random, CallbackInfoReturnable<MerchantOffer> cir) {
        MerchantOffer offer = cir.getReturnValue();
        if (offer == null) return;
        ItemStack resultStack = offer.getResult();
        var lookup = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        var mending = lookup.getOrThrow(Enchantments.MENDING);

        if (EnchantmentHelper.getItemEnchantmentLevel(mending, resultStack) > 0) {
            var unbreaking = lookup.getOrThrow(Enchantments.UNBREAKING);
            ItemStack newBook = new ItemStack(Items.ENCHANTED_BOOK);
            newBook.enchant(unbreaking, 2);
            ItemCost costA = offer.getItemCostA();
            Optional<ItemCost> costB = offer.getItemCostB();

            cir.setReturnValue(new MerchantOffer(
                    costA,
                    costB,
                    newBook,
                    offer.getMaxUses(),
                    offer.getXp(),
                    offer.getPriceMultiplier()
            ));
        }
    }
}