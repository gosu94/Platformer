package com.gdx.game.Adapter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ToastWindow {
    void toastLong(String text);

    void toastShort(String text);

    void render(SpriteBatch batch);
}
