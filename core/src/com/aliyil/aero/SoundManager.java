package com.aliyil.aero;

public final class SoundManager {
    private Game gameInstance;

    SoundManager(Game gameInstance) {
        this.gameInstance = gameInstance;
    }

//    public void fire() {
//        fire(1);
//    }
//
//    public void fire(float pitch) {
//        if (!PrefsManager.isSoundEnabled()) return;
//        float volume = 1f;
//        if (Utilities.RANDOM.nextBoolean())
//            gameInstance.getResourceManager().sFire1.play(volume, pitch, 0);
//        else gameInstance.getResourceManager().sFire2.play(volume, pitch, 0);
//    }
}
