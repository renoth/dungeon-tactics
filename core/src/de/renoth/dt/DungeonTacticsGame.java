package de.renoth.dt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.renoth.dt.res.Resources;
import de.renoth.dt.screen.GameScreen;
import de.renoth.dt.screen.IntroScreen;
import de.renoth.dt.screen.MenuScreen;

public class DungeonTacticsGame extends Game {
	SpriteBatch batch;

    public MenuScreen menuScreen;
    public GameScreen gameScreen;

    @Override
	public void create () {
        Resources.init();

        IntroScreen intro = new IntroScreen(this);
        gameScreen = new GameScreen(this);
        menuScreen = new MenuScreen(this);

        setScreen(gameScreen);
	}
}
