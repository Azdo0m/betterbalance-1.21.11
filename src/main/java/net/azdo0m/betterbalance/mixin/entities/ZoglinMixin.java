package net.azdo0m.betterbalance.mixin.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.Zoglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zoglin.class)
public abstract class ZoglinMixin {

    @Inject(
            method = "doHurtTarget",
            at = @At("RETURN")
    )
    private void betterbalance$poisonOnHit(
            ServerLevel level,
            Entity target,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!cir.getReturnValue()) return;
        if (target instanceof LivingEntity living
                && !(target instanceof IronGolem)) {
            living.addEffect(
                    new MobEffectInstance(
                            MobEffects.POISON,
                            200,
                            0
                    ),
                    (Entity)(Object)this
            );
        }
    }
}