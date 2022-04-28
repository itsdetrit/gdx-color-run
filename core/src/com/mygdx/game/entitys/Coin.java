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
    public Coin(int xPosition, int yPosition, int width, int height, TextureRegion textureRegion) {
        super(xPosition, yPosition, width, height, textureRegion);
    }

    @Override
    public void onDraw(Batch batch) {

    }

    @Override
    public void onTranslate(float xChange, float yChange) {

    }

    public static void detectCollisions(LinkedList<Entity> entityList, Dog player){
        ListIterator<Entity> entityListIterator = entityList.listIterator();
        while (entityListIterator.hasNext()){
            Entity entity = entityListIterator.next();
            if(player.intersects(entity.getBoundingBox())){
                player.addScore(1);
                entityListIterator.remove();
                break;
            }
        }
    }
}
