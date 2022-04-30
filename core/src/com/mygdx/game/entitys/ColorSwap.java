package com.mygdx.game.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.player.Dog;

public class ColorSwap extends Entity{
    private Sound getColorSwap;

    public ColorSwap() {
        super();
        getColorSwap = Gdx.audio.newSound(Gdx.files.internal("sound/colorswap.mp3"));
    }

    @Override
    public void onDraw(Batch batch) {

    }

    @Override
    public void onDetectCollisions(Dog player, int id) {
        player.randomColor();
        player.addScore(10);
        getColorSwap.play(0.1f);
    }
}
