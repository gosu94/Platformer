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
        availablePixels.add(new CloverPixel(PIXEL_TYPE.ITEM_CLOVER));

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

        smoothGround();
    }

    public static void smoothGround() {

        boolean isUnder = false;
        for (Entity entity : Level.entities) {
            if ("Rock".equals(entity.getName())) {
                isUnder = false;
                BoundsComponent bounds = (BoundsComponent) entity.getComponent("BoundsComponent");
                Vector2 rockPos = bounds.position;
                for (Entity entity2 : Level.entities) {
                    if ("Rock".equals(entity2.getName())) {
                        BoundsComponent bounds2 = (BoundsComponent) entity2.getComponent("BoundsComponent");
                        Vector2 rockPos2 = bounds2.position;
                        if (rockPos.y == rockPos2.y - 1 && rockPos.x == rockPos2.x) {
                            isUnder = true;
                            break;
                        }
                    }
                }
                if (isUnder) {
                    SpriteComponent spriteComponent = (SpriteComponent) entity.getComponent("SpriteComponent");
                    entity.removeComponent("SpriteComponent");
                    entity.addComponent(new SpriteComponent(Assets.instance.rock.rockUnder));

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
        ITEM_CLOVER(255, 0, 255),
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

    public static class RockPixel extends PixelData {

        public RockPixel(PIXEL_TYPE blockType) {
            super(blockType);
        }

        public static void addComponents(Entity entity, float x, float y) {
            Vector2 position = new Vector2(x, y);

            entity.addComponent(new BoundsComponent(position, 1, 1));
            entity.addComponent(new SpriteComponent(Assets.instance.rock.rock));
            entity.addComponent(new CollisionComponent());
        }

        @Override
        public void interpret(int pixelX, float baseHeight) {
            float offsetHeight = -4f;
            Entity rock = new Entity("Rock");
            addComponents(rock, pixelX, baseHeight + offsetHeight);
            Level.entities.add(rock);
        }

    }

    public static class CloverPixel extends PixelData {

        public CloverPixel(PIXEL_TYPE blockType) {
            super(blockType);
        }

        public static void addComponents(Entity entity, float x, float y) {
            Vector2 position = new Vector2(x, y);

            entity.addComponent(new BoundsComponent(position, 1, 1));
            entity.addComponent(new SpriteComponent(Assets.instance.clover.clover));
            entity.addComponent(new CollisionComponent());
        }

        @Override
        public void interpret(int pixelX, float baseHeight) {
            float offsetHeight = -4f;
            Entity rock = new Entity("Clover");
            addComponents(rock, pixelX, baseHeight + offsetHeight);
            Level.entities.add(rock);
        }

    }

    public static class PlayerPixel extends PixelData {

        public PlayerPixel(PIXEL_TYPE blockType) {
            super(blockType);
        }

        public static void addComponents(Entity entity, float x, float y) {
            Vector2 velocity = new Vector2(0, 0);
            Vector2 maximalSpeed = new Vector2(4, 9);
            Vector2 friction = new Vector2(12f, 0);
            Vector2 acceleration = new Vector2(0, -25f);
            Globals.startingPoint = new Vector2(x, y);
            entity.addComponent(new VelocityComponent(velocity, maximalSpeed, friction, acceleration));
            entity.addComponent(new BoundsComponent(new Vector2(x, y), 1, 1));
            entity.addComponent(new SpriteComponent(Assets.instance.player.runningAnimation, Assets.instance.player.standingAnimation));
            entity.addComponent(new CollisionComponent());
            entity.addComponent(new JumpComponent());
        }

        @Override
        public void interpret(int pixelX, float baseHeight) {
            float offsetHeight = -4.0f;

            Entity player = new Entity("Player");
            addComponents(player, pixelX, baseHeight + offsetHeight);

            Level.playerEntity = player;
            Level.entities.add(player);
        }
    }

    public static class CoinPixel extends PixelData {

        public CoinPixel(PIXEL_TYPE blockType) {
            super(blockType);
        }

        public static void addComponents(Entity entity, float x, float y) {
            entity.addComponent(new BoundsComponent(new Vector2(x, y), 0.75f, 0.75f));
            entity.addComponent(new SpriteComponent(Assets.instance.coin.animGoldCoin));
            entity.addComponent(new CollisionComponent());
        }

        @Override
        public void interpret(int pixelX, float baseHeight) {
            float offsetHeight = -3.2f;

            Entity coin = new Entity("Coin");
            addComponents(coin, pixelX, baseHeight + offsetHeight);

            Level.entities.add(coin);
        }
    }

    public static class EnemyPixel extends PixelData {

        public EnemyPixel(PIXEL_TYPE blockType) {
            super(blockType);
        }

        public static void addComponents(Entity entity, float x, float y) {
            Vector2 velocity = new Vector2(4, 1);
            Vector2 maximalSpeed = new Vector2(4, 8);
            Vector2 friction = new Vector2();
            Vector2 acceleration = new Vector2(8, -25);

            entity.addComponent(new BoundsComponent(new Vector2(x, y), 1, 1));
            entity.addComponent(new VelocityComponent(velocity, maximalSpeed, friction, acceleration));
            entity.addComponent(new SpriteComponent(Assets.instance.enemy.animation));
            entity.addComponent(new CollisionComponent());
        }

        @Override
        public void interpret(int pixelX, float baseHeight) {

            float offsetHeight = 1;

            Entity enemy = new Entity("Enemy");
            addComponents(enemy, pixelX, baseHeight + offsetHeight);

            Level.entities.add(enemy);
        }
    }


}
