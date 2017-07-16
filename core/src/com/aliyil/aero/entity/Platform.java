//package com.aliyil.aero.entity;
//
//import com.aliyil.aero.Game;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.utils.Array;
//
//public class Platform extends Entity {
//    Array<PlatformBlock> blocks;
//    private Rectangle platformRect;
//
//    public Platform(Game game, int size) {
//        super(game);
//        blocks = new Array<PlatformBlock>(size);
//        for (int i = 0; i<size; i++){
//            PlatformBlock block = new PlatformBlock(game);
//            blocks.add(block);
//        }
//        platformRect = new Rectangle();
//    }
//
//    @Override
//    public void start() {
//        super.start();
//        for (PlatformBlock block : blocks) {
//            block.start();
//        }
//    }
//
//    @Override
//    public void tick() {
//        super.tick();
//    }
//
//    @Override
//    public void stop() {
//        super.stop();
//        for (PlatformBlock block : blocks) {
//            block.stop();
//        }
//    }
//
//    @Override
//    public void kill() {
//        super.kill();
//        for (PlatformBlock block : blocks) {
//            block.kill();
//        }
//    }
//
//    @Override
//    public void setY(float y) {
//        super.setY(y);
//        for (int i = 0; i<blocks.size; i++) {
//            PlatformBlock block = blocks.get(i);
//            block.setY(getY());
//        }
//    }
//
//    @Override
//    public void setX(float x) {
//        super.setX(x);
//        for (int i = 0; i<blocks.size; i++) {
//            PlatformBlock block = blocks.get(i);
//            block.setX(getX() + i * block.getSprite().getWidth());
//        }
//    }
//}
