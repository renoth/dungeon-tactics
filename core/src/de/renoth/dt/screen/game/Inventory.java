package de.renoth.dt.screen.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.renoth.dt.actor.DemoActor;
import de.renoth.dt.res.Resources;

public class Inventory {
    public Inventory(GameWorld gameWorld) {
        gameWorld.stage.addActor(new DemoActor(0, 0, 300, 800, gameWorld, Resources.bgMenu));
    }
}
