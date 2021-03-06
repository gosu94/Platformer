package com.gdx.game.GameScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.gdx.game.Constants;
import com.gdx.game.Globals;
import com.gdx.game.Memento.Originator;
import com.gdx.game.WorldController;
import com.gdx.game.WorldRenderer;

public class GameScreen extends AbstractGameScreen {

    private static final String TAG = GameScreen.class.getName();
    private WorldController worldController;
    private WorldRenderer worldRenderer;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void render(float deltaTime) {
        float delta = Math.min(1 / 60f, deltaTime);
        worldController.update(delta);
        Gdx.gl.glClearColor(174 / 255.0f, 222 / 255.0f, 203 / 255.0f, 255 / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override
    public void show() {
        Globals.lives = Constants.LIVES_START;
        worldController = new WorldController(game);
        worldRenderer = new WorldRenderer(worldController);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void hide() {
        worldRenderer.dispose();
        if (Globals.mementos.size() > 0)
            Originator.saveMementos(Globals.mementos);
        Gdx.input.setCatchBackKey(false);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }
}
