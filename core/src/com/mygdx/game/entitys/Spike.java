package com.mygdx.game.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.player.Dog;

public class Spike extends Entity{
    private Sound gameOver,hitSpike;

    public Spike() {
        super();
        gameOver = Gdx.audio.newSound(Gdx.files.internal("sound/gameover.mp3"));
        hitSpike = Gdx.audio.newSound(Gdx.files.internal("sound/hitspike.mp3"));
    }

    @Override
    public void onDraw(Batch batch) {

    }

    @Override
    public void onDetectCollisions(Dog player, int id) {
        player.removeLifePoint(1);
        hitSpike.play(0.2f);
        if (player.getLifePoint() == 0){
            hitSpike.pause();
            player.setState(Dog.DOG_OVER);
            gameOver.play(0.05f);
        }
    }

}
