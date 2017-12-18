package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Clouds extends AbstractGameObject {

    private float length;
    private Array<Cloud> clouds;

//    private class Cloud extends AbstractGameObject {
//        private TextureRegion regCloud;
//
//        public Cloud() {
//        }
//
//        public void setRegion(TextureRegion region) {
//            regCloud = region;
//        }
//
//        @Override
//        public void render(SpriteBatch batch) {
//            TextureRegion reg = regCloud;
//            batch.draw(reg.getTexture(), position.x + origin.x, position.y + origin.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
//        }
//    }


    public Clouds(float length) {
        this.length = length;
        init();
    }

    private void init() {
        dimension.set(2.0f, 1.0f);
        int distFac = 5;
        int numClouds = (int) (length / distFac);
        clouds = new Array<Cloud>(2 * numClouds);
        for (int i = 0; i < numClouds; i++) {
            Cloud cloud = EnvironmentFactroy.createCloud(i * 10, dimension);//spawnCloud();
            cloud.position.x = i * distFac;
            clouds.add(cloud);
        }
    }

//    private Cloud spawnCloud() {
//        Cloud cloud = new Cloud();
//        cloud.dimension.set(dimension);
//        // select random cloud image
//        cloud.setRegion(Assets.instance.levelDecoration.cloud);
//        // position
//        Vector2 pos = new Vector2();
//        pos.x = length + 10;
//        // position after end of level
//        pos.y += 1.75;
//        // base position
//        pos.y += MathUtils.random(0.0f, 0.2f) * (MathUtils.randomBoolean() ? 1 : -1);
//        // random additional position
//        cloud.position.set(pos);
//        return cloud;
//    }

    @Override
    public void render(SpriteBatch batch) {
        for (Cloud cloud : clouds) cloud.render(batch);
    }

}
