package com.gdx.game.Interpreter;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.Assets;
import com.gdx.game.Components.*;
import com.gdx.game.Entity.Entity;
import com.gdx.game.Globals;
import com.gdx.game.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelParser {

    public static void interpret(Pixmap pixmap) {

        List<Pixel> availablePixels = new ArrayList<>();
        availablePixels.add(new RockPixel(PIXEL_TYPE.ROCK));
        availablePixels.add(new PlayerPixel(PIXEL_TYPE.PLAYER_SPAWNPOINT));
        availablePixels.add(new CoinPixel(PIXEL_TYPE.ITEM_GOLD_COIN));
        availablePixels.add(new EnemyPixel(PIXEL_TYPE.ENEMY));

        for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {

            for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
                //height grows from bottom to top
                float baseHeight = pixmap.getHeight() - pixelY;
                int currentPixel = pixmap.getPixel(pixelX, pixelY);

                for (Pixel pixel : availablePixels) {
                    if (pixel.getPixelType().sameColor(currentPixel))
                        pixel.interpret(pixelX, baseHeight);
                }


            }
        }
    }


    public enum PIXEL_TYPE {
        EMPTY(255, 255, 255),
        ROCK(0, 255, 0),
        ROCK_UNDER(0, 240, 0),
        PLAYER_SPAWNPOINT(0, 0, 0),
        ITEM_GOLD_COIN(255, 255, 0),
        ENEMY(128, 128, 128);

        private int color;

        PIXEL_TYPE(int r, int g, int b) {
            color = r << 24 | g << 16 | b << 8 | 0xff;
        }

        public boolean sameColor(int color) {
            return this.color == color;
        }

        public int getColor() {
            return color;
        }
    }

    static class RockPixel extends PixelData {

        public RockPixel(PIXEL_TYPE blockType) {
            super(blockType);
        }

        @Override
        public void interpret(int pixelX, float baseHeight) {
            float offsetHeight = -4f;
            Entity rock = new Entity("Rock");
            Vector2 position = new Vector2(pixelX, baseHeight + offsetHeight);

            rock.addComponent(new BoundsComponent(position, 1, 1));
            rock.addComponent(new SpriteComponent(Assets.instance.rock.rock));
            rock.addComponent(new CollisionComponent());

            Level.entities.add(rock);
        }
    }

    static class PlayerPixel extends PixelData {

        public PlayerPixel(PIXEL_TYPE blockType) {
            super(blockType);
        }

        @Override
        public void interpret(int pixelX, float baseHeight) {
            float offsetHeight = -4.0f;

            Entity player = new Entity("Player");
            Vector2 velocity = new Vector2(0, 0);
            Vector2 maximalSpeed = new Vector2(4, 9);
            Vector2 friction = new Vector2(12f, 0);
            Vector2 acceleration = new Vector2(0, -25f);
            Globals.startingPoint = new Vector2(pixelX, baseHeight + offsetHeight);
            player.addComponent(new BoundsComponent(new Vector2(pixelX, baseHeight + offsetHeight), 1, 1));
            player.addComponent(new VelocityComponent(velocity, maximalSpeed, friction, acceleration));
            player.addComponent(new SpriteComponent(Assets.instance.player.animation));
            player.addComponent(new CollisionComponent());
            player.addComponent(new JumpComponent());

            Level.playerEntity = player;
            Level.entities.add(player);
        }
    }

    static class CoinPixel extends PixelData {

        public CoinPixel(PIXEL_TYPE blockType) {
            super(blockType);
        }

        @Override
        public void interpret(int pixelX, float baseHeight) {
            float offsetHeight = -3.2f;

            Entity coin = new Entity("Coin");

            coin.addComponent(new BoundsComponent(new Vector2(pixelX, baseHeight + offsetHeight), 0.75f, 0.75f));
            coin.addComponent(new SpriteComponent(Assets.instance.coin.animGoldCoin));
            coin.addComponent(new CollisionComponent());

            Level.entities.add(coin);
        }
    }

    static class EnemyPixel extends PixelData {

        public EnemyPixel(PIXEL_TYPE blockType) {
            super(blockType);
        }

        @Override
        public void interpret(int pixelX, float baseHeight) {

            float offsetHeight = 1;

            Entity enemy = new Entity("Enemy");

            Vector2 velocity = new Vector2(4, 1);
            Vector2 maximalSpeed = new Vector2(4, 8);
            Vector2 friction = new Vector2();
            Vector2 acceleration = new Vector2(8, -25);

            enemy.addComponent(new BoundsComponent(new Vector2(pixelX, baseHeight + offsetHeight), 1, 1));
            enemy.addComponent(new VelocityComponent(velocity, maximalSpeed, friction, acceleration));
            enemy.addComponent(new SpriteComponent(Assets.instance.player.player));
            enemy.addComponent(new CollisionComponent());

            Level.entities.add(enemy);
        }
    }


}
