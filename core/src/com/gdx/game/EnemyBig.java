package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyBig extends Enemy {

    private Enemy baseEnemy;

    EnemyBig(Enemy baseEnemy) {
        this.baseEnemy = baseEnemy;
        baseEnemy.dimension.set(2.0f, 2.0f);
    }

    @Override
    public void update(float deltaTime) {
        baseEnemy.update(deltaTime);
    }

    @Override
    public void setAnimation(Animation animation) {
        baseEnemy.setAnimation(animation);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setColor(0f, 0.8f, 0.0f, 1.0f);
        baseEnemy.render(batch);
        batch.setColor(1, 1, 1, 1);
    }
}
