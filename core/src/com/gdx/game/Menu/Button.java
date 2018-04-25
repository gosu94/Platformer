package com.gdx.game.Menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

abstract public class Button extends TextButton {

    protected ClickListener clickListener;

    Button(String text, Skin skin) {
        super(text, skin);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                listener();
            }
        });
    }

    void listener() {
    }

    void setBounds() {
    }


}
