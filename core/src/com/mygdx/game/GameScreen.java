package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class GameScreen implements Screen {

    private Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private TextureRegion background;
    private TextureRegion dogTextureRegion,coinTextureRegion,spikeTextureRegion;

    private int backgroundOffSet;

    private final int WORLD_WIDTH = 800;
    private final int WORLD_HEIGHT = 600;

    private Dog playerDog;
    private LinkedList<Spike> spikeList;
    private LinkedList<Coin> coinList;
    private BitmapFont font = new BitmapFont();

    public GameScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        textureAtlas = new TextureAtlas("image.atlas");
        background = textureAtlas.findRegion("road2");
        backgroundOffSet = 0;

        dogTextureRegion = textureAtlas.findRegion("dog");
        coinTextureRegion = textureAtlas.findRegion("coin");
        spikeTextureRegion = textureAtlas.findRegion("spike");

        playerDog = new Dog(230,50,50,WORLD_WIDTH/2,WORLD_HEIGHT/10,dogTextureRegion);
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
        renderBackground(deltaTime);

        playerDog.detectInput(deltaTime,WORLD_WIDTH);
        playerDog.detectSpikeCollisions(spikeList);
        playerDog.detectCoinCollisions(coinList);

        Spike.renderSpike(deltaTime,spikeList,batch);
        Spike.spawnSpike(deltaTime,spikeList,WORLD_HEIGHT,spikeTextureRegion);
        Coin.renderCoin(deltaTime,coinList,batch);
        Coin.spawnCoin(deltaTime,coinList,WORLD_HEIGHT,coinTextureRegion);

        font.setColor(new Color(0,1,0,1));
        font.getData().setScale(1.5f,1.5f);
        font.draw(batch,"HP :  "+ Dog.lifePoint,20,550);

        font.setColor(new Color(0,1,0,1));
        font.getData().setScale(1.5f,1.5f);
        font.draw(batch,"Scores :  "+ Dog.scores,20,520);

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
}
