package de.renoth.dt.screen.game;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.renoth.dt.actor.ActorWithDescription;
import de.renoth.dt.res.Resources;

public class GameWorld {

    private final World world;
    public final Stage stage;
    public RayHandler rayhandler;

    public GameWorld(Stage stage) {
        this.stage = stage;
        world = new World(new Vector2(0.5f, 0), true);

        addMenu();
        addPlayer();

    }

    private void addPlayer() {
        stage.addActor(new ActorWithDescription(500, 336, 64, 128, this, Resources.hero1));
    }

    private void addMenu() {
        Inventory inventory = new Inventory(this);

    }

    public World getWorld() {
        return world;
    }

    public void createRayhandler(OrthographicCamera cam) {
        rayhandler = new RayHandler(world);
        rayhandler.setCombinedMatrix(cam.combined);
        rayhandler.setCulling(true);
        rayhandler.setAmbientLight(new Color(0,0,0,0.8f));
    }

    public void updateRayhandler(OrthographicCamera cam) {
        rayhandler.setCombinedMatrix(cam.combined);
        rayhandler.updateAndRender();
    }

}
