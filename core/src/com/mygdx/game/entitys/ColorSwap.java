package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ColorSwap extends Entity{
    public ColorSwap(int xPosition, int yPosition, int width, int height, TextureRegion textureRegion) {
        super(xPosition, yPosition, width, height, textureRegion);
    }

    @Override
    public void onDraw(Batch batch) {

    }

    @Override
    public void onTranslate(float xChange, float yChange) {

    }
}
