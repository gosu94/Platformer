package com.gdx.game.Systems;

import com.badlogic.gdx.math.MathUtils;
import com.gdx.game.Components.BoundsComponent;
import com.gdx.game.Components.VelocityComponent;
import com.gdx.game.Constants;
import com.gdx.game.Entity.Entity;
import com.gdx.game.Globals;
import com.gdx.game.Observer.DeathObserver;
import com.gdx.game.Observer.Observer;
import com.gdx.game.WorldRenderer;

import java.util.ArrayList;
import java.util.List;

public class MovingSystem extends System {
    private List<Observer> observers;
    public MovingSystem(List<Entity> entityList) {
        super(entityList);
        observers = new ArrayList<>();
        observers.add(new DeathObserver());
    }

    public void update(float deltaTime) {
        for (Entity entity : entityList) {
            if (containsComponent(entity.componentList, "VelocityComponent")) {
                VelocityComponent velocityComponent = (VelocityComponent) getComponentOfEntity(entity, "VelocityComponent");
                BoundsComponent boundsComponent = (BoundsComponent) getComponentOfEntity(entity, "BoundsComponent");
                updateMotionX(velocityComponent, deltaTime);
                updateMotionY(velocityComponent, deltaTime);
                // Move to new position
                boundsComponent.position.x += velocityComponent.velocity.x * deltaTime;
                boundsComponent.position.y += velocityComponent.velocity.y * deltaTime;

                if (velocityComponent.velocity.x != 0) {
                    velocityComponent.viewDirection = velocityComponent.velocity.x < 0 ? Constants.VIEW_DIRECTION.LEFT :
                            Constants.VIEW_DIRECTION.RIGHT;
                }
                if (boundsComponent.position.y < -4) {
                    if ("Player".equals(entity.getName())) {
                        Globals.lives -= 1;

                        boundsComponent.position.x = Globals.startingPoint.x;
                        boundsComponent.position.y = Globals.startingPoint.y;
                        WorldRenderer.toast.toastShort("You have lost a life");
                        notifyAllObservers();
                    }
                    else
                        entity.reset();
                }
            }
        }
    }

    private void updateMotionX(VelocityComponent velocityComponent, float deltaTime) {
        if (velocityComponent.velocity.x != 0) {
            // Apply friction
            if (velocityComponent.velocity.x > 0) {
                velocityComponent.velocity.x =
                        Math.max(velocityComponent.velocity.x - velocityComponent.friction.x * deltaTime, 0);
            } else {
                velocityComponent.velocity.x =
                        Math.min(velocityComponent.velocity.x + velocityComponent.friction.x * deltaTime, 0);
            }
        }
        velocityComponent.velocity.x += velocityComponent.acceleration.x * deltaTime;
        velocityComponent.velocity.x = MathUtils.clamp(velocityComponent.velocity.x,
                -velocityComponent.maximalSpeed.x, velocityComponent.maximalSpeed.x);
    }

    private void updateMotionY(VelocityComponent velocityComponent, float deltaTime) {
        if (velocityComponent.velocity.y != 0) {
            // Apply friction
            if (velocityComponent.velocity.y > 0) {
                velocityComponent.velocity.y = Math.max(velocityComponent.velocity.y - velocityComponent.friction.y *
                        deltaTime, 0);
            } else {
                velocityComponent.velocity.y = Math.min(velocityComponent.velocity.y + velocityComponent.friction.y *
                        deltaTime, 0);
            }
        }
        // Apply acceleration
        velocityComponent.velocity.y += velocityComponent.acceleration.y * deltaTime;
        // Make sure the object's velocity does not exceed the
        // positive or negative terminal velocity
        velocityComponent.velocity.y = MathUtils.clamp(velocityComponent.velocity.y, -
                velocityComponent.maximalSpeed.y, velocityComponent.maximalSpeed.y);
    }

    private void notifyAllObservers() {
        for (Observer observer : observers)
            observer.update();
    }

}
