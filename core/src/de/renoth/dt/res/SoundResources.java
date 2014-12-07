package de.renoth.dt.res;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundResources {

    public static Sound gitarrenmusik;
    public static Sound blipPut;
    public static Sound blipSelect;
    public static Sound club;
    public static Sound explosion;
    public static Sound gameOver;
    public static Sound takeDamage;
    public static Sound knife;
    public static Sound levelUp;
    public static Sound powerUp;
    public static Sound sword;

    public static void init() {
        gitarrenmusik = Gdx.audio.newSound(Gdx.files.internal("music/gitarrenmusik.mp3"));
        blipPut = Gdx.audio.newSound(Gdx.files.internal("sound/blip_put.wav"));
        blipSelect = Gdx.audio.newSound(Gdx.files.internal("sound/blip_select.wav"));
        club = Gdx.audio.newSound(Gdx.files.internal("sound/club.wav"));
        explosion = Gdx.audio.newSound(Gdx.files.internal("sound/explosion.wav"));
        gameOver = Gdx.audio.newSound(Gdx.files.internal("sound/game_over.wav"));
        takeDamage = Gdx.audio.newSound(Gdx.files.internal("sound/hero_take_damage.wav"));
        knife = Gdx.audio.newSound(Gdx.files.internal("sound/knife.wav"));
        levelUp = Gdx.audio.newSound(Gdx.files.internal("sound/level_up.wav"));
        powerUp = Gdx.audio.newSound(Gdx.files.internal("sound/power_up.wav"));
        sword = Gdx.audio.newSound(Gdx.files.internal("sound/sword.wav"));
    }
}
