package com.aliyil.aero;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public final class ResourceManager {
    //Resources
    public BitmapFont bitmapFont;
    public Texture blockTexture;

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

        gameInstance.getParticleEffectManager().loadResources();
    }

    public float getProgress() {
        return assetManager.getProgress();
    }

    public void finishLoading() {
        bitmapFont = assetManager.get("fonts/font.fnt", BitmapFont.class);
        blockTexture = assetManager.get("textures/block.png", Texture.class);

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
