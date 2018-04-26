package com.gdx.game.Interpreter;

public interface Pixel {
    void interpret(int pixelX, float baseHeight);

    LevelParser.PIXEL_TYPE getPixelType();
}
