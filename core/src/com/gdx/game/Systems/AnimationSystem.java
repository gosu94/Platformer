package com.gdx.game.Systems;

import com.gdx.game.Components.SpriteComponent;
import com.gdx.game.Entity.Entity;

import java.util.List;

public class AnimationSystem extends System {
    public AnimationSystem(List<Entity> entityList) {
        super(entityList);
    }

    public void update(float deltaTime) {
        for (Entity entity : entityList) {
            if (entity.containsComponent("SpriteComponent")) {
                SpriteComponent spriteComponent = (SpriteComponent) entity.getComponent("SpriteComponent");
                spriteComponent.stateTime += deltaTime;
                if (spriteComponent.stateTime > 20) spriteComponent.stateTime = 0;
            }

        }

    }
}
