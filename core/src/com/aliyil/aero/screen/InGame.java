package com.aliyil.aero.screen;

import com.aliyil.aero.Game;
import com.aliyil.aero.entity.Block;
import com.aliyil.aero.entity.Player;
import com.aliyil.aero.entity.Screen;
import com.badlogic.gdx.utils.Array;

public class InGame extends Screen {
    private Array<Block> blocks;
    private Block platform;
    private Player player;

    public InGame(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();
        blocks = new Array<Block>();

        platform = new Block(getGameInstance());
        platform.start();
        platform.setPosition(Game.w * 0.5f, Game.h * 0.2f);

        player = new Player(getGameInstance(), blocks);
        player.setPosition(platform.getPosVector());
        player.start();
        player.jump();

        blocks.add(platform);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void stop() {
        super.stop();
        platform.kill();
        player.kill();
    }

    @Override
    public boolean onBackPressed() {
        return super.onBackPressed();
    }
}
