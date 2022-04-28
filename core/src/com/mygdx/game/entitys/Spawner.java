package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.LinkedList;
import java.util.Random;

public class Spawner {

    private float spawnTimer = 0;
    private float timeBetweenSpawns = 1f;
    private int width,height;

    public Spawner(float spawnTimer, float timeBetweenSpawns, int width, int height) {
        this.spawnTimer = spawnTimer;
        this.timeBetweenSpawns = timeBetweenSpawns;
        this.width = width;
        this.height = height;
    }

    public void render(float delta, LinkedList<Entity> entityList, Batch batch){
        for (Entity entity : entityList){
            move(entity,delta);
            entity.draw(batch);
        }
    }

    public void spawn(float deltaTime, LinkedList<Entity> entityList, int WORLD_HEIGHT, TextureRegion entityTextureRegion){
        spawnTimer += deltaTime;
        Random random = new Random();

        if (spawnTimer > timeBetweenSpawns){
            entityList.add(new Entity(random.nextInt(361) + 220, WORLD_HEIGHT - 10, width, height, entityTextureRegion) {
                @Override
                public void onDraw(Batch batch) {

                }

                @Override
                public void onTranslate(float xChange, float yChange) {

                }
            });
            spawnTimer -= timeBetweenSpawns;
        }
    }

    private void move(Entity entity, float deltaTime){
        entity.translate(0,-entity.movementSpeed*deltaTime);
    }

    public void setMovementSpeed(LinkedList<Entity> entityList,int movementSpeed){
        for (Entity entity : entityList){
            entity.movementSpeed = movementSpeed;
        }
    }

    public void setSpawnTimer(float spawnTimer) {
        this.spawnTimer = spawnTimer;
    }
}
