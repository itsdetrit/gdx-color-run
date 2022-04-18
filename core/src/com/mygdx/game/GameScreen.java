package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Texture background;
    private int backgroundOffSet;
    private final int WORLD_WIDTH = 800;
    private final int WORLD_HEIGHT = 600;

    public GameScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        background = new Texture("road2.png");
        backgroundOffSet = 0;
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        backgroundOffSet ++;
        if (backgroundOffSet % WORLD_HEIGHT == 0){
            backgroundOffSet = 0;
        }
        batch.draw(background,0,-backgroundOffSet,WORLD_WIDTH,WORLD_HEIGHT);
        batch.draw(background,0,-backgroundOffSet+WORLD_HEIGHT,WORLD_WIDTH,WORLD_HEIGHT);
        batch.end();
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
