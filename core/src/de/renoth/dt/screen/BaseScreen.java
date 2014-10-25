package de.renoth.dt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.renoth.dt.DungeonTacticsGame;
import de.renoth.dt.common.Constants;

public class BaseScreen implements Screen {
    final SpriteBatch batch;
    final OrthographicCamera camera;
    final Stage stage;

    DungeonTacticsGame game;

    protected BaseScreen(DungeonTacticsGame game) {
        this.game = game;

        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);

        batch.begin();

        onRender();

        batch.end();
    }

    protected void onRender() {
       stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
