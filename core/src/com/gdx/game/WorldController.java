package com.gdx.game;

import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.List;


public class WorldController {
    private static final String TAG = WorldController.class.getName();
    public static List<Memento> mementos = new ArrayList<Memento>();
    static CameraHandler cameraHandler;
    Level level;
    InputHandler inputHandler;
    static int score;
    int lives;
    State state;
    public static Game game;


    public WorldController(Game game) {
        WorldController.game = game;
        init();
    }

    public void init() {
        lives = Constants.LIVES_START;
        cameraHandler = new CameraHandler();
        inputHandler = new InputHandler(game);
        initLevel();
    }

    void initLevel() {
        score = 0;
        level = new Level(Constants.LEVEL_01);
        cameraHandler.setTarget(Level.playerBounds);
        cameraHandler.setPosition(0, 3);
    }

    public void update(float deltaTime) {
        inputHandler.handleDebugInput(deltaTime);
        InputHandler.handlePlayerInput(deltaTime, Level.playerEntity);
        level.update(deltaTime);
        // collisionHandler.testCollisionsForPlayer();
        //collisionHandler.testCollisionForEnemies();
        cameraHandler.update(deltaTime);
    }






}
