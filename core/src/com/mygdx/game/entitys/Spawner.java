package com.mygdx.game.entitys;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.player.Dog;

import java.util.LinkedList;
import java.util.Random;

public class Spawner {

    private float spawnTimer = 0;
    private float timeBetweenSpawns = 1f;
    private int width,height;
    private LinkedList<Entity> entities;
    private TextureRegion entityTextureRegion;
    private TextureRegion[] textureRegionList;

    public Spawner(float spawnTimer, float timeBetweenSpawns, int width, int height, TextureRegion entityTextureRegion) {
        this.spawnTimer = spawnTimer;
        this.timeBetweenSpawns = timeBetweenSpawns;
        this.width = width;
        this.height = height;
        this.entityTextureRegion = entityTextureRegion;
        entities = new LinkedList<>();
    }

    public Spawner(float spawnTimer, float timeBetweenSpawns, int width, int height, TextureRegion defaultTextureRegion, TextureRegion[] textureRegionList) {
        this.spawnTimer = spawnTimer;
        this.timeBetweenSpawns = timeBetweenSpawns;
        this.width = width;
        this.height = height;
        this.entityTextureRegion = defaultTextureRegion;
        this.textureRegionList = textureRegionList;
        entities = new LinkedList<>();
    }

    public void render(float delta, Batch batch){
        for (Entity entity : entities){
            move(entity,delta);
            entity.draw(batch);
        }
    }

    public void spawn(float deltaTime,int WORLD_HEIGHT){
        spawnTimer += deltaTime;
        Random random = new Random();

        if (spawnTimer > timeBetweenSpawns){
            if (textureRegionList == null){
                entities.add(new Entity(random.nextInt(361) + 220, WORLD_HEIGHT - 10, width, height, entityTextureRegion) {
                    @Override
                    public void onDraw(Batch batch) {

                    }

                    @Override
                    public void onDetectCollisions(Dog player, int id) {

                    }
                });
            } else {
                int id = random.nextInt(3);
                entities.add(new Entity(random.nextInt(361) + 220, WORLD_HEIGHT - 10, width, height
                        , textureRegionList[id],id) {
                    @Override
                    public void onDraw(Batch batch) {

                    }

                    @Override
                    public void onDetectCollisions(Dog player, int id) {
                    }
                });
            }
            spawnTimer -= timeBetweenSpawns;
        }
    }

    private void move(Entity entity, float deltaTime){
        entity.translate(0,-entity.getMovementSpeed()*deltaTime);
    }

    public void setMovementSpeed(int movementSpeed){
        for (Entity entity : entities){
            entity.setMovementSpeed(movementSpeed);
        }
    }

    public void setSpawnTimer(float spawnTimer) {
        this.spawnTimer = spawnTimer;
    }

    public LinkedList<Entity> getEntities() {
        return entities;
    }
}
