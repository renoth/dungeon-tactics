package de.renoth.dt.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Timer;
import de.renoth.dt.common.GameStats;
import de.renoth.dt.domain.Enemy;
import de.renoth.dt.domain.IDescribable;
import de.renoth.dt.screen.GameScreen;
import de.renoth.dt.screen.game.GameWorld;

public class EnemyActor extends ActorWithDescription {
    private Enemy enemy;
    private boolean waitForEnemy;

    public EnemyActor(int x, int y, int width, int height, final GameWorld gameWorld, Texture tex, Enemy e) {
        super(x, y, width, height, gameWorld, tex);
        this.enemy = e;

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (EnemyActor.this.isHittableByHero(gameWorld)) {
                    boolean enemyDied = false;
                    if (enemy.takeDamage(gameWorld.hero, EnemyActor.this) <= 0) {
                        enemyDied = true;
                        EnemyActor.this.die();
                    } else {
                        createDescriptionBox(enemy);
                    }

                    if (!enemyDied) {
                        //wait for 1 second
                        waitForEnemy = true;
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                gameWorld.enemiesAttack();
                                waitForEnemy = false;
                            }
                        }, 0.5f);
                    }
                }
                return false;
            }
        });

        createDescriptionBox(enemy);
    }

    private void die() {
        remove();
        GameStats.killCount++;
        gameWorld.enemyActors.remove(this);
        //TODO spawn blood

        descriptionBox.destroy();

        gameWorld.shiftEnemiesByOne();

        gameWorld.addNewEnemy();

        gameWorld.inventory.spawnNewItemPerhaps(enemy);

        gameWorld.hero.addXP((int) enemy.getXp());

        GameScreen.getGameWorld().heroActor.createDescriptionBox(gameWorld.hero);
    }

    private boolean isHittableByHero(GameWorld gameWorld) {
        if (waitForEnemy) {
            return false;
        }
        if (gameWorld.enemyActors.get(0).equals(this)) {
            return true;
        }
        //TODO check for side attacks
        return false;
    }

    @Override
    protected IDescribable getEntity() {
        return enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
