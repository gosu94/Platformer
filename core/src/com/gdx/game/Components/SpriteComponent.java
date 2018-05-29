package com.gdx.game.Components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent extends Component {

    public Animation<TextureRegion> animation;
    public Animation<TextureRegion> standingAnimation;
    public TextureRegion sprite;
    public TextureRegion alterSprite;
    public float stateTime;
    public boolean hasAnimation;
    public boolean hasStandingAnimation;

    public SpriteComponent() {
        super("SpriteComponent");
    }

    public SpriteComponent(Animation<TextureRegion> animation, Animation<TextureRegion> standingAnimation) {
        super("SpriteComponent");
        this.animation = animation;
        this.standingAnimation = standingAnimation;
        this.hasAnimation = true;
        this.hasStandingAnimation = true;
        this.stateTime = 0;


    }
    public SpriteComponent(Animation<TextureRegion> animation) {
        super("SpriteComponent");
        this.animation = animation;
        this.hasAnimation = true;
        this.hasStandingAnimation = false;
        this.stateTime = 0;

    }

    public SpriteComponent(TextureRegion sprite) {
        super("SpriteComponent");
        this.sprite = sprite;
        this.hasAnimation = false;
        this.hasStandingAnimation = false;
    }

}
