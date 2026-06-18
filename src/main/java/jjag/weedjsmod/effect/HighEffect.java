package jjag.weedjsmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;

public class HighEffect extends StatusEffect {
   public HighEffect() {
       super(
               StatusEffectCategory.NEUTRAL,
               0x22BB44
       );
   }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
       if (entity instanceof PlayerEntity player) {
           player.getHungerManager().addExhaustion(8.0f);
       }
       return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
       return duration % 60 == 0;
   }
}
