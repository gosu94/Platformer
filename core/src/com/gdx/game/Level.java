package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gdx.game.GameObjects.*;

import java.util.ArrayList;
import java.util.List;

class Level {
    static final String TAG = Level.class.getName();

    static Array<Enemy> enemies;
    static List<Entity> entities;
    static Entity playerEntity;
    static Array<Rock> rocks;
    static Array<Coin> coins;
    Clouds clouds;
    static Player player;
    static float playerBaseX;
    static float playerBaseY;
    static DrawingSystem drawingSystem;
    static MovingSystem movingSystem;
    static CollisionSystem collisionSystem;


    Level(String filename) {
        init(filename);
    }

    private void init(String filename) {
        rocks = new Array<Rock>();
        coins = new Array<Coin>();
        enemies = new Array<Enemy>();
        entities = new ArrayList<>();
        player = null;

        Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
        loadMapFromPicture(pixmap);

        clouds = new Clouds(pixmap.getWidth());
        clouds.position.set(0, 2);

        drawingSystem = new DrawingSystem(entities);
        movingSystem = new MovingSystem(entities);
        collisionSystem = new CollisionSystem(entities);

        pixmap.dispose();
        Gdx.app.debug(TAG, "level '" + filename + "' loaded");
    }

    public void render(SpriteBatch batch) {

        //for (Rock rock : rocks)
        //     rock.render(batch);
        for (Coin coin : coins)
            coin.render(batch);
        //for (Enemy enemy : enemies)
        //    enemy.render(batch);

        drawingSystem.update(batch);

        clouds.render(batch);
        //player.render(batch);

    }

    void update(float deltaTime) {
        //player.update(deltaTime);

//        for (Rock rock : rocks)
//            rock.update(deltaTime);
        for (Coin coin : coins)
            coin.update(deltaTime);
        //for (Enemy enemy : enemies)
        //    enemy.update(deltaTime);
        movingSystem.update(deltaTime);
        collisionSystem.update();

        clouds.update(deltaTime);
    }

    void loadMapFromPicture(Pixmap pixmap) {
        //scan pixels from top-left to bottom-right
        int lastPixel = -1;
        for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {

            for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
                AbstractGameObject obj = null;
                float offsetHeight = 0;
                //height grows from bottom to top
                float baseHeight = pixmap.getHeight() - pixelY;
                int currentPixel = pixmap.getPixel(pixelX, pixelY);

                if (BLOCK_TYPE.EMPTY.sameColor(currentPixel)) {
                    // do nothing
                } else if (BLOCK_TYPE.ROCK_UNDER.sameColor(currentPixel)) {

//                    if (lastPixel != currentPixel) {
//                        obj = new Rock(true);
//                        offsetHeight = -4f;
//                        obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
//                        rocks.add((Rock) obj);
//                    } else {
//                        rocks.get(rocks.size - 1).increaseLength(1);
//                    }

                } else if (BLOCK_TYPE.ROCK.sameColor(currentPixel)) {
                    offsetHeight = -4f;
                    Entity rock = new Entity("Rock");
                    Rectangle bounds = new Rectangle(0, 0, 1, 1);
                    Vector2 position = new Vector2(pixelX, baseHeight + offsetHeight);

                    rock.addComponent(new BoundsComponent(position, bounds));
                    rock.addComponent(new SpriteComponent(Assets.instance.rock.rock));
                    rock.addComponent(new CollisionComponent());

                    entities.add(rock);

//                    if (lastPixel != currentPixel) {
//                        obj = new Rock(false);
//                        obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
//                        rocks.add((Rock) obj);
//                    } else {
//                        rocks.get(rocks.size - 1).increaseLength(1);
//                    }

                } else if (BLOCK_TYPE.PLAYER_SPAWNPOINT.sameColor(currentPixel)) {
                    offsetHeight = -4.0f;

                    Entity player = new Entity("Player");
                    Vector2 velocity = new Vector2(0, 0);
                    Vector2 maximalSpeed = new Vector2(4, 9);
                    Vector2 friction = new Vector2(12f, 0);
                    Vector2 acceleration = new Vector2(0, -25f);
                    Rectangle bounds = new Rectangle(0, 0, 1, 1);

                    player.addComponent(new BoundsComponent(new Vector2(pixelX, baseHeight + offsetHeight), bounds));
                    player.addComponent(new VelocityComponent(velocity, maximalSpeed, friction, acceleration));
                    player.addComponent(new SpriteComponent(Assets.instance.player.animation));
                    player.addComponent(new CollisionComponent());
                    player.addComponent(new JumpComponent());

                    playerEntity = player;
                    entities.add(player);

//                    obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
//                    playerBaseX = pixelX;
//                    playerBaseY = baseHeight * obj.dimension.y + offsetHeight;
//                    player = (Player) obj;

                } else if (BLOCK_TYPE.ITEM_GOLD_COIN.sameColor(currentPixel)) {

                    obj = new Coin();
                    offsetHeight = -3.2f;
                    obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
                    coins.add((Coin) obj);

                } else if (BLOCK_TYPE.ENEMY.sameColor(currentPixel)) {
                    //obj = new EnemyBasic();
                    // offsetHeight = -4f;
                    // obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
                    // enemies.add((Enemy) obj);

                    Entity enemy = new Entity("Enemy");

                    Vector2 velocity = new Vector2(4, 1);
                    Vector2 maximalSpeed = new Vector2(4, 8);
                    Vector2 friction = new Vector2();
                    Vector2 acceleration = new Vector2(8, -25);

                    Rectangle bounds = new Rectangle(0, 0, 1, 1);

                    enemy.addComponent(new BoundsComponent(new Vector2(pixelX, baseHeight + offsetHeight), bounds));
                    enemy.addComponent(new VelocityComponent(velocity, maximalSpeed, friction, acceleration));
                    enemy.addComponent(new SpriteComponent(Assets.instance.player.player));
                    enemy.addComponent(new CollisionComponent());

                    entities.add(enemy);

                } else {

                    int r = 0xff & (currentPixel >>> 24);
                    int g = 0xff & (currentPixel >>> 16);
                    int b = 0xff & (currentPixel >>> 8);
                    int a = 0xff & currentPixel;
                    Gdx.app.error(TAG, "Unknown object at x<" + pixelX + "> y<" + pixelY + ">: r<" + r + "> g<" + g + "> b<" + b + "> a<" + a + ">");
                }

                lastPixel = currentPixel;

            }
        }
    }


    public enum BLOCK_TYPE {
        EMPTY(255, 255, 255),
        ROCK(0, 255, 0),
        ROCK_UNDER(0, 240, 0),
        PLAYER_SPAWNPOINT(0, 0, 0),
        ITEM_GOLD_COIN(255, 255, 0),
        ENEMY(128, 128, 128);

        private int color;

        BLOCK_TYPE(int r, int g, int b) {
            color = r << 24 | g << 16 | b << 8 | 0xff;
        }

        public boolean sameColor(int color) {
            return this.color == color;
        }

        public int getColor() {
            return color;
        }
    }


}
