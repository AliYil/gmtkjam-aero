package com.aliyil.aero.entity;

import com.aliyil.aero.Game;
import com.aliyil.aero.Utilities;
import com.aliyil.aero.OnClickListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class ClickableSprite extends SpriteEntity {
    public int inpIndex;
    public float clickableAreaScale = 1;
    public Rectangle clickableArea;
    private OnClickListener listener;

    public ClickableSprite(Game game, Texture texture) {
        super(game, texture);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void start() {
        enableInputListener(inpIndex);
        super.start();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!isRunning() || listener == null) return false;
        Rectangle rect;
        if (clickableArea == null) {
            rect = Utilities.scaleRectangle(getSprite().getBoundingRectangle(), clickableAreaScale);
        } else {
            rect = clickableArea;
        }

        if (rect.contains(getSharedValues().touch.x, getSharedValues().touch.y)) {
            return listener.onClick();
        }
        return false;
    }

    public void click() {
        if (listener != null) listener.onClick();
    }
}
