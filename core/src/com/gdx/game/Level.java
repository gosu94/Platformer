package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Components.BoundsComponent;
import com.gdx.game.Entity.Entity;
import com.gdx.game.Interpreter.LevelParser;
import com.gdx.game.Systems.*;

import java.util.ArrayList;
import java.util.List;

public class Level {
    static final String TAG = Level.class.getName();


    public static List<Entity> entities;
    public static Entity playerEntity;
    Clouds clouds;
    static float playerBaseX;
    static float playerBaseY;
    static DrawingSystem drawingSystem;
    static MovingSystem movingSystem;
    static CollisionSystem collisionSystem;
    static JumpSystem jumpSystem;
    static AnimationSystem animationSystem;
    static BoundsComponent playerBounds;


    Level(String filename) {
        init(filename);
    }

    private void init(String filename) {
        entities = new ArrayList<Entity>();
        Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));

        clouds = new Clouds(pixmap.getWidth());
        LevelParser.interpret(pixmap);
        for (int i = 0; i < Level.entities.size(); i++) {
            if ("Player".equals(Level.entities.get(i).getName()))
                playerBounds = (BoundsComponent) Level.entities.get(i).getComponent("BoundsComponent");
        }

        drawingSystem = new DrawingSystem(entities);
        movingSystem = new MovingSystem(entities);
        collisionSystem = new CollisionSystem(entities);
        jumpSystem = new JumpSystem(entities);
        animationSystem = new AnimationSystem(entities);

        pixmap.dispose();
        Gdx.app.debug(TAG, "level '" + filename + "' loaded");
    }

    public void render(SpriteBatch batch) {
        drawingSystem.update(batch);
    }

    void update(float deltaTime) {

        movingSystem.update(deltaTime);
        collisionSystem.update();
        jumpSystem.update(deltaTime);
        animationSystem.update(deltaTime);


    }

}
