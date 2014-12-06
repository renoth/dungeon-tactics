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

    private final Box2DDebugRenderer debugRenderer;
    private Matrix4 debugMatrix;
    private GameWorld gameWorld;

    public GameScreen(DungeonTacticsGame dungeonTacticsGame) {
        super(dungeonTacticsGame);

        gameWorld = new GameWorld(stages[0]);

        gameWorld.createRayhandler(camera);

        viewport = new FitViewport(1280, 800, camera);
        stages[0].setViewport(viewport);
        stages[0].getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);


        //debug renderer and camera matrix (same as viewport matrix)
        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.setDrawBodies(true);
        debugRenderer.setDrawInactiveBodies(true);
        debugMatrix = new Matrix4(stages[0].getCamera().combined);

        setInputProcessor();
    }

    @Override
    protected void onRender() {
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            exitApplication();
        }

        handleInput();

        gameWorld.updateRayhandler(camera);

        debugMatrix = new Matrix4(stages[0].getCamera().combined);
        debugRenderer.render(gameWorld.getWorld() ,debugMatrix);
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
/*        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.rotate(-0.5f, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.rotate(0.5f, 0, 0, 1);
        }*/
    }
}
