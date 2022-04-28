package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Random;

public class Spike extends Entity{
    public static float spikeSpawnTimer = 0;
    public static float timeBetweenSpikeSpawns = 1f;

    public Spike(int xPosition, int yPosition, int width, int height, TextureRegion spikeTextureRegion) {
        super(xPosition,yPosition,width,height,spikeTextureRegion);
    }

    @Override
    public void draw(Batch batch){
        batch.draw(super.getTextureRegion(),boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
    }

    @Override
    public void translate(float xChange,float yChange){
        boundingBox.setPosition(boundingBox.x+xChange, boundingBox.y+yChange);
    }

    public static void renderSpike(float delta, LinkedList<Spike> spikeList,Batch batch){
        for (Spike spike : spikeList) {
            moveSpike(spike, delta);
            spike.draw(batch);
        }
    }

    public static void spawnSpike(float deltaTime,LinkedList<Spike> spikeList,int WORLD_HEIGHT, TextureRegion spikeTextureRegion){
        spikeSpawnTimer += deltaTime;
        Random random = new Random();

        if (spikeSpawnTimer > timeBetweenSpikeSpawns){
            spikeList.add(new Spike(random.nextInt(361)+220,WORLD_HEIGHT-10,100,30,spikeTextureRegion));
            spikeSpawnTimer -= timeBetweenSpikeSpawns;
        }
    }

    private static void moveSpike(Spike spike, float deltaTime){
        spike.translate(0,-spike.movementSpeed*deltaTime);
    }

    public static void setSpikeMovementSpeed(LinkedList<Spike> spikeList,int movementSpeed){
        for (Spike spike : spikeList){
            spike.movementSpeed = movementSpeed;
        }
    }
}
