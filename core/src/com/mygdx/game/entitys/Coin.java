package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.player.Dog;

public class Coin extends Entity{
    public Coin() {
        super();
    }

    @Override
    public void onDraw(Batch batch) {

    }

    @Override
    public void onDetectCollisions(Dog player) {
        player.addScore(1);
    }

}
