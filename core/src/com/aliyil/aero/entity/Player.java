package com.aliyil.aero.entity;

import com.aliyil.aero.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Player extends SpriteEntity {
    private Array<Block> blocks;
    private State state;
    public Rectangle previousBoundingRectangle;
    private Rectangle floorCheckRect;

    private float jumpAcc = 0;
    private float moveSpeed = 400;
    private float baseJumpPower = 600;
    private float jumpHoldPower = 150;
    private float gravity = 2700;

    public Player(Game game, Array<Block> blocks) {
        super(game, game.getResourceManager().blockTexture);
        setColor(new Color(Color.GREEN));
        this.blocks = blocks;
        resizeWidth(50);
        enableInputListener(0);
        previousBoundingRectangle = new Rectangle(getSprite().getBoundingRectangle());
        floorCheckRect = new Rectangle();
    }

    @Override
    public void start() {
        super.start();
        setState(State.MidAir);
        setMoving(true);
    }

    public void setState(State stete){
        this.state = stete;
        switch (stete) {
            case Stand:
                accY = 0;
                break;
            case MidAir:
                accY = -gravity;
                break;
        }
    }

    @Override
    public void tick() {
        super.tick();
        switch (state) {
            case Stand:
                if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
                    jump();
                floorCheckRect.set(getSprite().getBoundingRectangle());
                floorCheckRect.setY(floorCheckRect.getY()-0.1f);
                boolean free = true;
                for (Block block : blocks) {
                    Rectangle blockRect = block.getBoundingRectangle();
                    if(blockRect.overlaps(floorCheckRect)){
                        free = false;
                        break;
                    }
                }
                if(free){
                    setState(State.MidAir);
                }

                break;
            case MidAir:
                //Hold jump mechanic
                if(jumpAcc > 0){
                    if(!Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                        jumpAcc = 0;
                    }
                    jumpAcc *= 0.8f;
                    speedY += jumpAcc;
                }
                else{
                    jumpAcc = 0;
                }

                //Collision detection
                Rectangle playerBoundingRect = getSprite().getBoundingRectangle();
                for (Block block : blocks) {
                    Rectangle blockRect = block.getBoundingRectangle();
                    if(blockRect.overlaps(playerBoundingRect)/* && !blockRect.overlaps(previousBoundingRectangle)*/){
                        if(previousBoundingRectangle.getY() >= blockRect.getY() + blockRect.getHeight()){
                            setY(block.getY() + Block.size);
                            speedY = 0;
                            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                                jump();
                            }else{
                                setState(State.Stand);
                            }
                        }else if(!block.isDisappearing()){
                            block.disappear();
                            getSharedValues().score++;
                        }
                    }
                }

                break;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            speedX = -moveSpeed;
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            speedX = moveSpeed;
        else{
            speedX = 0;
        }
    }

    @Override
    protected void calculateMovings() {
        previousBoundingRectangle.set(getSprite().getBoundingRectangle());
        float horizontalSpeedScale = state == State.MidAir ? 1 : 0.3f;
        speedX += accX * dts();
        speedY += accY * dts();
        translate(
                speedX * dts() * horizontalSpeedScale,
                speedY * dts());
    }

    private void jump(){
        setState(State.MidAir);
        jumpAcc = jumpHoldPower;
        speedY = baseJumpPower;
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

    public enum State{
        Stand,
        MidAir
    }
}
