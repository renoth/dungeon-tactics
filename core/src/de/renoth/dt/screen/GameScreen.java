package de.renoth.dt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import de.renoth.dt.DungeonTacticsGame;
import de.renoth.dt.res.Resources;
import de.renoth.dt.screen.game.GameWorld;

public class GameScreen extends BaseScreen {

    private Matrix4 debugMatrix;
    private static GameWorld gameWorld;

    public static GameWorld getGameWorld() {
        return gameWorld;
    }

    public GameScreen(DungeonTacticsGame dungeonTacticsGame) {
        super(dungeonTacticsGame);

        gameWorld = new GameWorld(stages[0], camera);

        viewport = new FitViewport(1280, 800, camera);
        stages[0].setViewport(viewport);
        stages[0].getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        setInputProcessor();
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
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            ((OrthographicCamera)stages[0].getCamera()).zoom *= 1.03;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            ((OrthographicCamera)stages[0].getCamera()).zoom *= 0.97;
        }
    }
}
