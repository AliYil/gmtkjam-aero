package com.aliyil.aero.entity;

import com.aliyil.aero.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Player extends SpriteEntity {
    Array<Block> blocks;
    State state;

    private float moveSpeed = 400;
    private float jumpPower = 700;
    private float gravity = 1500;

    public Player(Game game, Array<Block> blocks) {
        super(game, game.getResourceManager().blockTexture);
        setColor(new Color(Color.GREEN));
        this.blocks = blocks;
        resizeWidth(50);
        enableInputListener(0);
    }

    @Override
    public void start() {
        super.start();
        state = State.MidAir;
        setMoving(true);
    }

    @Override
    public void tick() {
        super.tick();
        switch (state) {
            case Stand:
                if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
                    jump();
                break;
            case MidAir:
                for (Block block : blocks) {
                    if(block.getSprite().getBoundingRectangle().overlaps(getSprite().getBoundingRectangle())){
                        setY(block.getY() + block.getSprite().getHeight());
                        state = State.Stand;
                        setMoving(false);
                    }
                }
                break;
        }
    }

    public void jump(){
        state = State.MidAir;
        accY = -gravity;
        speedY = jumpPower;
        setMoving(true);
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.SPACE:
                if(state == State.Stand)
                    jump();
                break;
            case Input.Keys.LEFT:
                speedX = -moveSpeed;
                break;
            case Input.Keys.RIGHT:
                speedX = moveSpeed;
                break;
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.SPACE:
                break;
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
                speedX = 0;
                break;
        }
        return super.keyUp(keycode);
    }

    private enum State{
        Stand,
        MidAir
    }
}
