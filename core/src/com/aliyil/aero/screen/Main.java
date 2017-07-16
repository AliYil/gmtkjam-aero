package com.aliyil.aero.screen;

import com.aliyil.aero.Game;
import com.aliyil.aero.entity.Block;
import com.aliyil.aero.entity.Player;
import com.aliyil.aero.entity.Screen;
import com.aliyil.aero.entity.Text;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

public class Main extends Screen {
    private Text text1;
    private Text text2;
    public Main(Game game) {
        super(game);
        enableInputListener(0);
    }

    @Override
    public void start() {
        super.start();
        getSharedValues().blocks = new Array<Block>();

        Player player = new Player(getGameInstance());
        player.zIndex = -10;
        player.setPosition(Game.w * 0.5f, 101f+Block.size);
        player.canRender = true;

        getSharedValues().player = player;

        addPlatform(Game.w * 0.5f, 100f);

        for (int i = 0; i<20; i++){
            addPlatform(i * Block.size, 100f + Block.size * 3f);
        }


        text1 = new Text(getGameInstance(), "");
        text1.setPosition(Game.w * 0.5f, Game.h * 0.85f);
        text1.setCentered(true);
        text1.setColor(new Color(Color.CYAN));
        text1.start();

        text2 = new Text(getGameInstance(), "");
        text2.setPosition(Game.w * 0.5f, Game.h * 0.7f);
        text2.setScale(3f);
        text2.setCentered(true);
        text2.setColor(new Color(Color.CYAN));
        text2.start();

        if(getSharedValues().score <= 0){
            text1.setScale(6f);
            text1.setText("AERO BOX");
            text2.setText("PRESS SPACE");
        }else{
            text1.setScale(4f);
            text1.setText("TRY AGAIN");
            text2.setText("YOUR SCORE: " + getSharedValues().score);
        }

        getGameInstance().getSoundManager().start();
    }

    private void addPlatform(float x, float y){
        Block block = new Block(getGameInstance());
        block.start();
        block.setPosition(x, y);
        getSharedValues().blocks.add(block);
    }

    @Override
    public void tick() {
        super.tick();
        getSharedValues().player.setX(Game.w * 0.5f);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.SPACE:
                getSharedValues().player.start();
                getSharedValues().player.jump();
                new InGame(getGameInstance()).start();
                break;
        }
        return super.keyDown(keycode);
    }

    @Override
    public void stop() {
        super.stop();
        text1.kill();
        text2.kill();
    }

    @Override
    public boolean onBackPressed() {
        return super.onBackPressed();
    }
}
