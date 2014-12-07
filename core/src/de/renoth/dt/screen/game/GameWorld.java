package de.renoth.dt.screen.game;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import de.renoth.dt.actor.*;
import de.renoth.dt.common.Constants;
import de.renoth.dt.common.GameStats;
import de.renoth.dt.domain.Enemy;
import de.renoth.dt.domain.Hero;
import de.renoth.dt.domain.Item;
import de.renoth.dt.domain.enums.AttackType;
import de.renoth.dt.domain.factory.EnemyFactory;
import de.renoth.dt.res.Resources;

import java.util.ArrayList;

public class GameWorld {

    public final GameStage stage;
    private final World world;
    public RayHandler rayhandler;

    public Item selectedItem;
    public ArrayList<EnemyActor> enemyActors;
    public Hero hero;
    public HeroActor heroActor;
    public Inventory inventory;
    public boolean heroDied = false;
    private SimpleActor selectedItemActor;
    private DamageLabelActor damageLabelActor;
    public HeroStatsLabel heroStatsLabel;

    public GameWorld(GameStage stage, OrthographicCamera camera) {
        this.stage = stage;
        world = new World(new Vector2(0.5f, 0), true);

        createRayhandler(camera);

        addMenu();
        addAttackTypeMenu();

        addPlayer();
        addPlayerStats();
        addSelectedItemIcon();
        addInitialEnemies();

        addDamageLabelActors();

        //Resources.gitarrenmusik.loop(0.5f);
    }

    public void addPlayerStats() {
        stage.fg.addActor(heroStatsLabel = new HeroStatsLabel(0,600,Resources.descriptionBg, hero.getDescription(), this, null));
    }

    private void addAttackTypeMenu() {
        stage.bg.addActor(new AttackTypeSwitch(140, 205, 40, 40, this, Resources.club, AttackType.BLUDGEON));
        stage.bg.addActor(new AttackTypeSwitch(180, 205, 40, 40, this, Resources.sword, AttackType.SLICE));
        stage.bg.addActor(new AttackTypeSwitch(220, 205, 40, 40, this, Resources.knife, AttackType.STAB));
    }

    private void addDamageLabelActors() {
        damageLabelActor = new DamageLabelActor(this);
    }

    private void addInitialEnemies() {
        enemyActors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Enemy e = EnemyFactory.createRandomEnemy();
            EnemyActor ea = new EnemyActor(600 + i * 100, 336, 64, 64, this, e.getType().getTexture(), e);
            stage.bg.addActor(ea);
            enemyActors.add(ea);
        }
    }

    private void addSelectedItemIcon() {
        stage.fg.addActor(selectedItemActor = new SimpleActor(0, 0, Constants.ITEM_SIZE, Constants.ITEM_SIZE, this, Resources.item) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(selectedItem.texture, Gdx.input.getX(), 800 - Gdx.input.getY(), Constants.ITEM_SIZE, Constants.ITEM_SIZE);
                batch.draw(selectedItem.rarityType.getTexture(), Gdx.input.getX(), 800 - Gdx.input.getY(), Constants.ITEM_SIZE, Constants.ITEM_SIZE);
            }
        });
        selectedItemActor.setVisible(false);
    }

    private void addPlayer() {
        hero = new Hero("Nina");
        stage.bg.addActor(heroActor = new HeroActor(500, 336, 64, 128, this, Resources.hero1, hero));
    }

    private void addMenu() {
        stage.bg.addActor(new SimpleActor(280, 0, 1000, 800, this, Resources.background));
        inventory = new Inventory(this);
    }

    public World getWorld() {
        return world;
    }

    public void createRayhandler(OrthographicCamera cam) {
        rayhandler = new RayHandler(world);
        rayhandler.setCombinedMatrix(cam.combined);
        rayhandler.setCulling(true);
        rayhandler.setAmbientLight(new Color(0, 0, 0, 0.8f));
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
        Enemy e = EnemyFactory.createRandomEnemy();
        EnemyActor ea = new EnemyActor(600 + (enemyActors.size() + 1) * 100, 336, 64, 64, this, e.getType().getTexture(), e);
        enemyActors.add(ea);
        stage.bg.addActor(ea);
        if (GameStats.killCount % 10 == 0) {
            EnemyFactory.increaseBaseLevelByOne();
        }
        ea.setColor(1, 1, 1, 0);

        ea.addAction(Actions.alpha(1f, 1));
        ea.addAction(Actions.moveTo(ea.getX() - 100, ea.getY(), 1));
    }

    public void enemiesAttack() {
        enemyActors.get(0).getEnemy().attack(hero);
    }

    public DamageLabelActor getDamageLabelActor() {
        return damageLabelActor;
    }
}
