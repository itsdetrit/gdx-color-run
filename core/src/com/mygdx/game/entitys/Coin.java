package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.player.Dog;

public class Coin extends Entity{
    public Coin() {
        super();
    }

    @Override
    public void onDraw(Batch batch) {

    }

    @Override
    public void onDetectCollisions(Dog player, int id) {
        if(player.getColor() == id){
            player.addScore(1);
        } else {
            player.removeScore(1);
        }
    }

}
