package com.aliyil.aero.screen;

import com.aliyil.aero.Game;
//import com.aliyil.aero.entity.DBG_TxInfo;
import com.aliyil.aero.entity.LoadingBar;
import com.aliyil.aero.entity.Screen;


public class LoadingScreen extends Screen {
    private LoadingBar bar;
    private boolean loaded;

    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();
        bar = new LoadingBar(getGameInstance());
        bar.setPosition(Game.w * 0.5f, Game.h * 0.5f);
        bar.start();
        loaded = false;
    }

    @Override
    public void tick() {
        super.tick();
        if (!loaded && getGameInstance().getResourceManager().isLoaded()) {
            loaded = true;
            getGameInstance().getResourceManager().finishLoading();

            new Main(getGameInstance()).start();
//            if (Game.devMode)
//                new DBG_TxInfo(getGameInstance()).start();
        }
    }

    @Override
    public void stop() {
        super.stop();
        bar.kill();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
