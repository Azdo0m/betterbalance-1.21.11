package net.azdo0m.betterbalance.mixin.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Slime.class)
public abstract class MagmaCubeMixin {

    @Inject(method = "dealDamage", at = @At("HEAD"))
    private void betterbalance$fireOnTouch(LivingEntity target, CallbackInfo ci) {
        if ((Object)this instanceof MagmaCube && target instanceof Player player) {
            player.setRemainingFireTicks(100);
        }
    }
}