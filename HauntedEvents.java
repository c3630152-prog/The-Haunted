package com.example.thehaunted.event;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HauntedEvents {
    private int tickCounter = 0;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        tickCounter++;
        if (tickCounter < 200) return;
        tickCounter = 0;

        Player player = event.player;
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        boolean isNight = serverLevel.isNight();
        boolean isDarkArea = serverLevel.getMaxLocalRawBrightness(player.blockPosition()) <= 4;

        if (isNight && isDarkArea) {
            player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 0, false, false));
            serverLevel.playSound(null, player.blockPosition(), SoundEvents.AMBIENT_CAVE.value(),
                    SoundSource.AMBIENT, 0.8f, 0.7f);
            serverLevel.sendParticles(ParticleTypes.SMOKE,
                    player.getX(), player.getY() + 1.0, player.getZ(),
                    12, 0.6, 0.5, 0.6, 0.02);
        }
    }
}
