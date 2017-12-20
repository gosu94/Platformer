package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.io.Serializable;

abstract class Enemy extends AbstractGameObject implements Serializable {

    private static final String TAG = WorldController.class.getName();
    int speedX = 8;
    int speedY = -25;
    TextureRegion enemySprite;


    Enemy() {
    }

    void init() {
    }

    void changeDirection() {
    }

    int getSpeedX() {
        return speedX;
    }

    void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    int getSpeedY() {
        return speedY;
    }

    void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
}
