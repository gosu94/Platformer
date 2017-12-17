package com.gdx.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;


public class WorldController extends InputAdapter {
    private static final String TAG = WorldController.class.getName();
    CameraHelper cameraHelper;
    Level level;
    int lives;
    int score;

    WorldController() {
        init();
    }

    private void init () {
        Gdx.input.setInputProcessor(this);
        cameraHelper = new CameraHelper();
        lives = Constants.LIVES_START;
        initLevel();
    }

    void initLevel() {
        score = 0;
        level = new Level(Constants.LEVEL_01);
        cameraHelper.setTarget(level.player);
        cameraHelper.setPosition(0, 3);
    }

    void update(float deltaTime) {
        handleDebugInput(deltaTime);
        handleInputGame(deltaTime);
        level.update(deltaTime);
        testCollisionsForPlayer();
        testCollisionForEnemies();
        cameraHelper.update(deltaTime);
    }

    private Rectangle r1 = new Rectangle();
    private Rectangle r2 = new Rectangle();

    private void onCollisionBunnyHeadWithRock(Rock rock) {
        Player player = level.player;
        float heightDifference = Math.abs(player.position.y - (rock.position.y + rock.bounds.height));
        if (heightDifference > 0.25f) {
            boolean hitRightEdge = player.position.x > (rock.position.x + rock.bounds.width / 2.0f);
            if (hitRightEdge)
                player.position.x = rock.position.x + rock.bounds.width;
            else
                player.position.x = rock.position.x - player.bounds.width;
            return;
        }

        switch (player.jumpState) {
            case GROUNDED:
                break;
            case FALLING:
            case JUMP_FALLING:
                player.position.y = rock.position.y + player.bounds.height + player.origin.y;
                player.jumpState = Player.JUMP_STATE.GROUNDED;
                break;
            case JUMP_RISING:
                player.position.y = rock.position.y + player.bounds.height + player.origin.y;
                break;
        }
    }

    private void onCollisionEnemyWithRock(Enemy enemy, Rock rock) {
        float heightDifference = Math.abs(enemy.position.y - (rock.position.y + rock.bounds.height));
        if (heightDifference > 0.25f) {
            boolean hitRightEdge = enemy.position.x > (rock.position.x + rock.bounds.width / 2.0f);
            if (hitRightEdge) {
                enemy.position.x = rock.position.x + rock.bounds.width;
                enemy.changeDirection();
            } else {
                enemy.position.x = rock.position.x - enemy.bounds.width;
                enemy.changeDirection();
            }
            return;
        }

        enemy.position.y = rock.position.y + enemy.bounds.height + enemy.origin.y;
    }

    private void onCollisionBunnyWithGoldCoin(Coin coin) {
        coin.collected = true;
        score += coin.getScore();
        Gdx.app.log(TAG, "Gold coin collected");
    }

    private void testCollisionForEnemies() {
        for (Enemy enemy : level.enemies) {
            r1.set(enemy.position.x, enemy.position.y, enemy.bounds.width, enemy.bounds.height);
            for (Rock rock : level.rocks) {
                r2.set(rock.position.x, rock.position.y, rock.bounds.width, rock.bounds.height);
                if (!r1.overlaps(r2)) continue;
                onCollisionEnemyWithRock(enemy, rock);
            }
        }
    }

    private void testCollisionsForPlayer() {
        r1.set(level.player.position.x, level.player.position.y, level.player.bounds.width, level.player.bounds.height);
        for (Rock rock : level.rocks) {
            r2.set(rock.position.x, rock.position.y, rock.bounds.width, rock.bounds.height);
            if (!r1.overlaps(r2)) continue;
            onCollisionBunnyHeadWithRock(rock);
        }

        for (Coin coin : level.coins) {
            if (coin.collected) continue;
            r2.set(coin.position.x, coin.position.y, coin.bounds.width, coin.bounds.height);
            if (!r1.overlaps(r2)) continue;
            onCollisionBunnyWithGoldCoin(coin);
            break;
        }

    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.R) {
            init();
            Gdx.app.debug(TAG, "World has been resetted");
        } else if (keycode == Keys.ENTER) {
            cameraHelper.setTarget(cameraHelper.hasTarget() ? null : level.player);
            Gdx.app.debug(TAG, "Camera follow enabled: " + cameraHelper.hasTarget());
        }
        if (keycode == Keys.E) {
            Gdx.app.debug(TAG, "Position y: " + level.player.position.y);
        }
        return false;
    }

    private void handleInputGame(float deltaTime) {
        if (cameraHelper.hasTarget(level.player)) {
            if (cameraHelper.hasTarget(level.player)) {
                //movement
                if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                    level.player.velocity.x = -level.player.maximalSpeed.x;
                } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                    level.player.velocity.x = level.player.maximalSpeed.x;
                }

                // Bunny Jump
                if (Gdx.input.isKeyPressed(Keys.SPACE)) {
                    level.player.setJumping(true);
                } else {
                    level.player.setJumping(false);
                }
            }
        }
    }

    private void handleDebugInput(float deltaTime) {
        if (Gdx.app.getType() != ApplicationType.Desktop) return;

        if (!cameraHelper.hasTarget(level.player)) {
            float camMoveSpeed = 5 * deltaTime;
            float camMoveSpeedAccelerationFactor = 5;
            if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) camMoveSpeed *= camMoveSpeedAccelerationFactor;
            if (Gdx.input.isKeyPressed(Keys.LEFT)) moveCamera(-camMoveSpeed, 0);
            if (Gdx.input.isKeyPressed(Keys.RIGHT)) moveCamera(camMoveSpeed, 0);
            if (Gdx.input.isKeyPressed(Keys.UP)) moveCamera(0, camMoveSpeed);
            if (Gdx.input.isKeyPressed(Keys.DOWN)) moveCamera(0, -camMoveSpeed);
            if (Gdx.input.isKeyPressed(Keys.BACKSPACE)) cameraHelper.setPosition(0, 0);
        }


        //Camera zoom
        float camZoomSpeed = 1 * deltaTime;
        float camZoomSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) camZoomSpeed *= camZoomSpeedAccelerationFactor;
        if (Gdx.input.isKeyPressed(Keys.COMMA)) cameraHelper.addZoom(camZoomSpeed);
        if (Gdx.input.isKeyPressed(Keys.PERIOD)) cameraHelper.addZoom(-camZoomSpeed);
        if (Gdx.input.isKeyPressed(Keys.SLASH)) cameraHelper.setZoom(1);

    }

    private void moveCamera(float x, float y) {
        x += cameraHelper.getPosition().x;
        y += cameraHelper.getPosition().y;
        cameraHelper.setPosition(x, y);
    }


}
