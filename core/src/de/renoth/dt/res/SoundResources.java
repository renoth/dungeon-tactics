package de.renoth.dt.res;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundResources {

    public static Sound gitarrenmusik;

    public static void init() {
        gitarrenmusik = Gdx.audio.newSound(Gdx.files.internal("music/gitarrenmusik.mp3"));
    }
}
