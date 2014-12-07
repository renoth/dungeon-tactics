package de.renoth.dt;

import com.badlogic.gdx.Game;
import de.renoth.dt.res.Resources;
import de.renoth.dt.res.SoundResources;
import de.renoth.dt.screen.GameScreen;

public class DungeonTacticsGame extends Game {
    public GameScreen gameScreen;

    @Override
    public void create() {
        Resources.init();

        SoundResources.init();

        gameScreen = new GameScreen(this);

        setScreen(gameScreen);
    }
}
