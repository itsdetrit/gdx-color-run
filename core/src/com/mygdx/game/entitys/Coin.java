package com.mygdx.game.entitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.player.Dog;

public class Coin extends Entity{
    private Sound hitCoin,wrongCoin;

    public Coin() {
        super();
        hitCoin = Gdx.audio.newSound(Gdx.files.internal("sound/coinsound.mp3"));
        wrongCoin = Gdx.audio.newSound(Gdx.files.internal("sound/miss.mp3"));
    }

    @Override
    public void onDraw(Batch batch) {

    }

    @Override
    public void onDetectCollisions(Dog player, int id) {
        if(player.getColor() == id){
            player.addScore(1);
            hitCoin.play(0.015f);
        } else {
            if (player.getScores() > 0){
                player.removeScore(1);
            }
            wrongCoin.play(0.15f);
        }
    }

}
