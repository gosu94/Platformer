package com.gdx.game.Components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent extends Component {

    public Animation<TextureRegion> animation;
    public TextureRegion sprite;
    public float stateTime;
    public boolean hasAnimation;

    public SpriteComponent(Animation<TextureRegion> animation) {
        super("SpriteComponent");
        this.animation = animation;
        this.hasAnimation = true;
        this.stateTime = 0;

    }

    public SpriteComponent(TextureRegion sprite) {
        super("SpriteComponent");
        this.sprite = sprite;
        this.hasAnimation = false;
    }
}
