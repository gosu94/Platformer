package com.gdx.game.GameScreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gdx.game.Constants;
import com.gdx.game.Menu.Button;
import com.gdx.game.Menu.Buttons;
import com.gdx.game.Menu.Decorators;

public class MenuScreen extends AbstractGameScreen {

    private static final String TAG = MenuScreen.class.getName();
    public static Skin skin;
    public static BitmapFont font;
    private Stage stage;
    private Button playButton;
    private Button exitButton;
    private Button continueButton;


    public MenuScreen(Game game) {
        super(game);
    }


    private void rebuildStage() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        font = new BitmapFont();

        playButton = new Decorators.FadeOut(new Decorators.Shake(new Buttons.PlayButton()));
        exitButton = new Decorators.RedHover(new Buttons.ExitButton());



        stage.clear();
        // stage.addActor(continueButton);
        stage.addActor(playButton);
        stage.addActor(exitButton);
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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





}
