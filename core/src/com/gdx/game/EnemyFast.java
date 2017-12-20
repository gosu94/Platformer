package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyFast extends Enemy {

    private Enemy baseEnemy;

    EnemyFast(Enemy baseEnemy) {
        this.baseEnemy = baseEnemy;
        baseEnemy.setSpeedX(baseEnemy.getSpeedX() * 2);
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
        batch.setColor(1.0f, 0.8f, 0.0f, 1.0f);
        baseEnemy.render(batch);
        batch.setColor(1, 1, 1, 1);
    }

}
