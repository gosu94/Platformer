package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.io.Serializable;

public class Coin extends AbstractGameObject implements Serializable {

    public boolean collected;
    private TextureRegion regGoldCoin;

    public Coin() {
        init();
    }

    private void init() {
        dimension.set(0.7f, 0.7f);
        setAnimation(Assets.instance.coin.animGoldCoin);
        stateTime = MathUtils.random(0.0f, 1.0f);
        // Set bounding box for collision detection
        bounds.set(0, 0, dimension.x, dimension.y);
        collected = false;
    }

    public void render(SpriteBatch batch) {
        if (collected) return;
        TextureRegion reg = null;
        reg = animation.getKeyFrame(stateTime, true);
        batch.draw(reg.getTexture(),
                position.x, position.y,
                origin.x, origin.y,
                dimension.x, dimension.y,
                scale.x, scale.y,
                rotation,
                reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(),
                false, false);
    }

    public int getScore() {
        return 100;
    }

}
