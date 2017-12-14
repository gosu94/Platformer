package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

class Level {
    static final String TAG = Level.class.getName();

    public enum BLOCK_TYPE {
        EMPTY(255, 255, 255),
        ROCK(0, 255, 0),    //green
        PLAYER_SPAWNPOINT(0, 0, 0),
        ITEM_GOLD_COIN(255, 255, 0);

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

    Array<Rock> rocks;
    Clouds clouds;

    Level(String filename) {
        init(filename);
    }

    private void init(String filename) {
        rocks = new Array<Rock>();
        Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
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
                } else if (BLOCK_TYPE.ROCK.sameColor(currentPixel)) {
                    if (lastPixel != currentPixel) {
                        obj = new Rock();
                        float heightIncreaseFactor = 0.25f;
                        offsetHeight = -2.5f;
                        obj.position.set(pixelX, baseHeight * obj.dimension.y * heightIncreaseFactor + offsetHeight);
                        rocks.add((Rock) obj);
                    } else {
                        rocks.get(rocks.size - 1).increaseLength(1);
                    }
                } else if (BLOCK_TYPE.PLAYER_SPAWNPOINT.sameColor(currentPixel)) {

                } else if (BLOCK_TYPE.ITEM_GOLD_COIN.sameColor(currentPixel)) {

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

        clouds = new Clouds(pixmap.getWidth());
        clouds.position.set(0, 2);

        pixmap.dispose();
        Gdx.app.debug(TAG, "level '" + filename + "' loaded");
    }

    public void render(SpriteBatch batch) {
        for (Rock rock : rocks) {
            rock.render(batch);
            clouds.render(batch);
        }
    }


}