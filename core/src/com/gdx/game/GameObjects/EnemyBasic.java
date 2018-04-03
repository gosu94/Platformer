package com.gdx.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.game.Assets;

public class EnemyBasic extends Enemy {

    public EnemyBasic() {
        init();
    }

    void init() {
        dimension.set(1f, 1f);
        origin.set(dimension.x / 2, dimension.y / 2);
        bounds.set(0, 0, dimension.x, dimension.y);
        maximalSpeed.set(maxSpeedX, 8.0f);
        acceleration.set(speedX, speedY);
        enemySprite = Assets.instance.player.player;

    }

    public void changeDirection() {
        super.changeDirection();
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
