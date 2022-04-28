package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entitys.Coin;
import com.mygdx.game.entitys.Entity;
import com.mygdx.game.entitys.Spike;

import java.util.LinkedList;
import java.util.ListIterator;

public class Dog {
    private float movementSpeed;
    private int lifePoint;
    private int scores;
    private int state;
    private Rectangle boundingBox;
    private float xPosition,yPosition;
    private float width,height;
    private TextureRegion dogTexture;

    public static final int DOG_RUNNING = 0;
    public static final int DOG_OVER = 1;

    public Dog(float movementSpeed,float width, float height,
               float xCentre, float yCentre,TextureRegion dogTexture) {
        this.movementSpeed = movementSpeed;
        this.xPosition = xCentre - width/2;
        this.yPosition = yCentre - height/2;
        this.width = width;
        this.height = height;
        this.dogTexture = dogTexture;
        this.boundingBox = new Rectangle(xCentre-width/2,yCentre-height/2,width,height);
        this.lifePoint = 1;
        this.state = DOG_RUNNING;
    }

    public void draw(Batch batch){
        batch.draw(dogTexture,boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
    }

    public void translate(float xChange,float yChange){
        boundingBox.setPosition(boundingBox.x+xChange, boundingBox.y+yChange);
    }

    public boolean intersects(Rectangle otherRectangle) {
        return boundingBox.overlaps(otherRectangle);
    }

    public void detectInput(float delta,int WORLD_WIDTH){
        float leftLimit,rightLimit;
        leftLimit = -this.boundingBox.x+120;
        rightLimit = WORLD_WIDTH-this.boundingBox.x - this.boundingBox.width-120;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && rightLimit > 0) {
            this.translate(Math.min(this.movementSpeed * delta, rightLimit), 0f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && leftLimit < 0) {
            this.translate(Math.max(-this.movementSpeed * delta, leftLimit), 0f);
        }
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int getState() {
        return state;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public int getScores() {
        return scores;
    }

    public void addScore(int score){
        this.scores += score;
    }

    public void removeLifePoint(int lifePoint){
        this.lifePoint -= lifePoint;
    }

    public void setState(int state) {
        this.state = state;
    }
}
