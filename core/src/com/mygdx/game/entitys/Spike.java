package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.player.Dog;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class Spike extends Entity{
    public Spike() {
        super();
    }

    @Override
    public void onDraw(Batch batch) {

    }

    @Override
    public void onTranslate(float xChange, float yChange) {

    }

    @Override
    public void onDetectCollisions(LinkedList<Entity> entityList, ListIterator<Entity> listIterator, Dog player) {
        player.removeLifePoint(1);
        listIterator.remove();
        if (player.getLifePoint() == 0){
            player.setState(Dog.DOG_OVER);
        }
    }

}
