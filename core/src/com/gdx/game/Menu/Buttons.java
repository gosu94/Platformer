package com.gdx.game.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.gdx.game.Constants;
import com.gdx.game.GameScreens.GameScreen;
import com.gdx.game.GameScreens.MenuScreen;

import static com.gdx.game.GameScreens.AbstractGameScreen.game;

public class Buttons {

    public static class ContinueButton extends Button {
        public ContinueButton() {
            super("Continue", MenuScreen.skin);
            setBounds();
        }

        @Override
        void listener() {
            game.setScreen(new GameScreen(game));
            // Originator.loadFromMemento(WorldController.mementos.get(WorldController.mementos.size() - 1));
        }

        @Override
        void setBounds() {
            super.setBounds();
            setPosition(Constants.VIEWPORT_GUI_WIDTH / 2 - 100, Constants.VIEWPORT_GUI_HEIGHT / 2 + 70);
            setSize(200, 40);
        }
    }

    public static class PlayButton extends Button {

        public PlayButton() {
            super("New Game", MenuScreen.skin);
            setBounds();
            addAction(Actions.fadeIn(10));
            //setDisabled(true);
        }

        @Override
        void listener() {
            game.setScreen(new GameScreen(game));
        }

        @Override
        void setBounds() {
            setPosition(Constants.VIEWPORT_GUI_WIDTH / 2 - 100, Constants.VIEWPORT_GUI_HEIGHT / 2);
            setSize(200, 40);
        }


    }

    public static class ExitButton extends Button {
        public ExitButton() {
            super("Exit", MenuScreen.skin);
            setBounds();
        }

        @Override
        void listener() {
            Gdx.app.exit();
        }

        @Override
        void setBounds() {
            setPosition(Constants.VIEWPORT_GUI_WIDTH / 2 - 100, Constants.VIEWPORT_GUI_HEIGHT / 2 - 70);
            setSize(200, 40);
        }
    }


}
