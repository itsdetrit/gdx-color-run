package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

abstract class Entity {
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

    public abstract void draw(Batch batch);
    public abstract void translate(float xChange, float yChange);

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}
