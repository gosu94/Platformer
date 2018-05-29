package com.gdx.game.GameScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gdx.game.Constants;
import com.gdx.game.Globals;

public class FinishScreen extends AbstractGameScreen {
    private static final String TAG = MenuScreen.class.getName();
    public static Skin skin;
    FinishScreen.Text text;
    float countdown;
    private Stage stage;

    public FinishScreen(Game game) {
        super(game);
    }

    private void rebuildStage() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        countdown = 5;
        text = new FinishScreen.Text();

        stage.clear();
        stage.addActor(text);

    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        countdown -= deltaTime;

        if (countdown < 0) {
            game.setScreen(new MenuScreen(game));
            Globals.lives = Constants.LIVES_START;
            Globals.points = 0;
        }

        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH,
                Constants.VIEWPORT_GUI_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        rebuildStage();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


    class Text extends Actor {

        BitmapFont font;

        public Text() {
            font = new BitmapFont();
            font.setColor(255, 255, 255, 1);
            font.getData().setScale(3);

        }

        @Override
        public void draw(Batch batch, float parentAlpha) {

            font.draw(batch, "Congratulations!!!", Constants.VIEWPORT_GUI_WIDTH / 2 - 180,
                    Constants.VIEWPORT_GUI_HEIGHT / 2 + 80);
            font.draw(batch, "You have finished the game  ", Constants.VIEWPORT_GUI_WIDTH / 2 - 280,
                    Constants.VIEWPORT_GUI_HEIGHT / 2 + 20);
            font.draw(batch, "You record score is " + Globals.points, Constants.VIEWPORT_GUI_WIDTH / 2 - 230,
                    Constants.VIEWPORT_GUI_HEIGHT / 2 - 40);
        }

        @Override
        public Actor hit(float x, float y, boolean touchable) {
            return null;
        }
    }
}
