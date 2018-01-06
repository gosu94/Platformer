package com.gdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class EnvironmentFactroy {
    public static AbstractGameObject createCloud(String object, int posX, int posY, Vector2 dimension) {

        if ("Cloud".equals(object)) {
            Cloud cloud = new Cloud();
            cloud.dimension.set(dimension);
            cloud.setRegion(Assets.instance.levelDecoration.cloud);
            Vector2 pos = new Vector2();
            pos.x = posX;
            pos.y += 5.75;
            pos.y += MathUtils.random(0.0f, 0.2f) * (MathUtils.randomBoolean() ? 1 : -1);
            cloud.position.set(pos);
            return cloud;
        }

        return null;

    }
}
