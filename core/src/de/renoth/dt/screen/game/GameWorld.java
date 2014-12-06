package de.renoth.dt.screen.game;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GameWorld {

    private final World world;
    public RayHandler rayhandler;

    public GameWorld() {
        world = new World(new Vector2(0.5f, 0), true);


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
