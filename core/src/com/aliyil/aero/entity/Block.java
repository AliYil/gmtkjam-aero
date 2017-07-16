package com.aliyil.aero.entity;

import com.aliyil.aero.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class Block extends SpriteEntity {
    public static float size = 50;
    private float life;
    private boolean isDisappearing = false;

    public Block(Game game) {
        super(game, game.getResourceManager().blockTexture);
        setColor(new Color(Color.WHITE));
        resizeWidth(size);
    }

    @Override
    public void start() {
        life = 0f;
        super.start();
    }

    @Override
    public void tick() {
        if(isDisappearing){
            if(life > 0){
                life -= dts() * 7f;
                if(life <= 0){
                    kill();
                }else{
                    getSprite().setScale(life);
                }
            }
        }else{
            if(life < 1){
                life += dts() * 7f;
                if(life > 1){
                    life = 1;
                }
                getSprite().setScale(life);
            }
        }

        super.tick();
    }

    public void disappear(){
        isDisappearing = true;
    }

    public boolean isDisappearing(){
        return isDisappearing;
    }

    @Override
    public void stop() {
        super.stop();
    }

    public Rectangle getBoundingRectangle(){
        return new Rectangle(getSprite().getX(), getSprite().getY(), size, size);
    }
}