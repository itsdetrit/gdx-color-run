package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Random;

public class Coin {
    float xPosition,yPosition;
    float width,height;
    Rectangle boundingBox;
    Vector2 directionVector;
    int movementSpeed;
    public static float timeBetweenCoinSpawns = 3f;
    public static float coinSpawnTimer = 0;

    TextureRegion coinTexture;

    public Coin(float width, float height, float xPosition, float yPosition, TextureRegion coinTexture) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.coinTexture = coinTexture;
        this.boundingBox = new Rectangle(xPosition-width/2,yPosition-height/2,width,height);
        this.movementSpeed = 50;

        directionVector = new Vector2(0, -1);
    }

    public void draw(Batch batch){
        batch.draw(coinTexture,boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
    }

    public void translate(float xChange,float yChange){
        boundingBox.setPosition(boundingBox.x+xChange, boundingBox.y+yChange);
    }

    public Vector2 getDirectionVector() {
        return directionVector;
    }

    public static void renderCoin(float delta, LinkedList<Coin> coinList,Batch batch){
        for (Coin coin : coinList){
            moveCoin(coin,delta);
            coin.draw(batch);
        }
    }

    public static void spawnCoin(float deltaTime,LinkedList<Coin> coinList,int WORLD_HEIGHT,TextureRegion coinTextureRegion){
        coinSpawnTimer += deltaTime;
        Random random = new Random();

        if (coinSpawnTimer > timeBetweenCoinSpawns){
            coinList.add(new Coin(30,30, random.nextInt(361)+220,WORLD_HEIGHT-10,coinTextureRegion ));
            coinSpawnTimer -= timeBetweenCoinSpawns;
        }
    }

    private static void moveCoin(Coin coin, float deltaTime){
        float yMove = coin.getDirectionVector().y * coin.movementSpeed * deltaTime;
        coin.translate(0,-coin.movementSpeed*deltaTime);
    }

    public static void setCoinMovementSpeed(LinkedList<Coin> coinList,int movementSpeed){
        for (Coin coin : coinList){
            coin.movementSpeed = movementSpeed;
        }
    }
}
