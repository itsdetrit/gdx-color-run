package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.player.Dog;

import java.util.LinkedList;
import java.util.ListIterator;

public abstract class Entity {
    protected float xPosition,yPosition;
    protected float width,height;
    protected Rectangle boundingBox;
    protected Vector2 directionVector;
    protected int movementSpeed = 200;
    protected TextureRegion textureRegion;

    public Entity(int xPosition, int yPosition, int width, int height, TextureRegion textureRegion) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.textureRegion = textureRegion;

        this.boundingBox = new Rectangle(xPosition-width/2,yPosition-height/2,width,height);
        this.directionVector = new Vector2(0, -1);
    }

    public Entity() {}

    public void draw(Batch batch){
        batch.draw(textureRegion,boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
        onDraw(batch);
    };

    public void translate(float xChange, float yChange){
        boundingBox.setPosition(boundingBox.x+xChange, boundingBox.y+yChange);
    }

    public void detectCollisions(LinkedList<Entity> entityList, Dog player){
        ListIterator<Entity> entityListIterator = entityList.listIterator();
        while (entityListIterator.hasNext()){
            Entity entity = entityListIterator.next();
            if(player.intersects(entity.getBoundingBox())){
                onDetectCollisions(player);
                entityListIterator.remove();
                break;
            }
        }
    }

    public abstract void onDraw(Batch batch);
    public abstract void onDetectCollisions(Dog player);
    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}
