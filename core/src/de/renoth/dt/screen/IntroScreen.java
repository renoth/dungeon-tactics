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
        title.setPosition((int) (Gdx.graphics.getWidth() * 0.5F),(int) (Gdx.graphics.getHeight() * 0.7F));

        Label bla = new Label("Ludum Dare 31", new Label.LabelStyle(Resources.font36, new Color(1f, 1f, 1f, 1f)));
        bla.setPosition((int) (Gdx.graphics.getWidth() * 0.5F),(int) (Gdx.graphics.getHeight() * 0.6F));


        stages[0].addActor(title);
        stages[0].addActor(bla);
    }

    @Override
    protected void onRender() {
        if (Gdx.input.justTouched()) {
            game.setScreen(game.menuScreen);
        }
    }
}
