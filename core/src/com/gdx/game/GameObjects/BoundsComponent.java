package com.gdx.game.GameObjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BoundsComponent extends Component {

    public Vector2 position;
    public Vector2 dimension;
    public Vector2 origin;
    public Vector2 scale;
    public float rotation;
    public Rectangle bounds;

    public BoundsComponent(Vector2 position, Rectangle bounds) {
        super("BoundsComponent");
        this.position = position;
        this.bounds = bounds;
        this.dimension = new Vector2().set(1f, 1f);
        this.origin = new Vector2().set(dimension.x / 2, dimension.y / 2);
        this.scale = new Vector2(1, 1);
        this.rotation = 0;
    }
}
