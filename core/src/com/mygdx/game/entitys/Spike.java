package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.player.Dog;

public class Spike extends Entity{
    public Spike() {
        super();
    }

    @Override
    public void onDraw(Batch batch) {

    }

    @Override
    public void onDetectCollisions(Dog player, int id) {
        player.removeLifePoint(1);
        if (player.getLifePoint() == 0){
            player.setState(Dog.DOG_OVER);
        }
    }

}
