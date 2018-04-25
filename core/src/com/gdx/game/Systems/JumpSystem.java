package com.gdx.game.Systems;

import com.gdx.game.Components.JumpComponent;
import com.gdx.game.Components.VelocityComponent;
import com.gdx.game.Constants;
import com.gdx.game.Entity.Entity;

import java.util.List;

public class JumpSystem extends System {

    public JumpSystem(List<Entity> entityList) {
        super(entityList);
    }

    public void update(float deltaTime) {
        for (Entity entity : entityList) {

            if (entity.containsComponent("JumpComponent")) {
                JumpComponent jumpComponent = (JumpComponent) entity.getComponent("JumpComponent");
                VelocityComponent velocityComponent = (VelocityComponent) entity.getComponent("VelocityComponent");

                setJumping(jumpComponent);

                switch (jumpComponent.jumpState) {
                    case GROUNDED:
                        jumpComponent.jumpState = Constants.JUMP_STATE.FALLING;
                        break;
                    case JUMP_RISING:
                        jumpComponent.timeJumping += deltaTime;
                        if (jumpComponent.timeJumping <= jumpComponent.JUMP_TIME_MAX) {
                            velocityComponent.velocity.y = velocityComponent.maximalSpeed.y;
                        }
                        if (jumpComponent.timeJumping > 0 && jumpComponent.timeJumping <= jumpComponent.JUMP_TIME_MIN) {
                            velocityComponent.velocity.y = velocityComponent.maximalSpeed.y;
                        }
                        break;
                    case FALLING:
                        break;
                    case JUMP_FALLING:
                        jumpComponent.timeJumping += deltaTime;
                }


            }

        }
    }

    public void setJumping(JumpComponent jumpComponent) {
        switch (jumpComponent.jumpState) {
            case GROUNDED:
                if (jumpComponent.jumpKeyPressed) {
                    jumpComponent.timeJumping = 0;
                    jumpComponent.jumpState = Constants.JUMP_STATE.JUMP_RISING;
                }
                break;
            case JUMP_RISING:
                if (!jumpComponent.jumpKeyPressed)
                    jumpComponent.jumpState = Constants.JUMP_STATE.JUMP_FALLING;
                break;
            case FALLING:
            case JUMP_FALLING:
                break;
        }
    }

}
