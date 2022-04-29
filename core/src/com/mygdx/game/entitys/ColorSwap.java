package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.player.Dog;

public class ColorSwap extends Entity{
    public ColorSwap() {
        super();
    }

    @Override
    public void onDraw(Batch batch) {

    }

    @Override
    public void onDetectCollisions(Dog player, int id) {
        player.randomColor();
    }
}
