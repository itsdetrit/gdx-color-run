package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entitys.Coin;
import com.mygdx.game.entitys.ColorSwap;
import com.mygdx.game.entitys.Spawner;
import com.mygdx.game.player.Dog;
import com.mygdx.game.entitys.Spike;

import java.util.Random;

public class GameScreen implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private final int WORLD_WIDTH = 800;
    private final int WORLD_HEIGHT = 600;
    private int backgroundOffSet;

    private TextureAtlas textureAtlas,itemAtlas;
    private TextureRegion background;
    private TextureRegion redDogTextureRegion,greenDogTextureRegion,blueDogTextureRegion
            ,redCoinTextureRegion,greenCoinTextureRegion,blueCoinTextureRegion,coinTextureRegion
            ,spikeTextureRegion,colorSwapTextureRegion;
    private TextureRegion restartButton;

    private Dog playerDog;
    private Coin coinDetector = new Coin();
    private Spike spikeDetector = new Spike();
    private ColorSwap colorSwapDetector = new ColorSwap();
    private Spawner coinSpawner,spikeSpawner,colorSwapSpawner;

    private BitmapFont font = new BitmapFont();
    private Vector2 touchPoint;
    private RGBDog game;

    public GameScreen(RGBDog game){
        this.game = game;
        Random random = new Random();
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        textureAtlas = new TextureAtlas("image.atlas");
        itemAtlas = new TextureAtlas("items.atlas");
        background = textureAtlas.findRegion("road2");
        backgroundOffSet = 0;

        redDogTextureRegion = textureAtlas.findRegion("reddog");
        greenDogTextureRegion = textureAtlas.findRegion("greendog");
        blueDogTextureRegion = textureAtlas.findRegion("bluedog");

        coinTextureRegion = textureAtlas.findRegion("coin");
        redCoinTextureRegion = textureAtlas.findRegion("redcoin");
        greenCoinTextureRegion = textureAtlas.findRegion("greencoin");
        blueCoinTextureRegion = textureAtlas.findRegion("bluecoin");
        colorSwapTextureRegion = textureAtlas.findRegion("coin");

        spikeTextureRegion = textureAtlas.findRegion("spike");
        restartButton = itemAtlas.findRegion("restart");

        TextureRegion[] dogList = {
                redDogTextureRegion,
                greenDogTextureRegion,
                blueDogTextureRegion
        };

        TextureRegion[] coinList = {
                redCoinTextureRegion,
                greenCoinTextureRegion,
                blueCoinTextureRegion
        };


        playerDog = new Dog(300,50,50,WORLD_WIDTH/2,WORLD_HEIGHT/10,dogList);
        coinSpawner = new Spawner(0,1f,30,30,coinTextureRegion,coinList);
        spikeSpawner = new Spawner(0,1.5f,100,30,spikeTextureRegion);
        colorSwapSpawner = new Spawner(0,25f,30,30,colorSwapTextureRegion);

        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        batch.begin();
        renderBackground();

        playerDog.detectInput(deltaTime,WORLD_WIDTH);
        spikeDetector.detectCollisions(spikeSpawner.getEntities(), playerDog);
        coinDetector.detectCollisions(coinSpawner.getEntities(), playerDog);
        colorSwapDetector.detectCollisions(colorSwapSpawner.getEntities(), playerDog);

        spikeSpawner.render(deltaTime,batch);
        spikeSpawner.spawn(deltaTime,WORLD_HEIGHT);
        coinSpawner.render(deltaTime,batch);
        coinSpawner.spawn(deltaTime,WORLD_HEIGHT);
        colorSwapSpawner.render(deltaTime,batch);
        colorSwapSpawner.spawn(deltaTime,WORLD_HEIGHT);

        font.setColor(new Color(0,1,0,1));
        font.getData().setScale(1.5f,1.5f);
        font.draw(batch,"HP :  "+ playerDog.getLifePoint(),20,550);
        font.draw(batch,"Scores :  "+ playerDog.getScores(),20,520);
        font.draw(batch,"Color : "+playerDog.getColorText()+" ( id : "+playerDog.getColor()+")",20,490);

        playerDog.draw(batch);
        renderGameOver();

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {
        backgroundOffSet = 0;
        playerDog.setMovementSpeed(0);

        spikeSpawner.setMovementSpeed(0);
        spikeSpawner.setSpawnTimer(0);

        coinSpawner.setMovementSpeed(0);
        coinSpawner.setSpawnTimer(0);

        colorSwapSpawner.setMovementSpeed(0);
        colorSwapSpawner.setSpawnTimer(0);
    }

    @Override
    public void resume() {
        backgroundOffSet ++;
        playerDog.setMovementSpeed(300);
        spikeSpawner.setMovementSpeed(50);
        coinSpawner.setMovementSpeed(50);
        colorSwapSpawner.setMovementSpeed(50);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void renderGameOver(){
        if (playerDog.getState() == Dog.DOG_OVER) {
            pause();
            Rectangle restartBounds = new Rectangle(200,250,restartButton.getRegionWidth(),restartButton.getRegionHeight());
            batch.draw(restartButton,200,250);
            if (Gdx.input.justTouched()){
                touchPoint = new Vector2(Gdx.input.getX(),Gdx.input.getY());
                if (restartBounds.contains(touchPoint.x, touchPoint.y)){
                    game.setScreen(new GameScreen(game));
                    playerDog.setScores(0);
                }
            }
        }
    }

    public void renderBackground(){
        backgroundOffSet += 2;
        if (backgroundOffSet % WORLD_HEIGHT == 0){
            backgroundOffSet = 0;
        }
        batch.draw(background,0,-backgroundOffSet,WORLD_WIDTH,WORLD_HEIGHT);
        batch.draw(background,0,-backgroundOffSet+WORLD_HEIGHT,WORLD_WIDTH,WORLD_HEIGHT);
    }
}
