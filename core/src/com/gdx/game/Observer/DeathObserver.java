package com.gdx.game.Observer;

import com.gdx.game.Constants;
import com.gdx.game.GameScreens.GameOverScreen;
import com.gdx.game.Globals;

import static com.gdx.game.GameScreens.AbstractGameScreen.game;


public class DeathObserver implements Observer {
    @Override
    public void update() {
        if (Globals.lives == 0) {

            game.setScreen(new GameOverScreen(game));
            Globals.lives = Constants.LIVES_START;
            Globals.points = 0;
        }
    }
}
