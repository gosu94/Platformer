package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.GameObjects.Coin;
import com.gdx.game.GameObjects.Enemy;
import com.gdx.game.GameObjects.Player;
import com.gdx.game.GameObjects.Rock;

public class CollisionHandler {

    private static final String TAG = WorldController.class.getName();
    private static int score;


    private Rectangle r1 = new Rectangle();
    private Rectangle r2 = new Rectangle();

    void testCollisionForEnemies() {
        for (Enemy enemy : Level.enemies) {
            r1.set(enemy.position.x, enemy.position.y, enemy.bounds.width, enemy.bounds.height);
            for (Rock rock : Level.rocks) {
                r2.set(rock.position.x, rock.position.y, rock.bounds.width, rock.bounds.height);
                if (!r1.overlaps(r2)) continue;
                onCollisionEnemyWithRock(enemy, rock);
            }
        }
    }

    void testCollisionsForPlayer() {
        r1.set(Level.player.position.x, Level.player.position.y, Level.player.bounds.width, Level.player.bounds.height);

        for (Rock rock : Level.rocks) {
            r2.set(rock.position.x, rock.position.y, rock.bounds.width, rock.bounds.height);
            if (!r1.overlaps(r2)) continue;
            onCollisionPlayerWithRock(rock);
        }

        for (Enemy enemy : Level.enemies) {
            r2.set(enemy.position.x, enemy.position.y, enemy.bounds.width, enemy.bounds.height);
            if (!r1.overlaps(r2)) continue;
            onCollisionPlayerWithEnemy(Level.player);
        }

        for (Coin coin : Level.coins) {
            if (coin.collected) continue;
            r2.set(coin.position.x, coin.position.y, coin.bounds.width, coin.bounds.height);
            if (!r1.overlaps(r2)) continue;
            onCollisionPlayerWithGoldCoin(coin);
            break;
        }

    }

    private void onCollisionPlayerWithRock(Rock rock) {
        Player player = Level.player;
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
                player.position.y = rock.position.y + player.bounds.height;
                player.jumpState = Player.JUMP_STATE.GROUNDED;
                break;
            case JUMP_RISING:
                player.position.y = rock.position.y + player.bounds.height;
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

        enemy.position.y = rock.position.y + enemy.bounds.height;
    }

    private void onCollisionPlayerWithGoldCoin(Coin coin) {
        coin.collected = true;
        score += coin.getScore();
        Gdx.app.log(TAG, "Gold coin collected");
    }

    private void onCollisionPlayerWithEnemy(Player player) {
        player.setPosition(new Vector2(Level.playerBaseX, Level.playerBaseY));
        Gdx.app.log(TAG, "Life lost");
    }

}
