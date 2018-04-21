package com.gdx.game.Systems;

import com.badlogic.gdx.math.Rectangle;
import com.gdx.game.Components.BoundsComponent;
import com.gdx.game.Components.JumpComponent;
import com.gdx.game.Components.VelocityComponent;
import com.gdx.game.Constants;
import com.gdx.game.Entity;
import com.gdx.game.Globals;

import java.util.List;

public class CollisionSystem extends System {

    private Rectangle r1;
    private Rectangle r2;

    public CollisionSystem(List<Entity> entityList) {
        super(entityList);
        r1 = new Rectangle();
        r2 = new Rectangle();
    }

    public void update() {
        for (Entity entity : entityList) {
            removeIfNeccesarry(entity);
            if (containsComponent(entity.componentList, "CollisionComponent") &&
                    !"Rock".equals(entity.name)) {
                BoundsComponent bounds = (BoundsComponent) getComponentOfEntity(entity, "BoundsComponent");

                for (Entity entity2 : entityList) {
                    if (containsComponent(entity2.componentList, "CollisionComponent")) {
                        BoundsComponent entity2Bounds = (BoundsComponent) getComponentOfEntity(entity2, "BoundsComponent");
                        r1.set(bounds.position.x, bounds.position.y,
                                bounds.bounds.width, bounds.bounds.height);
                        r2.set(entity2Bounds.position.x, entity2Bounds.position.y,
                                entity2Bounds.bounds.width, entity2Bounds.bounds.height);
                        if (r1.overlaps(r2)) {
                            if ("Rock".equals(entity2.name))
                                collisionWithRock(entity, entity2);
                            if ("Coin".equals(entity2.name) && "Player".equals(entity.name))
                                collisionWithCoin(entityList, entity2);
                        }
                    }

                }

            }
        }
    }

    private void collisionWithRock(Entity entity, Entity rock) {
        BoundsComponent bounds = (BoundsComponent) getComponentOfEntity(entity, "BoundsComponent");
        VelocityComponent velocity = (VelocityComponent) getComponentOfEntity(entity, "VelocityComponent");
        BoundsComponent rockBounds = (BoundsComponent) getComponentOfEntity(rock, "BoundsComponent");

        float heightDifference = Math.abs(bounds.position.y - (rockBounds.position.y + rockBounds.bounds.height));
        if (heightDifference > 0.25f) {

            boolean hitRightEdge = bounds.position.x > (rockBounds.position.x + rockBounds.bounds.width / 2.0f);
            if (hitRightEdge) {
                bounds.position.x = rockBounds.position.x + rockBounds.bounds.width;

                if ("Enemy".equals(entity.name)) {
                    changeDirection(velocity);
                }
            } else {
                bounds.position.x = rockBounds.position.x - bounds.bounds.width;
                if ("Enemy".equals(entity.name)) {
                    changeDirection(velocity);
                }
            }
            return;
        }

        if ("Player".equals(entity.name)) {
            JumpComponent jumpComponent = (JumpComponent) entity.getComponent("JumpComponent");
            switch (jumpComponent.jumpState) {
                case GROUNDED:
                    break;
                case FALLING:
                case JUMP_FALLING:
                    bounds.position.y = rockBounds.position.y + bounds.bounds.height;
                    jumpComponent.jumpState = Constants.JUMP_STATE.GROUNDED;
                    break;
                case JUMP_RISING:
                    bounds.position.y = rockBounds.position.y + bounds.bounds.height;
                    break;
            }
        } else {
            bounds.position.y = rockBounds.position.y + rockBounds.bounds.height;
        }
    }

    private void collisionWithCoin(List<Entity> entityList, Entity coin) {

        //coin.remove();
        Globals.points += 100;

    }


    private void changeDirection(VelocityComponent velocity) {
        float speedX = velocity.acceleration.x;
        speedX = -speedX;
        //zeby sie odbil
        velocity.velocity.x = speedX / 2;
        velocity.acceleration.x = speedX;
    }

}
