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
import com.mygdx.game.player.Dog;
import com.mygdx.game.entitys.Spike;

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
    private LinkedList<Spike> spikeList;
    private LinkedList<Coin> coinList;

    private BitmapFont font = new BitmapFont();
    private Vector2 touchPoint;
    private RGBDog game;

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
        playerDog.detectSpikeCollisions(spikeList);
        playerDog.detectCoinCollisions(coinList);

        Spike.renderSpike(deltaTime,spikeList,batch);
        Spike.spawnSpike(deltaTime,spikeList,WORLD_HEIGHT,spikeTextureRegion);
        Coin.renderCoin(deltaTime,coinList,batch);
        Coin.spawnCoin(deltaTime,coinList,WORLD_HEIGHT,coinTextureRegion);

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
        Spike.setSpikeMovementSpeed(spikeList,0);
        Coin.setCoinMovementSpeed(coinList,0);
        Spike.spikeSpawnTimer = 0;
        Coin.coinSpawnTimer = 0;
    }

    @Override
    public void resume() {
        backgroundOffSet ++;
        playerDog.setMovementSpeed(300);
        Spike.setSpikeMovementSpeed(spikeList,50);
        Coin.setCoinMovementSpeed(coinList,50);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
