package com.gdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxGame extends ApplicationAdapter {
    private static final String TAG = GdxGame.class.getName();
    private WorldController worldController;
    private WorldRenderer worldRenderer;


	@Override
	public void create () {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
	}

	@Override
	public void render () {
        worldController.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(00/255.0f, 149/255.0f, 237/255.0f, 255/255.0f);
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
	}
}
