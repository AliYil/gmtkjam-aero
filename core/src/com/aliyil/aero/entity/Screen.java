package com.aliyil.aero.entity;

import com.aliyil.aero.Game;

public abstract class Screen extends Entity {

    public Screen(Game game) {
        super(game);
    }

    @Override
    public void start() {
        super.start();
        if (getGameInstance().getCurrentScreen() != null && getGameInstance().getCurrentScreen().isLiving())
            getGameInstance().getCurrentScreen().kill();
        getGameInstance().setCurrentScreen(this);
    }

    public boolean onBackPressed() {
        return false;
    }
}