package de.renoth.dt.screen.game;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import de.renoth.dt.actor.EnemyActor;
import de.renoth.dt.actor.SimpleActor;
import de.renoth.dt.common.Constants;
import de.renoth.dt.domain.Enemy;
import de.renoth.dt.domain.Hero;
import de.renoth.dt.domain.Item;
import de.renoth.dt.domain.factory.EnemyFactory;
import de.renoth.dt.res.Resources;

import java.util.ArrayList;

public class GameWorld {

    private final World world;
    public final GameStage stage;
    public RayHandler rayhandler;

    public Item selectedItem;
    private SimpleActor selectedItemActor;
    public ArrayList<EnemyActor> enemyActors;
    public Hero hero;
    public HeroActor heroActor;

    public GameWorld(GameStage stage, OrthographicCamera camera) {
        this.stage = stage;
        world = new World(new Vector2(0.5f, 0), true);

        createRayhandler(camera);

        addMenu();
        addPlayer();
        addSelectedItemIcon();
        addInitialEnemies();
    }

    private void addInitialEnemies() {
        enemyActors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Enemy e = EnemyFactory.createRandomEnemy();
            EnemyActor ea = new EnemyActor(600 + i * 100, 336, 64, 64, this, e.getType().getTexture(), e);
            stage.bg.addActor(ea);
            enemyActors.add(ea);
        }
    }

    private void addSelectedItemIcon() {
        stage.fg.addActor(selectedItemActor = new SimpleActor(0,0,Constants.ITEM_SIZE,Constants.ITEM_SIZE, this, Resources.item) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(selectedItem.texture, Gdx.input.getX(), 800 - Gdx.input.getY(), Constants.ITEM_SIZE, Constants.ITEM_SIZE);
            }
        });
        selectedItemActor.setVisible(false);
    }

    private void addPlayer() {
        hero = new Hero("Nina");
        stage.bg.addActor(heroActor = new HeroActor(500, 336, 64, 128, this, Resources.hero1, hero));
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

    public void setSelectedItem(Item selectedItem) {
        selectedItemActor.setVisible(selectedItem != null);
        this.selectedItem = selectedItem;
    }

    public void shiftEnemiesByOne() {
        for (Actor actor : enemyActors) {
            actor.addAction(Actions.moveTo(actor.getX() - 100, actor.getY(), 1));
        }
    }

    public void addNewEnemy() {
        //enemyActors.add(EnemyFactory.createRandomEnemy());
    }

    public void enemiesAttack() {
        enemyActors.get(0).getEnemy().attack(hero);
    }
}
