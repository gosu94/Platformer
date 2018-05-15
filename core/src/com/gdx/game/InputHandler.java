package com.gdx.game;

import com.badlogic.gdx.*;
import com.gdx.game.Components.JumpComponent;
import com.gdx.game.Components.VelocityComponent;
import com.gdx.game.Entity.Entity;
import com.gdx.game.GameScreens.GameOverScreen;
import com.gdx.game.GameScreens.MenuScreen;

import java.util.ArrayList;
import java.util.List;

import static com.gdx.game.WorldController.cameraHandler;

public class InputHandler extends InputAdapter {

    private static final String TAG = WorldController.class.getName();
    static List<Memento> mementos = new ArrayList<Memento>();
    static int score;

    static State state;
    static private Game game;

    public InputHandler(Game game) {
        Gdx.input.setInputProcessor(this);

        InputHandler.game = game;

    }

    static public void handlePlayerInput(float deltaTime, Entity player) {

        // if (cameraHandler.hasTarget(Level.player)) {

        VelocityComponent velocityComponent = (VelocityComponent) player.getComponent("VelocityComponent");
        JumpComponent jumpComponent = (JumpComponent) player.getComponent("JumpComponent");

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            velocityComponent.velocity.x = -velocityComponent.maximalSpeed.x;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            velocityComponent.velocity.x = velocityComponent.maximalSpeed.x;


        //Level.player.setJumping(true);
//Level.player.setJumping(false);
        jumpComponent.jumpKeyPressed = Gdx.input.isKeyPressed(Input.Keys.SPACE);


    }

    static public void backToMenu() {
        game.setScreen(new MenuScreen(game));
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ENTER) {

            cameraHandler.setTarget(cameraHandler.hasTarget() ? null : Level.playerBounds);
            Gdx.app.debug(TAG, "Camera follow enabled: " + cameraHandler.hasTarget());
        }
        if (keycode == Input.Keys.S) {
            state = new State(Level.entities, score);
            if (mementos != null)
                mementos.add(Originator.saveToMemento(state, "savedGame" + Integer.toString(mementos.size() + 1)));
            else
                mementos.add(Originator.saveToMemento(state, "savedGame0"));
        }
        if (keycode == Input.Keys.L) {
            Originator.loadFromMemento(mementos.get(mementos.size() - 1));
        }
        if (keycode == Input.Keys.G) {
            game.setScreen(new GameOverScreen(game));
        }
        if (keycode == Input.Keys.ESCAPE) {
            backToMenu();
        }
        return false;
    }

    public void handleDebugInput(float deltaTime) {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) return;

        if (!cameraHandler.hasTarget(Level.playerBounds)) {
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
