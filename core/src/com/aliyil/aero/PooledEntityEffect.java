package com.aliyil.aero;

import com.aliyil.aero.entity.Entity;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;

public class PooledEntityEffect {
    ParticleEffectPool.PooledEffect effect;
    Entity parentEntity;
    boolean respectPause = false;

    public PooledEntityEffect(ParticleEffectPool.PooledEffect effect) {
        this.effect = effect;
    }
}
