package net.azdo0m.betterbalance.mixin.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item; // Import crucial
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class ElytraWaterMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void betterbalance$disableElytraInWater(CallbackInfo ci) {
        Player player = (Player) (Object) this;

        if (!player.level().isClientSide()) {
            if (player.isInWater() || player.level().isRainingAt(player.blockPosition())) {
                ItemStack chestStack = player.getItemBySlot(EquipmentSlot.CHEST);
                if (chestStack.is(Items.ELYTRA)) {
                    Item elytraItem = Items.ELYTRA;
                    player.getCooldowns().addCooldown(chestStack, 600); // 30s
                    if (player.isFallFlying()) {
                        player.stopFallFlying();
                    }
                }
            }
        }
    }
}