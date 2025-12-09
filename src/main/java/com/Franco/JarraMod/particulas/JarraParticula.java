package com.Franco.JarraMod.particulas;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class JarraParticula extends TextureSheetParticle {
    protected JarraParticula(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet sp) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.xd = pXSpeed;
        this.yd = pYSpeed;
        this.zd = pZSpeed;
        this.gravity = 0.0F;
        this.quadSize = 0.2F;
        this.lifetime = 30;
        this.friction = 1.0F;
        this.hasPhysics = false;
        this.setSpriteFromAge(sp);
    }

    @Override
    public ParticleRenderType getRenderType() {
         return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    public static class Provider implements ParticleProvider<SimpleParticleType>{
        private final SpriteSet sp;

        public Provider(SpriteSet sp) {
            this.sp = sp;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new JarraParticula(pLevel,pX,pY,pZ,pXSpeed,pYSpeed,pZSpeed,this.sp);
        }
    }
}
