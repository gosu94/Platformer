package com.gdx.game;

import com.badlogic.gdx.*;
import com.gdx.game.GameObjects.Entity;
import com.gdx.game.GameObjects.VelocityComponent;
import com.gdx.game.GameScreens.MenuScreen;

import java.util.ArrayList;
import java.util.List;

import static com.gdx.game.WorldController.cameraHandler;

public class InputHandler extends InputAdapter {

    private static final String TAG = WorldController.class.getName();
    static List<Memento> mementos = new ArrayList<Memento>();
    static int score;
    CollisionHandler collisionHandler;
    static State state;
    static private Game game;

    public InputHandler(Game game) {
        Gdx.input.setInputProcessor(this);
        collisionHandler = new CollisionHandler();
        InputHandler.game = game;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ENTER) {

            cameraHandler.setTarget(cameraHandler.hasTarget() ? null : Level.player);
            Gdx.app.debug(TAG, "Camera follow enabled: " + cameraHandler.hasTarget());
        }
        if (keycode == Input.Keys.S) {
            state = new State(Level.player, Level.enemies, Level.rocks, Level.coins, score);
            if (mementos != null)
                mementos.add(Originator.saveToMemento(state, "savedGame" + Integer.toString(mementos.size() + 1)));
            else
                mementos.add(Originator.saveToMemento(state, "savedGame0"));
        }
        if (keycode == Input.Keys.L) {
            Originator.loadFromMemento(mementos.get(mementos.size() - 1));
        }
        if (keycode == Input.Keys.ESCAPE) {
            backToMenu();
        }
        return false;
    }

    static public void backToMenu() {
        game.setScreen(new MenuScreen(game));
    }


    static public void handlePlayerInput(float deltaTime, Entity player) {

        // if (cameraHandler.hasTarget(Level.player)) {

        VelocityComponent velocityComponent = (VelocityComponent) player.getComponent("VelocityComponent");

                if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                    velocityComponent.velocity.x = -velocityComponent.maximalSpeed.x;
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                    velocityComponent.velocity.x = velocityComponent.maximalSpeed.x;


        //if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
        //   Level.player.setJumping(true);
        //else
        //   Level.player.setJumping(false);

        // }



    }

    public void handleDebugInput(float deltaTime) {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) return;

        if (!cameraHandler.hasTarget(Level.player)) {
            float camMoveSpeed = 5 * deltaTime;
            float camMoveSpeedAccelerationFactor = 5;
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) camMoveSpeed *= camMoveSpeedAccelerationFactor;
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) moveCamera(-camMoveSpeed, 0);
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveCamera(camMoveSpeed, 0);
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) moveCamera(0, camMoveSpeed);
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) moveCamera(0, -camMoveSpeed);
            if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) cameraHandler.setPosition(0, 0);
        }


        //Camera zoom
        float camZoomSpeed = 1 * deltaTime;
        float camZoomSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) camZoomSpeed *= camZoomSpeedAccelerationFactor;
        if (Gdx.input.isKeyPressed(Input.Keys.COMMA)) cameraHandler.addZoom(camZoomSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)) cameraHandler.addZoom(-camZoomSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.SLASH)) cameraHandler.setZoom(1);

    }

    private void moveCamera(float x, float y) {
        x += cameraHandler.getPosition().x;
        y += cameraHandler.getPosition().y;
        cameraHandler.setPosition(x, y);
    }
}
