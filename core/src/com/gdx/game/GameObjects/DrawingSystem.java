package com.gdx.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.List;

public class DrawingSystem extends System {

    DrawingSystem(List<Entity> entityList) {
        super(entityList);
    }

    void update(Batch batch, float deltaTime) {
        for (Entity entity : entityList) {
            if (containsComponent(entity.componentList, "SpriteComponent")) {
                BoundsComponent bounds = (BoundsComponent) getComponentOfEntity(entity, "BoundsComponent");
                SpriteComponent sprite = (SpriteComponent) getComponentOfEntity(entity, "SpriteComponent");
                sprite.stateTime += deltaTime;

                if (!sprite.hasAnimation) {
                    batch.draw(sprite.sprite.getTexture(), bounds.position.x, bounds.position.y,
                            bounds.origin.x, bounds.origin.y, bounds.dimension.x, bounds.dimension.y, bounds.scale.x, bounds.scale.y,
                            bounds.rotation, sprite.sprite.getRegionX(), sprite.sprite.getRegionY(),
                            sprite.sprite.getRegionWidth(), sprite.sprite.getRegionHeight(), false, false);
                } else {

                    TextureRegion reg = sprite.animation.getKeyFrame(sprite.stateTime, true);

                    batch.draw(reg.getTexture(), bounds.position.x, bounds.position.y, bounds.origin.x,
                            bounds.origin.y, bounds.dimension.x, bounds.dimension.y, bounds.scale.x, bounds.scale.y,
                            bounds.rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                            reg.getRegionHeight(), false,
                            false);
                }

            }
        }


    }


}
