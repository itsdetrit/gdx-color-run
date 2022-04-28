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
    public Spike(int xPosition, int yPosition, int width, int height, TextureRegion spikeTextureRegion) {
        super(xPosition,yPosition,width,height,spikeTextureRegion);
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
                player.removeLifePoint(1);
                entityListIterator.remove();
                if (player.getLifePoint() == 0){
                    player.setState(Dog.DOG_OVER);
                }
                break;
            }
        }
    }

}
