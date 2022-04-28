package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.player.Dog;

import java.util.LinkedList;
import java.util.ListIterator;

public abstract class Entity {
    private Rectangle boundingBox;
    private int movementSpeed = 200;
    private TextureRegion textureRegion;
    private int id;

    public Entity(int xPosition, int yPosition, int width, int height, TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        this.boundingBox = new Rectangle(xPosition-width/2,yPosition-height/2,width,height);
        this.id = 0;
    }

    public Entity(int xPosition, int yPosition, int width, int height, TextureRegion textureRegion, int id) {
        this.textureRegion = textureRegion;
        this.boundingBox = new Rectangle(xPosition-width/2,yPosition-height/2,width,height);
        this.id = id;
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
                onDetectCollisions(player, entity.id);
                entityListIterator.remove();
                break;
            }
        }
    }

    public abstract void onDraw(Batch batch);
    public abstract void onDetectCollisions(Dog player, int id);

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
