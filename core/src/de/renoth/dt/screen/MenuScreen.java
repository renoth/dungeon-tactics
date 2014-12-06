package de.renoth.dt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.renoth.dt.DungeonTacticsGame;
import de.renoth.dt.res.Resources;

public class MenuScreen extends BaseScreen {
    private final TextButton playButton;
    private final TextButton exitButton;
    private Table table = new Table();


    public MenuScreen(final DungeonTacticsGame game) {
        super(game);

        playButton = new TextButton("Play", Resources.mainMenuSkin);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.gameScreen);
            }
        });

        exitButton = new TextButton("Exit", Resources.mainMenuSkin);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitApplication();
            }
        });

        table.add(playButton).width(200).pad(10).row();
        table.add(exitButton).width(200).pad(10).row();

        table.setFillParent(true);

        stage.addActor(table);
    }

    @Override
    protected void onRender() {

    }
}
