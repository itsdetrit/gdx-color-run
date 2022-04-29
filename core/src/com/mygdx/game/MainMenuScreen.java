package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen extends ScreenAdapter {
    RGBDog game;
    Vector2 touchPoint;
    BitmapFont font = new BitmapFont();
    OrthographicCamera camera;
    private TextureAtlas itemsAtlas;
    private TextureRegion mainMenuBackGround;
    private Viewport viewport;

    public MainMenuScreen(RGBDog game){
        this.game = game;
        touchPoint = new Vector2();
        camera = new OrthographicCamera(800,600);
        viewport = new StretchViewport(800,600,camera);
        itemsAtlas = new TextureAtlas("items.atlas");
        mainMenuBackGround = itemsAtlas.findRegion("mainmenubg");
    }

    public void render(float delta){
        viewport.update(800,600,true);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.batch.draw(mainMenuBackGround,0,0);
        game.batch.end();
        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
        }
    }
}
