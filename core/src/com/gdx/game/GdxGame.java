package com.gdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.gdx.game.GameScreens.MenuScreen;
import com.gdx.game.Memento.Originator;

import java.io.File;

public class GdxGame extends Game {
    @Override
    public void create() {
        // Set Libgdx log level
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        // Load assets
        Assets.instance.init(new AssetManager());
        File f = new File("mementos.obj");
        if (f.exists() && !f.isDirectory()) {
            Globals.mementos = Originator.loadMementos();
        }

        // Start game at menu screen
        setScreen(new MenuScreen(this));
    }
}
