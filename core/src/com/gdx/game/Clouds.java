package com.gdx.game;

import com.gdx.game.Entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Clouds {

    private float length;
    private List<Entity> clouds;

    public Clouds(float length) {
        this.length = length;
        fillLevel();
    }

    private void fillLevel() {
        int distFac = 2;
        int numClouds = (int) (length / distFac);
        clouds = new ArrayList<Entity>(2 * numClouds);

        for (int i = 0; i < numClouds; i++) {
            Entity cloud = EnvironmentFactory.createEntity("Cloud", i * 10, 0);
            clouds.add(cloud);
        }
        Level.entities.addAll(clouds);
    }


}
