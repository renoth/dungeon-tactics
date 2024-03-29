package de.renoth.dt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.FitViewport;
import de.renoth.dt.DungeonTacticsGame;
import de.renoth.dt.screen.game.GameStage;
import de.renoth.dt.screen.game.GameWorld;

public class GameScreen extends BaseScreen {

    private static GameWorld gameWorld;
    private Matrix4 debugMatrix;

    public GameScreen(DungeonTacticsGame dungeonTacticsGame) {
        super(dungeonTacticsGame);

        gameWorld = new GameWorld(stages[0], camera);

        viewport = new FitViewport(1280, 800, camera);
        stages[0].setViewport(viewport);
        stages[0].getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        setInputProcessor();
    }

    public static GameWorld getGameWorld() {
        return gameWorld;
    }

    @Override
    protected void onRender() {
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            exitApplication();
        }

        handleInput();

        gameWorld.updateRayhandler(camera);
    }

    @Override
    void setInputProcessor() {
        Gdx.input.setInputProcessor(new InputMultiplexer(stages[0]));
    }

    private void handleInput() {
        if (gameWorld.heroDied && Gdx.input.isKeyPressed(Input.Keys.N)) {
            stages[0] = new GameStage();
            gameWorld = new GameWorld(stages[0], camera);
            setInputProcessor();
        }
    }
}
