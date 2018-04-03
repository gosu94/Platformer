package com.gdx.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.game.Assets;

public class Rock extends AbstractGameObject {

    private TextureRegion regMiddle;
    private int length;

    public Rock(boolean isUnder) {
        init(isUnder);
    }

    private void init(boolean isUnder) {
        dimension.set(1.0f, 1.0f);
        if (!isUnder) regMiddle = Assets.instance.rock.rock;
        else regMiddle = Assets.instance.rock.rockUnder;
        // Start length of this rock
        setLength(1);
    }

    public void setLength(int length) {
        this.length = length;
        bounds.set(0, 0, dimension.x * length, dimension.y);
    }

    public void increaseLength(int amount) {
        setLength(length + amount);
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = null;
        float relX = 0;
        float relY = 0;
        relX = 0;
        reg = regMiddle;
        for (int i = 0; i < length; i++) {
            batch.draw(reg.getTexture(), position.x + relX, position.y + relY, origin.x, origin.y,
                    dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(),
                    reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
            relX += dimension.x;
        }

    }

}
