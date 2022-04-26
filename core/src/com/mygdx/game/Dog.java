package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Dog {
    float movementSpeed;
    public static int lifePoint;
    public static int scores;
    public static final int DOG_RUNNING = 0;
    public static final int DOG_OVER = 1;
    public int state;

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

    public void detectSpikeCollisions(LinkedList<Spike> spikeList){
        ListIterator<Spike> spikeListIterator = spikeList.listIterator();
        while (spikeListIterator.hasNext()){
            Spike spike = spikeListIterator.next();
            if(this.intersects(spike.boundingBox)){
                Dog.lifePoint -= 1;
                spikeListIterator.remove();
                if (Dog.lifePoint == 0){
                    this.state = DOG_OVER;
                }
                break;
            }
        }
    }

    public void detectCoinCollisions(LinkedList<Coin> coinList){
        ListIterator<Coin> coinListIterator = coinList.listIterator();
        while (coinListIterator.hasNext()){
            Coin coin = coinListIterator.next();
            if(this.intersects(coin.boundingBox)){
                Dog.scores += 1;
                coinListIterator.remove();
                break;
            }
        }
    }
}
