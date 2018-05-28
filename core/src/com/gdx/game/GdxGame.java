package com.gdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.gdx.game.GameScreens.MenuScreen;

public class GdxGame extends Game {
    @Override
    public void create() {
        // Set Libgdx log level
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        // Load assets
        Assets.instance.init(new AssetManager());
        Globals.mementos = Originator.loadMementos();
        // Start game at menu screen
        setScreen(new MenuScreen(this));
    }
}
