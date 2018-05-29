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
    private static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    public AssetGoldCoin coin;
    public TextureAtlas atlas;
    public AssetPlayer player;
    public AssetEnemy enemy;
    public AssetRock rock;
    public AssetClover clover;
    public AssetLevelDecoration levelDecoration;

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

    public void init(AssetManager assetManager) {
        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);

        for (String a : assetManager.getAssetNames())
            Gdx.app.debug(TAG, "asset: " + a);

        atlas = new TextureAtlas(Constants.TEXTURE_ATLAS_OBJECTS);//assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        for (Texture t : atlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        coin = new AssetGoldCoin(atlas);
        player = new AssetPlayer(atlas);
        enemy = new AssetEnemy(atlas);
        rock = new AssetRock(atlas);
        clover = new AssetClover(atlas);
        levelDecoration = new AssetLevelDecoration(atlas);
        fonts = new AssetFonts();
    }


    public class AssetPlayer {
        public final AtlasRegion player;
        public final AtlasRegion player2;
        public final Animation runningAnimation;
        public final Animation standingAnimation;

        Array<AtlasRegion> runningRegions = null;
        Array<AtlasRegion> standingRegions = null;

        public AssetPlayer(TextureAtlas atlas) {
            player = atlas.findRegion("player");
            player2 = atlas.findRegion("player2");
            runningRegions = atlas.findRegions("anim_player");
            standingRegions = atlas.findRegions("stand");
            runningAnimation = new Animation(1.0f / 10.0f, runningRegions, Animation.PlayMode.LOOP_PINGPONG);
            standingAnimation = new Animation(1.0f / 12.0f, standingRegions, Animation.PlayMode.LOOP_PINGPONG);
        }
    }

    public class AssetEnemy {

        public final Animation animation;


        Array<AtlasRegion> regions = null;

        public AssetEnemy(TextureAtlas atlas) {
            regions = atlas.findRegions("enemy");
            animation = new Animation(1.0f / 10.0f, regions, Animation.PlayMode.LOOP_PINGPONG);
        }
    }


    public class AssetRock {
        public final AtlasRegion rock;
        public final AtlasRegion rockUnder;

        public AssetRock(TextureAtlas atlas) {
            rock = atlas.findRegion("rock");
            rockUnder = atlas.findRegion("rockUnder");
        }
    }

    public class AssetClover {
        public final AtlasRegion clover;

        public AssetClover(TextureAtlas atlas) {
            clover = atlas.findRegion("clover");
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

    public class AssetGoldCoin {
        public final AtlasRegion goldCoin;
        public final Animation animGoldCoin;

        public AssetGoldCoin(TextureAtlas atlas) {
            goldCoin = atlas.findRegion("item_gold_coin");
            // Animation: Gold Coin
            Array<AtlasRegion> regions =
                    atlas.findRegions("Gold");
            AtlasRegion region = regions.first();
            for (int i = 0; i < 10; i++)
                regions.insert(0, region);
            animGoldCoin = new Animation(1.0f / 20.0f, regions,
                    Animation.PlayMode.LOOP_PINGPONG);
        }
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
