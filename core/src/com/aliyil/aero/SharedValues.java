package com.aliyil.aero;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public final class SharedValues {
    public Vector2 touch = new Vector2();
    public boolean paused = false;
    //Overall Game Speed Multiplier
    public float gameSpeed = 1f;
    //Theme Color
    public Color bgColor;
    public Rectangle leftRect = new Rectangle(-1000, 0, 1000, Game.h + 1000);
    public Rectangle rightRect = new Rectangle(Game.w, 0, 1000, Game.h + 1000);
    public Rectangle upRect = new Rectangle(0, Game.h, Game.w, 1000);
    public Rectangle downRect = new Rectangle(0, -1000, Game.w, 1000);
}
