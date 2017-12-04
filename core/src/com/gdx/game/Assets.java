package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {
    static final String TAG = Assets.class.getName();
    static final Assets instance = new Assets();
    private AssetManager assetManager;
    AssetCoin coin;
    AssetPlayer player;
    AssetLevelDecoration levelDecoration;

    private Assets() {
    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        assetManager.finishLoading();
        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
        for (String a : assetManager.getAssetNames())
            Gdx.app.debug(TAG, "asset: " + a);

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        coin = new AssetCoin(atlas);
        player = new AssetPlayer(atlas);
        levelDecoration = new AssetLevelDecoration(atlas);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public void error(String filename, Class type, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + filename + "'", throwable);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", throwable);
    }

    public class AssetPlayer {
        public final AtlasRegion player;

        public AssetPlayer(TextureAtlas atlas) {
            player = atlas.findRegion("player");
        }
    }

    public class AssetCoin {
        public final AtlasRegion coin;

        public AssetCoin(TextureAtlas atlas) {
            coin = atlas.findRegion("coin");
        }
    }

    public class AssetLevelDecoration {
        public final AtlasRegion cloud;
        public final AtlasRegion bush;

        public AssetLevelDecoration(TextureAtlas atlas) {
            cloud = atlas.findRegion("cloud");
            bush = atlas.findRegion("bush");
        }
    }


}
