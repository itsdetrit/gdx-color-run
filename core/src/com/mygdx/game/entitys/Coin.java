package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.player.Dog;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

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
