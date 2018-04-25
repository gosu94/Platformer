package com.gdx.game.Components;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.Constants;

public class VelocityComponent extends Component {
    public Vector2 velocity;
    public Vector2 maximalSpeed;
    public Vector2 friction;
    public Vector2 acceleration;
    public Constants.VIEW_DIRECTION viewDirection;

    public VelocityComponent() {
        super("VelocityComponent");
    }

    public VelocityComponent(Vector2 velocity, Vector2 maximalSpeed, Vector2 friction, Vector2 acceleration) {
        super("VelocityComponent");
        this.velocity = velocity;
        this.maximalSpeed = maximalSpeed;
        this.friction = friction;
        this.acceleration = acceleration;
        this.viewDirection = Constants.VIEW_DIRECTION.RIGHT;
    }
}
