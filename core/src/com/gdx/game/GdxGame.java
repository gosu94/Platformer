package com.gdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

public class GdxGame extends ApplicationAdapter {
    private static final String TAG = GdxGame.class.getName();
    private WorldController worldController;
    private WorldRenderer worldRenderer;


	@Override
	public void create () {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Assets.instance.init(new AssetManager());
        worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
	}

	@Override
	public void render () {
        worldController.update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(153 / 255.0f, 217 / 255.0f, 234 / 255.0f, 255 / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render();
	}

	@Override
    public void resize(int width, int height){
        worldRenderer.resize(width, height);
    }
	
	@Override
	public void dispose () {
        worldRenderer.dispose();
        Assets.instance.dispose();
    }
}
