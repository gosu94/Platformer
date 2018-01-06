package com.gdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Clouds extends AbstractGameObject {

    private float length;
    private Array<Cloud> clouds;

    public Clouds(float length) {
        this.length = length;
        fillLevel();
    }

    private void fillLevel() {
        dimension.set(2.0f, 1.0f);
        int distFac = 5;
        int numClouds = (int) (length / distFac);
        clouds = new Array<Cloud>(2 * numClouds);
        for (int i = 0; i < numClouds; i++) {
            Cloud cloud = (Cloud) EnvironmentFactroy.createCloud("Cloud", i * 10, 0, dimension);//spawnCloud();
            cloud.position.x = i * distFac;
            clouds.add(cloud);
        }
    }


    @Override
    public void render(SpriteBatch batch) {
        for (Cloud cloud : clouds) cloud.render(batch);
    }

}
