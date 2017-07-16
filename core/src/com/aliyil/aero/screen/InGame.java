package com.aliyil.aero.screen;

import com.aliyil.aero.Game;
import com.aliyil.aero.entity.Block;
import com.aliyil.aero.entity.Player;
import com.aliyil.aero.entity.Screen;
import com.badlogic.gdx.utils.Array;

public class InGame extends Screen {
    private Array<Block> blocks;
    private Block block;
    private Player player;

    public InGame(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();
        blocks = new Array<Block>();

        block = new Block(getGameInstance());
        block.start();
        block.setPosition(Game.w * 0.5f, Game.h * 0.3f);
        blocks.add(block);

        player = new Player(getGameInstance(), blocks);
        player.setPosition(block.getPosVector());
        player.start();

        for (int i = 0; i<100; i++){
            block = new Block(getGameInstance());
            block.start();
            block.setPosition(i * block.getSprite().getWidth(), block.getSprite().getHeight()/2f);
            blocks.add(block);
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void stop() {
        super.stop();
        block.kill();
        player.kill();
    }

    @Override
    public boolean onBackPressed() {
        return super.onBackPressed();
    }
}
