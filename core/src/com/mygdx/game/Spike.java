package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Spike {

    float xPosition,yPosition;
    float width,height;
    Rectangle boundingBox;
    Vector2 directionVector;
    int movementSpeed;

    TextureRegion spikeTexture;

    public Spike(float width, float height,float xPosition, float yPosition,TextureRegion spikeTexture) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.spikeTexture = spikeTexture;
        this.boundingBox = new Rectangle(xPosition-width/2,yPosition-height/2,width,height);
        this.movementSpeed = 50;

        directionVector = new Vector2(0, -1);
    }

    public void draw(Batch batch){
        batch.draw(spikeTexture,boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
    }

    public void translate(float xChange,float yChange){
        boundingBox.setPosition(boundingBox.x+xChange, boundingBox.y+yChange);
    }

    public Vector2 getDirectionVector() {
        return directionVector;
    }
}
