package de.renoth.dt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import de.renoth.dt.DungeonTacticsGame;
import de.renoth.dt.common.Constants;
import de.renoth.dt.screen.game.GameStage;

public abstract class BaseScreen implements Screen {
    final SpriteBatch batch;
    final OrthographicCamera camera;
    final GameStage[] stages;
    FitViewport viewport;

    DungeonTacticsGame game;
    int stageCount = 1;

    protected BaseScreen(DungeonTacticsGame game) {
        this.game = game;

        stages = new GameStage[2];

        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        GameStage stage = new GameStage();

        stages[0] = stage;
    }

    void setInputProcessor() {
        Gdx.input.setInputProcessor(stages[0]);
    }

    @Override
    public void render(float delta) {
        stages[0].act();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);

        //batch.setProjectionMatrix(camera.projection);
        //batch.setTransformMatrix(camera.view);

        batch.begin();

        drawStages();

        onRender();

        batch.end();
    }

    private void drawStages() {
        for (int i = 0; i < stageCount; i++) {
            stages[i].draw();
        }
    }

    protected abstract void onRender();

    @Override
    public void resize (int width, int height) {
        stages[0].getViewport().update(width, height, true);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        for (Stage s : stages) {
            if (s != null) {
                s.dispose();
            }
        }
    }

    void exitApplication() {
        Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, false);
        Gdx.app.exit();
    }
}
