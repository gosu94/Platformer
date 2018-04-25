package com.gdx.game.Components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BoundsComponent extends Component {

    public Vector2 position;
    public Vector2 dimension;
    public Vector2 origin;
    public Vector2 scale;
    public float rotation;
    public Rectangle bounds;


    public BoundsComponent() {
        super("BoundsComponent");
    }

    public BoundsComponent(Vector2 position, float dimensionX, float dimensionY) {
        super("BoundsComponent");
        this.position = position;
        this.bounds = new Rectangle(0, 0, dimensionX, dimensionY);
        this.dimension = new Vector2().set(dimensionX, dimensionY);
        this.origin = new Vector2().set(dimension.x / 2, dimension.y / 2);
        this.scale = new Vector2(1, 1);
        this.rotation = 0;
    }
}
