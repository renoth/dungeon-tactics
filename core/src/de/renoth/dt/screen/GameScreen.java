package de.renoth.dt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import de.renoth.dt.DungeonTacticsGame;

public class GameScreen extends BaseScreen {
    public GameScreen(DungeonTacticsGame dungeonTacticsGame) {
        super(dungeonTacticsGame);
    }

    @Override
    protected void onRender() {
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            exitApplication();
        }
    }
}
