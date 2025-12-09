package com.Franco.JarraMod.particulas;

import com.Franco.JarraMod.JarraMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticula {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE=DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, JarraMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> JARRA_PARTICULA=PARTICLE_TYPE.register("jarra_particula",()->new SimpleParticleType(false));
    public static void register(IEventBus IEvent){
        PARTICLE_TYPE.register(IEvent);
    }
}
