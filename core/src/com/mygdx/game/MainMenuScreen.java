package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public class MainMenuScreen extends ScreenAdapter {
    ColorRun game;
    Vector2 touchPoint;
    BitmapFont font = new BitmapFont();
    OrthographicCamera camera;

    public MainMenuScreen(ColorRun game){
        this.game = game;
        touchPoint = new Vector2();
        camera = new OrthographicCamera(800,600);
        camera.position.set(800/2,600/2,0);
    }

    public void render(float delta){
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        font.setColor(1,0,0,1);
        font.getData().setScale(3f);
        font.draw(game.batch, "Tap screen to begin",300,400);
        game.batch.end();
        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
        }
    }
}
