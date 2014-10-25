package de.renoth.dt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.renoth.dt.DungeonTacticsGame;
import de.renoth.dt.res.Resources;

public class IntroScreen extends BaseScreen {
    public IntroScreen(DungeonTacticsGame dungeonTacticsGame) {
        super(dungeonTacticsGame);

        Label title = new Label("Dungeon Tactics", new Label.LabelStyle(Resources.font36, new Color(1f, 1f, 1f, 0.8f)));
        title.setPosition(100,100);

        stage.addActor(title);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            game.setScreen(game.menuScreen);
        }

        super.render(delta);
    }
}
