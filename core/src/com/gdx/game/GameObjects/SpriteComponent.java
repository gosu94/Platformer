package com.gdx.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent extends Component {

    Animation<TextureRegion> animation;
    TextureRegion sprite;
    int stateTime;
    boolean hasAnimation;

    SpriteComponent(Animation<TextureRegion> animation) {
        super("SpriteComponent");
        this.animation = animation;
        this.hasAnimation = true;
        this.stateTime = 0;

    }

    SpriteComponent(String name, TextureRegion sprite) {
        super(name);
        this.sprite = sprite;
        this.hasAnimation = false;
    }
}
