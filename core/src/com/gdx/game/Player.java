package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends AbstractGameObject {

    public static final String TAG = Player.class.getName();
    private final float JUMP_TIME_MAX = 0.3f;
    private final float JUMP_TIME_MIN = 0.1f;
    private final float JUMP_TIME_OFFSET_FLYING =
            JUMP_TIME_MAX - 0.018f;
    private final float SPEED = 4.0f;
    private final float JUMP_SPEED = 6.0f;
    private VIEW_DIRECTION viewDirection;
    private float timeJumping;
    public JUMP_STATE jumpState;

    Player() {
        init();
    }

    void init() {
        dimension.set(1f, 1f);
        origin.set(dimension.x / 2, dimension.y / 2);
        bounds.set(0, 0, dimension.x, dimension.y);
        maximalSpeed.set(SPEED, JUMP_SPEED);
        friction.set(12.0f, 0.0f);
        acceleration.set(0f, -25.0f);
        viewDirection = VIEW_DIRECTION.RIGHT;
        jumpState = JUMP_STATE.FALLING;
        timeJumping = 0;
        setAnimation(Assets.instance.player.animation);
        stateTime = 0;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (velocity.x != 0) {
            viewDirection = velocity.x < 0 ? VIEW_DIRECTION.LEFT :
                    VIEW_DIRECTION.RIGHT;
        }

        if (position.y < -4) position.y = 7;
    }

    @Override
    protected void updateMotionX(float deltaTime) {

        super.updateMotionX(deltaTime);
    }

    @Override
    protected void updateMotionY(float deltaTime) {

        switch (jumpState) {
            case GROUNDED:
                jumpState = JUMP_STATE.FALLING;
                break;
            case JUMP_RISING:
                // Keep track of jump time
                timeJumping += deltaTime;
                // Jump time left?
                if (timeJumping <= JUMP_TIME_MAX) {
                    // Still jumping
                    velocity.y = maximalSpeed.y;
                }
                // Jump to minimal height if jump key was pressed too short
                if (timeJumping > 0 && timeJumping <= JUMP_TIME_MIN) {
                    // Still jumping
                    velocity.y = maximalSpeed.y;
                }
                break;
            case FALLING:
                break;
            case JUMP_FALLING:
                // Add delta times to track jump time
                timeJumping += deltaTime;


        }

        super.updateMotionY(deltaTime);

    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = null;
        // Set special color when game object has a feather power-up
        // Draw image
        reg = animation.getKeyFrame(stateTime, true);

        batch.draw(reg.getTexture(), position.x, position.y, origin.x,
                origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
                reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                reg.getRegionHeight(), viewDirection == VIEW_DIRECTION.LEFT,
                false);
        // Reset color to white
        batch.setColor(1, 1, 1, 1);
    }

    public void setJumping(boolean jumpKeyPressed) {
        switch (jumpState) {
            case GROUNDED: // Character is standing on a platform
                if (jumpKeyPressed) {
                    // Start counting jump time from the beginning
                    timeJumping = 0;
                    jumpState = JUMP_STATE.JUMP_RISING;
                }
                break;
            case JUMP_RISING: // Rising in the air
                if (!jumpKeyPressed)
                    jumpState = JUMP_STATE.JUMP_FALLING;
                break;
            case FALLING:// Falling down
            case JUMP_FALLING: // Falling down after jump
                break;
        }
    }

    public enum VIEW_DIRECTION {LEFT, RIGHT}

    public enum JUMP_STATE {
        GROUNDED, FALLING, JUMP_RISING, JUMP_FALLING
    }
}
