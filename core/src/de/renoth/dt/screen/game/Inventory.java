package de.renoth.dt.screen.game;

import de.renoth.dt.actor.ActorWithDescription;
import de.renoth.dt.res.Resources;

public class Inventory {
    public Inventory(GameWorld gameWorld) {
        gameWorld.stage.addActor(new ActorWithDescription(0, 0, 300, 800, gameWorld, Resources.bgMenu));
    }
}
