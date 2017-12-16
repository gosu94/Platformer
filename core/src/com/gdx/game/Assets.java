package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {
    static final String TAG = Assets.class.getName();
    static final Assets instance = new Assets();
    TextureAtlas atlas;
    AssetCoin coin;
    AssetPlayer player;
    AssetRock rock;
    AssetLevelDecoration levelDecoration;

    private Assets() {
    }

    public AssetFonts fonts;

    @Override
    public void dispose() {
        atlas.dispose();
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
        public final Animation animation;

        Array<AtlasRegion> regions = null;
        AtlasRegion region = null;



        public AssetPlayer(TextureAtlas atlas) {
            player = atlas.findRegion("player");
            regions = atlas.findRegions("anim_player");
            animation = new Animation(1.0f / 10.0f, regions, Animation.PlayMode.LOOP_PINGPONG);
        }
    }

    public class AssetCoin {
        public final AtlasRegion coin;

        public AssetCoin(TextureAtlas atlas) {
            coin = atlas.findRegion("coin");
        }
    }

    public class AssetRock {
        public final AtlasRegion rock;

        public AssetRock(TextureAtlas atlas) {
            rock = atlas.findRegion("rock");
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

    void init(AssetManager assetManager) {
        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);

        for (String a : assetManager.getAssetNames())
            Gdx.app.debug(TAG, "asset: " + a);

        atlas = new TextureAtlas(Constants.TEXTURE_ATLAS_OBJECTS);//assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        for (Texture t : atlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        coin = new AssetCoin(atlas);
        player = new AssetPlayer(atlas);
        rock = new AssetRock(atlas);
        levelDecoration = new AssetLevelDecoration(atlas);
        fonts = new AssetFonts();
    }

    public class AssetFonts {
        public final BitmapFont defaultSmall;
        public final BitmapFont defaultNormal;
        public final BitmapFont defaultBig;

        AssetFonts() {
            // create three fonts using Libgdx's 15px bitmap font
            defaultSmall = new BitmapFont(
                    Gdx.files.internal("fonts/gabriela.fnt"), true);
            defaultNormal = new BitmapFont(
                    Gdx.files.internal("fonts/gabriela.fnt"), true);
            defaultBig = new BitmapFont(
                    Gdx.files.internal("fonts/gabriela.fnt"), true);
            // set font sizes
            defaultSmall.getData().setScale(0.75f);
            defaultNormal.getData().setScale(1.0f);
            defaultBig.getData().setScale(2.0f);
            // enable linear texture filtering for smooth fonts
            defaultSmall.getRegion().getTexture().setFilter(
                    TextureFilter.Linear, TextureFilter.Linear);
            defaultNormal.getRegion().getTexture().setFilter(
                    TextureFilter.Linear, TextureFilter.Linear);
            defaultBig.getRegion().getTexture().setFilter(
                    TextureFilter.Linear, TextureFilter.Linear);
        }
    }


}
