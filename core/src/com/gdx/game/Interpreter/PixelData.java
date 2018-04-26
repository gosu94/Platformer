package com.gdx.game.Interpreter;

abstract public class PixelData implements Pixel {
    private LevelParser.PIXEL_TYPE pixelType;

    PixelData(LevelParser.PIXEL_TYPE pixelType) {
        this.pixelType = pixelType;
    }

    @Override
    public LevelParser.PIXEL_TYPE getPixelType() {
        return pixelType;
    }
}
