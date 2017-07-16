package com.aliyil.aero;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public final class ResourceManager {
    //Resources
    public BitmapFont bitmapFont;
    public Texture blockTexture;

    public Sound hit1;

    public Sound jump1;
    public Sound jump2;
    public Sound jump3;
    public Sound jump4;

    public Sound start;

    private Game gameInstance;
    private AssetManager assetManager;

    ResourceManager(Game gameInstance) {
        this.gameInstance = gameInstance;

        assetManager = new AssetManager();
        Texture.setAssetManager(assetManager);

    }

    void loadResources() {
        assetManager.load("fonts/font.fnt", BitmapFont.class);
        assetManager.load("textures/block.png", Texture.class);

        assetManager.load("sounds/hit1.wav", Sound.class);

        assetManager.load("sounds/jump1.wav", Sound.class);
        assetManager.load("sounds/jump2.wav", Sound.class);
        assetManager.load("sounds/jump3.wav", Sound.class);
        assetManager.load("sounds/jump4.wav", Sound.class);

        assetManager.load("sounds/start.wav", Sound.class);

        gameInstance.getParticleEffectManager().loadResources();
    }

    public float getProgress() {
        return assetManager.getProgress();
    }

    public void finishLoading() {
        bitmapFont = assetManager.get("fonts/font.fnt", BitmapFont.class);
        blockTexture = assetManager.get("textures/block.png", Texture.class);


        hit1 = assetManager.get("sounds/hit1.wav", Sound.class);

        jump1 = assetManager.get("sounds/jump1.wav", Sound.class);
        jump2 = assetManager.get("sounds/jump2.wav", Sound.class);
        jump3 = assetManager.get("sounds/jump3.wav", Sound.class);
        jump4 = assetManager.get("sounds/jump4.wav", Sound.class);;

        start = assetManager.get("sounds/start.wav", Sound.class);

//        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    void dispose() {
        assetManager.dispose();
        gameInstance.getParticleEffectManager().releaseResources();
    }

    public boolean isLoaded() {
        return assetManager.update();
    }
}
