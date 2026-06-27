package net.azdo0m.betterbalance.mixin.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.nautilus.ZombieNautilus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ZombieNautilusMixin {

    @Inject(method = "hurtServer", at = @At("HEAD"))
    private void betterbalance$poisonOnTouch(ServerLevel level, DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (damageSource.getEntity() instanceof ZombieNautilus) {
            LivingEntity victim = (LivingEntity) (Object) this;

            if (victim.getRandom().nextFloat() < 1.0f) {
                victim.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
            }
        }
    }
}