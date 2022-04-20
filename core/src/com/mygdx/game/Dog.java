package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class Dog {
    float movementSpeed;

    Rectangle boundingBox;

    float xPosition,yPosition;
    float width,height;

    TextureRegion dogTexture;

    public Dog(float movementSpeed,float width, float height,
               float xCentre, float yCentre,TextureRegion dogTexture) {
        this.movementSpeed = movementSpeed;
        this.xPosition = xCentre - width/2;
        this.yPosition = yCentre - height/2;
        this.width = width;
        this.height = height;
        this.dogTexture = dogTexture;
        this.boundingBox = new Rectangle(xCentre-width/2,yCentre-height/2,width,height);
    }

    public void draw(Batch batch){
        batch.draw(dogTexture,boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
    }

    public void translate(float xChange,float yChange){
        boundingBox.setPosition(boundingBox.x+xChange, boundingBox.y+yChange);
    }
}
