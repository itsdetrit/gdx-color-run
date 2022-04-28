package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Random;

public class Spike {

    float xPosition,yPosition;
    float width,height;
    Rectangle boundingBox;
    Vector2 directionVector;
    int movementSpeed;
    public static float spikeSpawnTimer = 0;
    public static float timeBetweenSpikeSpawns = 1f;

    TextureRegion spikeTexture;

    public Spike(float width, float height,float xPosition, float yPosition,TextureRegion spikeTexture) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.spikeTexture = spikeTexture;
        this.boundingBox = new Rectangle(xPosition-width/2,yPosition-height/2,width,height);
        this.movementSpeed = 200;

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

    public static void renderSpike(float delta, LinkedList<Spike> spikeList,Batch batch){
        for (Spike spike : spikeList) {
            moveSpike(spike, delta);
            spike.draw(batch);
        }
    }

    private static void moveSpike(Spike spike, float deltaTime){
        float yMove = spike.getDirectionVector().y * spike.movementSpeed * deltaTime;
        spike.translate(0,-spike.movementSpeed*deltaTime);
    }

    public static void spawnSpike(float deltaTime,LinkedList<Spike> spikeList,int WORLD_HEIGHT, TextureRegion spikeTextureRegion){
        spikeSpawnTimer += deltaTime;
        Random random = new Random();

        if (spikeSpawnTimer > timeBetweenSpikeSpawns){
            spikeList.add(new Spike(100,30,random.nextInt(361)+220,WORLD_HEIGHT-10,spikeTextureRegion));
            spikeSpawnTimer -= timeBetweenSpikeSpawns;
        }
    }

    public static void setSpikeMovementSpeed(LinkedList<Spike> spikeList,int movementSpeed){
        for (Spike spike : spikeList){
            spike.movementSpeed = movementSpeed;
        }
    }
}
