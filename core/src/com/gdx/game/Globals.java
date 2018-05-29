package com.gdx.game;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.Memento.Memento;

import java.util.ArrayList;
import java.util.List;

public class Globals {
    public static int points = 0;
    public static boolean isPlayerMoving = false;
    public static int lives = Constants.LIVES_START;
    public static boolean isPlayerkilled = false;
    public static List<Memento> mementos = new ArrayList<Memento>();
    public static Vector2 startingPoint = new Vector2(0, 0);
}
