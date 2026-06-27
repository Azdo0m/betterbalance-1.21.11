package net.azdo0m.betterbalance.mixin.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class SpiderMixin extends LivingEntity {
    protected SpiderMixin(EntityType<? extends LivingEntity> entityType, net.minecraft.world.level.Level level) {
        super(entityType, level);
    }

    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void betterbalance$rollSpiderRareEffects(
            ServerLevelAccessor serverLevelAccessor,
            DifficultyInstance difficultyInstance,
            EntitySpawnReason entitySpawnReason,
            @Nullable SpawnGroupData spawnGroupData,
            CallbackInfoReturnable<SpawnGroupData> cir
    ) {
        if ((Object) this instanceof Spider) {
            if (this.random.nextFloat() < 0.40f) {
                this.addEffect(new MobEffectInstance(MobEffects.WEAVING, -1, 0, false, true));
            }
        }
    }

    @Inject(method = "doHurtTarget", at = @At("RETURN"))
    private void betterbalance$applyNauseaOnAttack(
            ServerLevel level,
            Entity target,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (cir.getReturnValue() && (Object) this instanceof Spider) {
            if (target instanceof LivingEntity livingTarget) {
                livingTarget.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 160, 0));
            }
        }
    }
}