package com.gdx.game.GameObjects;

import com.badlogic.gdx.math.Vector2;

public class VelocityComponent extends Component {
    public Vector2 velocity;
    public Vector2 maximalSpeed;
    public Vector2 friction;
    public Vector2 acceleration;

    public VelocityComponent(Vector2 velocity, Vector2 maximalSpeed, Vector2 friction, Vector2 acceleration) {
        super("VelocityComponent");
        this.velocity = velocity;
        this.maximalSpeed = maximalSpeed;
        this.friction = friction;
        this.acceleration = acceleration;
    }
}
