package com.aliyil.aero.entity;

import com.aliyil.aero.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Countdown extends Entity {
    private float life = 0f;
    private float maxWidth = 550f;
    private float drawWidth = 0f;
    private float speed = 1f;
    private float height = 10f;

    public Countdown(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();
    }

    public void setLife(float life) {
        this.life = life;
    }

    @Override
    public void tick() {
        life -= dts() * speed;
        if (life < 0) {
            stop();
            life = 0;
            canRender = false;
        }
        float actualWidth = maxWidth * life;
        drawWidth += (actualWidth - drawWidth) / 4f;
        super.tick();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void shapeRender(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(getColor());
        shapeRenderer.rect(getX() - drawWidth / 2f, getY() - height / 2f, drawWidth, height);
//        shapeRenderer.setColor(getColor().r, getColor().g, getColor().b, 0.2f);
//        shapeRenderer.rect(getX() - maxWidth / 2f, getY() - height/2f, maxWidth, height);
        super.shapeRender(shapeRenderer);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
