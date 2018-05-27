package com.gdx.game.Systems;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.game.Components.BoundsComponent;
import com.gdx.game.Components.SpriteComponent;
import com.gdx.game.Components.VelocityComponent;
import com.gdx.game.Constants;
import com.gdx.game.Entity.Entity;
import com.gdx.game.Globals;

import java.util.List;

public class DrawingSystem extends System {

    public DrawingSystem(List<Entity> entityList) {
        super(entityList);
    }

    public void update(Batch batch) {
        for (Entity entity : entityList) {

            if (containsComponent(entity.componentList, "SpriteComponent")) {
                BoundsComponent bounds = (BoundsComponent) entity.getComponent("BoundsComponent");
                SpriteComponent sprite = (SpriteComponent) entity.getComponent("SpriteComponent");

                //sprite.stateTime += deltaTime;
                if (entity.containsComponent("VelocityComponent"))
                    drawWithDirection(entity, sprite, bounds, batch);
                else
                    drawSprite(entity, sprite, bounds, batch);


            }
        }


    }

    private void drawSprite(Entity entity, SpriteComponent sprite, BoundsComponent bounds, Batch batch) {
        if (!sprite.hasAnimation) {
            batch.draw(sprite.sprite.getTexture(), bounds.position.x, bounds.position.y,
                    bounds.origin.x, bounds.origin.y, bounds.dimension.x, bounds.dimension.y, bounds.scale.x, bounds.scale.y,
                    bounds.rotation, sprite.sprite.getRegionX(), sprite.sprite.getRegionY(),
                    sprite.sprite.getRegionWidth(), sprite.sprite.getRegionHeight(),
                    false, false);
        } else {

            TextureRegion reg = sprite.animation.getKeyFrame(sprite.stateTime, true);

            batch.draw(reg.getTexture(), bounds.position.x, bounds.position.y, bounds.origin.x,
                    bounds.origin.y, bounds.dimension.x, bounds.dimension.y, bounds.scale.x, bounds.scale.y,
                    bounds.rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                    reg.getRegionHeight(), false,
                    false);
        }
    }

    private void drawWithDirection(Entity entity, SpriteComponent sprite, BoundsComponent bounds, Batch batch) {
        VelocityComponent velocity = (VelocityComponent) entity.getComponent("VelocityComponent");
        if (!sprite.hasAnimation) {
            batch.draw(sprite.sprite.getTexture(), bounds.position.x, bounds.position.y,
                    bounds.origin.x, bounds.origin.y, bounds.dimension.x, bounds.dimension.y, bounds.scale.x, bounds.scale.y,
                    bounds.rotation, sprite.sprite.getRegionX(), sprite.sprite.getRegionY(),
                    sprite.sprite.getRegionWidth(), sprite.sprite.getRegionHeight(),
                    velocity.viewDirection == Constants.VIEW_DIRECTION.LEFT, false);
        } else {


            if (sprite.hasStandingAnimation) {

                if (Globals.isPlayerMoving) {
                    TextureRegion reg = sprite.animation.getKeyFrame(sprite.stateTime, true);
                    batch.draw(reg.getTexture(), bounds.position.x, bounds.position.y, bounds.origin.x,
                            bounds.origin.y, bounds.dimension.x, bounds.dimension.y, bounds.scale.x, bounds.scale.y,
                            bounds.rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                            reg.getRegionHeight(), velocity.viewDirection == Constants.VIEW_DIRECTION.LEFT,
                            false);
                } else {
                    TextureRegion reg2 = sprite.standingAnimation.getKeyFrame(sprite.stateTime, true);
                    batch.draw(reg2.getTexture(), bounds.position.x, bounds.position.y, bounds.origin.x,
                            bounds.origin.y, bounds.dimension.x, bounds.dimension.y, bounds.scale.x, bounds.scale.y,
                            bounds.rotation, reg2.getRegionX(), reg2.getRegionY(), reg2.getRegionWidth(),
                            reg2.getRegionHeight(), velocity.viewDirection == Constants.VIEW_DIRECTION.LEFT,
                            false);
                }

            } else {
                TextureRegion reg = sprite.animation.getKeyFrame(sprite.stateTime, true);
                batch.draw(reg.getTexture(), bounds.position.x, bounds.position.y, bounds.origin.x,
                        bounds.origin.y, bounds.dimension.x, bounds.dimension.y, bounds.scale.x, bounds.scale.y,
                        bounds.rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                        reg.getRegionHeight(), velocity.viewDirection == Constants.VIEW_DIRECTION.LEFT,
                        false);
            }
        }
    }


}
