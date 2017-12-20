package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyBasic extends Enemy {

    EnemyBasic() {
        init();
    }

    void init() {
        dimension.set(1f, 1f);
        origin.set(dimension.x / 2, dimension.y / 2);
        bounds.set(0, 0, dimension.x, dimension.y);
        maximalSpeed.set(4.0f, 8.0f);
        acceleration.set(speedX, speedY);
        enemySprite = Assets.instance.player.player;

    }

    void changeDirection() {
        speedX = -speedX;
        //zeby sie odbil
        velocity.x = speedX / 2;
        acceleration.set(speedX, speedY);
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
