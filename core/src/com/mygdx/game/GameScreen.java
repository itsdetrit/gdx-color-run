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
import com.mygdx.game.entitys.Spawner;
import com.mygdx.game.player.Dog;
import com.mygdx.game.entitys.Spike;
import com.mygdx.game.entitys.Entity;

import java.util.LinkedList;

public class GameScreen implements Screen {

    private Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private TextureAtlas textureAtlas,itemAtlas;
    private TextureRegion background;
    private TextureRegion dogTextureRegion,coinTextureRegion,spikeTextureRegion;
    private TextureRegion restartButton;

    private int backgroundOffSet;

    private final int WORLD_WIDTH = 800;
    private final int WORLD_HEIGHT = 600;

    private Dog playerDog;
    private LinkedList<Entity> spikeList;
    private LinkedList<Entity> coinList;

    private BitmapFont font = new BitmapFont();
    private Vector2 touchPoint;
    private RGBDog game;
    private Coin coin = new Coin();
    private Spike spike = new Spike();
    private Spawner coinSpawner, spikeSpawner;

    public GameScreen(RGBDog game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        textureAtlas = new TextureAtlas("image.atlas");
        itemAtlas = new TextureAtlas("items.atlas");
        background = textureAtlas.findRegion("road2");
        backgroundOffSet = 0;

        dogTextureRegion = textureAtlas.findRegion("dog");
        coinTextureRegion = textureAtlas.findRegion("coin");
        spikeTextureRegion = textureAtlas.findRegion("spike");

        restartButton = itemAtlas.findRegion("restart");

        playerDog = new Dog(300,50,50,WORLD_WIDTH/2,WORLD_HEIGHT/10,dogTextureRegion);
        coinSpawner = new Spawner(0,1f,30,30);
        spikeSpawner = new Spawner(0,1f,100,30);
        spikeList = new LinkedList<>();
        coinList = new LinkedList<>();

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
        spike.detectCollisions(spikeList, playerDog);
        coin.detectCollisions(coinList, playerDog);

        spikeSpawner.render(deltaTime,spikeList,batch);
        spikeSpawner.spawn(deltaTime,spikeList,WORLD_HEIGHT,spikeTextureRegion);
        coinSpawner.render(deltaTime,coinList,batch);
        coinSpawner.spawn(deltaTime,coinList,WORLD_HEIGHT,coinTextureRegion);

        font.setColor(new Color(0,1,0,1));
        font.getData().setScale(1.5f,1.5f);
        font.draw(batch,"HP :  "+ playerDog.getLifePoint(),20,550);

        font.setColor(new Color(0,1,0,1));
        font.getData().setScale(1.5f,1.5f);
        font.draw(batch,"Scores :  "+ playerDog.getScores(),20,520);

        playerDog.draw(batch);
        presentGameOver();

        batch.end();
    }
    private void presentGameOver(){
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

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {
        backgroundOffSet = 0;
        playerDog.setMovementSpeed(0);
        spikeSpawner.setMovementSpeed(spikeList,0);
        coinSpawner.setMovementSpeed(coinList,0);
        spikeSpawner.setSpawnTimer(0);
        coinSpawner.setSpawnTimer(0);
    }

    @Override
    public void resume() {
        backgroundOffSet ++;
        playerDog.setMovementSpeed(300);
        spikeSpawner.setMovementSpeed(spikeList,50);
        coinSpawner.setMovementSpeed(coinList,50);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
