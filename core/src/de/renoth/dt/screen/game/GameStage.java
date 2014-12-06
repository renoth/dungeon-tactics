package de.renoth.dt.screen.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameStage extends Stage {

    private GameWorld world;

    public Group bg, fg;

    public GameStage() {
        fg = new Group();
        bg = new Group();
        addActor(bg);
        addActor(fg);
    }

    public GameWorld getWorld() {
        return world;

    }

    public void setWorld(GameWorld world) {
        this.world = world;
    }
}
