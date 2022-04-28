package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Random;

public class Coin extends Entity{
    public static float coinSpawnTimer = 0;
    public static float timeBetweenCoinSpawns = 1f;

    public Coin(int xPosition, int yPosition, int width, int height, TextureRegion textureRegion) {
        super(xPosition, yPosition, width, height, textureRegion);
    }

    @Override
    public void onDraw(Batch batch) {

    }

    @Override
    public void onTranslate(float xChange, float yChange) {

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
            coinList.add(new Coin(random.nextInt(361)+220,WORLD_HEIGHT-10, 30,30,coinTextureRegion ));
            coinSpawnTimer -= timeBetweenCoinSpawns;
        }
    }

    private static void moveCoin(Coin coin, float deltaTime){
        coin.translate(0,-coin.movementSpeed*deltaTime);
    }

    public static void setCoinMovementSpeed(LinkedList<Coin> coinList,int movementSpeed){
        for (Coin coin : coinList){
            coin.movementSpeed = movementSpeed;
        }
    }
}
