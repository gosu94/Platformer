package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends AbstractGameObject {

    private static final String TAG = WorldController.class.getName();
    int SPEED_X = 8;
    int SPEED_Y = -25;
    private TextureRegion enemySprite;

    Enemy() {
        init();
    }

    void init() {
        dimension.set(1f, 1f);
        origin.set(dimension.x / 2, dimension.y / 2);
        bounds.set(0, 0, dimension.x, dimension.y);
        maximalSpeed.set(4.0f, 8.0f);
        acceleration.set(SPEED_X, SPEED_Y);
        enemySprite = Assets.instance.player.player;

    }

    public void changeDirection() {
        SPEED_X = -SPEED_X;
        //zeby sie odbil
        velocity.x = SPEED_X / 2;
        acceleration.set(SPEED_X, SPEED_Y);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = enemySprite;
        batch.draw(reg.getTexture(), position.x, position.y,
                origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y,
                rotation, reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(), false, false);

    }
}
