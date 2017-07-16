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

        spawnerHeight = (100f + Block.size*5f);

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
        getSharedValues().blocks.add(block);
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
        float scrollDiff = getSharedValues().player.getY() - scrollAt;
        if(scrollDiff < 0) scrollDiff = 0;
        scrollDiff += 10f;

        float translateAmount = scrollDiff * 0.04f;

        spawn -= translateAmount;

        if(spawn<0){
            spawn += Block.size*2;
            addRandomPlatformsToRow(spawnerHeight);
        }

        getSharedValues().player.translateY(-translateAmount);
        getSharedValues().player.previousBoundingRectangle.setY(getSharedValues().player.previousBoundingRectangle.getY() - translateAmount);

        for (Iterator<Block> iterator = getSharedValues().blocks.iterator(); iterator.hasNext();) {
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

        if(getSharedValues().player.getY()<0){
            new Main(getGameInstance()).start();
        }

        scoreText.setText("SCORE: " + getSharedValues().score);

        super.tick();
    }

    @Override
    public void stop() {
        super.stop();
        getSharedValues().player.kill();
        scoreText.kill();

        for (Block block : getSharedValues().blocks) {
            block.disappear();
        }
    }

    @Override
    public boolean onBackPressed() {
        return super.onBackPressed();
    }
}
