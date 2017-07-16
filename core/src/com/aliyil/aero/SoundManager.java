package com.aliyil.aero;

import com.badlogic.gdx.audio.Sound;

public final class SoundManager {
    private Game gameInstance;

    SoundManager(Game gameInstance) {
        this.gameInstance = gameInstance;
    }

    public void hit(){
        float vol = 0.3f;
        Sound s = gameInstance.getResourceManager().hit1;
        s.stop();
        s.play(vol);
//        s.setPitch(s.play(vol), 5f);
    }

    public void jump(){
        float vol = 0.5f;
        Sound s = null;

        switch (Utilities.RANDOM.nextInt(4)){
            case 0:
                s = gameInstance.getResourceManager().jump1;
                break;
            case 1:
                s = gameInstance.getResourceManager().jump2;
                break;
            case 2:
                s = gameInstance.getResourceManager().jump3;
                break;
            case 3:
                s = gameInstance.getResourceManager().jump4;
                break;
        }
        s.play(vol);
//        s.setPitch(s.play(vol), 2f);
    }

    public void start(){
        float vol = 0.5f;
        Sound s = gameInstance.getResourceManager().start;
        s.stop();
        s.play(vol);
//        s.setPitch(s.play(vol), 3f);
    }
}
