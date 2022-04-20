package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;
import java.util.ListIterator;

public class GameScreen implements Screen {

    private Camera camera;
    private Viewport viewport;

    private SpriteBatch batch;
    private TextureAtlas textureAtlas;

    private TextureRegion background;

    private TextureRegion dogTextureRegion,spikeTextureRegion,coinTextureRegion;

    private int backgroundOffSet;
    private float timeBetweenSpikeSpawns = 3f;
    private float spikeSpawnTimer = 0;

    private final int WORLD_WIDTH = 800;
    private final int WORLD_HEIGHT = 600;

    private Dog playerDog;
    private LinkedList<Spike> spikeList;

    public GameScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        textureAtlas = new TextureAtlas("image.atlas");

        background = textureAtlas.findRegion("road2");

        backgroundOffSet = 0;


        dogTextureRegion = textureAtlas.findRegion("dog");
        spikeTextureRegion = textureAtlas.findRegion("spike");

        playerDog = new Dog(230,50,50,WORLD_WIDTH/2,WORLD_HEIGHT/10,dogTextureRegion);
        spikeList = new LinkedList<>();

        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltatime) {
        batch.begin();
        renderBackground(deltatime);
        detectInput(deltatime);
        spawnSpike(deltatime);
        ListIterator<Spike> spikeListIterator = spikeList.listIterator();
        while (spikeListIterator.hasNext()){
            Spike spike = spikeListIterator.next();
            moveSpike(spike,deltatime);
            spike.draw(batch);
        }
        playerDog.draw(batch);
        batch.end();
    }

    public void renderBackground(float delta){
        backgroundOffSet ++;
        if (backgroundOffSet % WORLD_HEIGHT == 0){
            backgroundOffSet = 0;
        }
        batch.draw(background,0,-backgroundOffSet,WORLD_WIDTH,WORLD_HEIGHT);
        batch.draw(background,0,-backgroundOffSet+WORLD_HEIGHT,WORLD_WIDTH,WORLD_HEIGHT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void detectInput(float deltatime){
        float leftLimit,rightLimit;
        leftLimit = -playerDog.boundingBox.x;
        rightLimit = WORLD_WIDTH-playerDog.boundingBox.x - playerDog.boundingBox.width;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && rightLimit > 0) {
            playerDog.translate(Math.min(playerDog.movementSpeed * deltatime, rightLimit), 0f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && leftLimit < 0) {
            playerDog.translate(Math.max(-playerDog.movementSpeed * deltatime, leftLimit), 0f);
        }
    }

    private void spawnSpike(float deltatime){
        spikeSpawnTimer += deltatime;

        if (spikeSpawnTimer > timeBetweenSpikeSpawns){
            spikeList.add(new Spike(100,30,WORLD_WIDTH/2,WORLD_HEIGHT/2,spikeTextureRegion));
            spikeSpawnTimer -= timeBetweenSpikeSpawns;
        }
    }

    private void moveSpike(Spike spike,float deltaTime){
        float yMove = spike.getDirectionVector().y * spike.movementSpeed * deltaTime;
        spike.translate(0,-spike.movementSpeed*deltaTime);

    }
}
