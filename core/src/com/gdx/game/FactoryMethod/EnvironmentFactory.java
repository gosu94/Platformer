package com.gdx.game.FactoryMethod;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.Assets;
import com.gdx.game.Components.BoundsComponent;
import com.gdx.game.Components.SpriteComponent;
import com.gdx.game.Entity.Entity;

public class EnvironmentFactory {
    public static Entity createEntity(String name, int posX, int posY) {

        if ("Cloud".equals(name)) {

            Entity cloud = new Entity("Cloud");
            float height = 6 + MathUtils.random(0.0f, 1f) * (MathUtils.randomBoolean() ? 1 : -1);
            cloud.addComponent(new BoundsComponent(new Vector2(posX, height), 2f, 1));
            cloud.addComponent(new SpriteComponent(Assets.instance.levelDecoration.cloud));

            return cloud;
        }

        return null;

    }
}
