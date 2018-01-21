package com.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MenuScreen extends AbstractGameScreen {

    private static final String TAG = MenuScreen.class.getName();
    private PlayButton playButton;
    private Stage stage;
    private Skin skin;
    private ExitButton exitButton;
    private ContinueButton continueButton;


    public MenuScreen(Game game) {
        super(game);
    }


    private void rebuildStage() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        continueButton = new ContinueButton();
        if (WorldController.mementos.size() == 0) {
            System.out.println("mementos are null");
            continueButton.setDisabled(true);
            continueButton.setBounds(0, 0, 0, 0);
        }
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                continueButton.listener();
            }
        });

        playButton = new PlayButton();
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                playButton.listener();
            }
        });

        exitButton = new ExitButton();
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                exitButton.listener();
            }
        });

        stage.clear();
        stage.addActor(continueButton);
        stage.addActor(playButton);
        stage.addActor(exitButton);
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // if(Gdx.input.isTouched())
        //   game.setScreen(new GameScreen(game));

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


    class ContinueButton extends TextButton {
        ContinueButton() {
            super("Continue", skin);
            setPosition(Constants.VIEWPORT_GUI_WIDTH / 2 - 100, Constants.VIEWPORT_GUI_HEIGHT / 2 + 70);
            setSize(200, 40);
        }

        void listener() {
            game.setScreen(new GameScreen(game));
            Originator.loadFromMemento(WorldController.mementos.get(WorldController.mementos.size() - 1));
        }
    }

    class PlayButton extends TextButton {
        PlayButton() {
            super("New Game", skin);
            setPosition(Constants.VIEWPORT_GUI_WIDTH / 2 - 100, Constants.VIEWPORT_GUI_HEIGHT / 2);
            setSize(200, 40);
        }

        void listener() {
            game.setScreen(new GameScreen(game));
        }
    }

    class ExitButton extends TextButton {
        ExitButton() {
            super("Exit", skin);
            setPosition(Constants.VIEWPORT_GUI_WIDTH / 2 - 100, Constants.VIEWPORT_GUI_HEIGHT / 2 - 70);
            setSize(200, 40);
        }

        void listener() {
            Gdx.app.exit();
        }
    }

}
