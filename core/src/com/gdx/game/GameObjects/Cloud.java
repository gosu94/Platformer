package com.gdx.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cloud extends AbstractGameObject {
    private TextureRegion regCloud;


    public Cloud() {
    }

    public void setRegion(TextureRegion region) {
        regCloud = region;
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = regCloud;
        batch.draw(reg.getTexture(), position.x + origin.x, position.y + origin.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
    }
}
