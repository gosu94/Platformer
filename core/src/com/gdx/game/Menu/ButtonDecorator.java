package com.gdx.game.Menu;


import com.badlogic.gdx.graphics.Color;
import com.gdx.game.GameScreens.MenuScreen;

abstract public class ButtonDecorator extends Button {
    protected Button buttonToBeDecorated;
    Color defaultColor;

    public ButtonDecorator(Button buttonToBeDecorated) {
        super("", MenuScreen.skin);
        this.buttonToBeDecorated = buttonToBeDecorated;
        setText(buttonToBeDecorated.getText().toString());
        setSkin(buttonToBeDecorated.getSkin());
        setBounds();
        this.defaultColor = buttonToBeDecorated.getColor();

    }

    @Override
    void listener() {
        buttonToBeDecorated.listener();
    }

    @Override
    void setBounds() {
        setPosition(buttonToBeDecorated.getX(), buttonToBeDecorated.getY());
        setSize(buttonToBeDecorated.getWidth(), buttonToBeDecorated.getHeight());
    }
}
