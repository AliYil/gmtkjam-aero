package com.aliyil.aero.entity;

import com.aliyil.aero.Game;
import com.badlogic.gdx.graphics.Color;

public class Block extends SpriteEntity {
    public Block(Game game) {
        super(game, game.getResourceManager().blockTexture);
        resizeWidth(50);
        setColor(new Color(Color.BROWN));
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void stop() {
        super.stop();
    }
}