package com.gdx.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.game.WorldController;

import java.io.Serializable;

abstract public class Enemy extends AbstractGameObject implements Serializable {

    private static final String TAG = WorldController.class.getName();
    int speedX = 8;
    int speedY = -25;
    int maxSpeedX = 4;
    TextureRegion enemySprite;


    Enemy() {
    }

    void init() {

    }

    public void changeDirection() {
        speedX = -speedX;
        //zeby sie odbil
        velocity.x = speedX / 2;
        acceleration.set(speedX, speedY);
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

    public int getMaxSpeedX() {
        return maxSpeedX;
    }

    public void setMaxSpeedX(int maxSpeedX) {
        this.maxSpeedX = maxSpeedX;
    }
}
