package com.aliyil.aero.screen;

import com.aliyil.aero.Game;
import com.aliyil.aero.Utilities;
import com.aliyil.aero.entity.Block;
import com.aliyil.aero.entity.Player;
import com.aliyil.aero.entity.Screen;
import com.aliyil.aero.entity.Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class InGame extends Screen {
    private Array<Block> blocks;
    private Player player;
    private Text scoreText;

    private float spawnerHeight;
    private float spawn;

    public InGame(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();
        getSharedValues().score = 0;

        blocks = new Array<Block>();

        addPlatform(Game.w * 0.5f, 100f);

        player = new Player(getGameInstance(), blocks);
        player.zIndex = -10;
        player.setPosition(Game.w * 0.5f, 101f+Block.size);
        player.start();

        for (int i = 0; i<20; i++){
            addPlatform(i * Block.size, 100f + Block.size * 3f);
        }

//        for (int i = 0; i<20; i++){
//            addRandomPlatformsToRow((100f + Block.size*5f) + i * 100f);
//        }

        spawnerHeight = (100f + Block.size*4f);

        scoreText = new Text(getGameInstance(), "SCORE:");
        scoreText.setPosition(10f, Game.h - scoreText.getBoundingRectangle().height - 10);
        scoreText.setColor(new Color(Color.CYAN));
        scoreText.zIndex = 10;
        scoreText.start();
    }

    private void addRandomPlatformsToRow(float y){
        int col = 0;
        boolean spawned = false;
        float leftMost = 0f;

        while (col < 15){
            if(spawned){
                if(Utilities.RANDOM.nextInt(4) < 2){
                    addPlatform(leftMost + Block.size * col, y);
                }else{
                    spawned = false;
                }
            }else{
                if(Utilities.RANDOM.nextInt(4) < 1){
                    addPlatform(leftMost + Block.size * col, y);
                    spawned = true;
                }
            }

            col++;
        }
    }

    private void addPlatform(float x, float y){
        Block block = new Block(getGameInstance());
        block.start();
        block.setPosition(x, y);
        blocks.add(block);
    }

    @Override
    public void tick() {
        float spawnHeightLimit = Game.h;
        if(spawnerHeight < spawnHeightLimit){
            spawnerHeight += dts() * 300f;
            spawn -= dts() * 300f;
        }
        else
            spawnerHeight = spawnHeightLimit;

        float scrollAt = Game.h * 0.5f;
        float scrollDiff = player.getY() - scrollAt;
        if(scrollDiff < 0) scrollDiff = 0;
        scrollDiff += 10f;

        float translateAmount = scrollDiff * 0.04f;

        spawn -= translateAmount;

        if(spawn<0){
            spawn += Block.size*2;
            addRandomPlatformsToRow(spawnerHeight);
        }

        player.translateY(-translateAmount);
        player.previousBoundingRectangle.setY(player.previousBoundingRectangle.getY() - translateAmount);

        for (Iterator<Block> iterator = blocks.iterator(); iterator.hasNext();) {
            Block block = iterator.next();
            if(!block.isLiving()){
                iterator.remove();
                continue;
            }

            block.translateY(-translateAmount);
            if(block.getY() < Game.h * 0.05f){
                block.disappear();
                iterator.remove();
            }
        }

        if(player.getY()<0){
            stop();
            start();
        }

        scoreText.setText("SCORE: " + getSharedValues().score);

        super.tick();
    }

    @Override
    public void stop() {
        super.stop();
        player.kill();
        scoreText.kill();

        for (Block block : blocks) {
            block.kill();
        }
    }

    @Override
    public boolean onBackPressed() {
        return super.onBackPressed();
    }
}
