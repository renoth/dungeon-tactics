package de.renoth.dt.actor;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import de.renoth.dt.common.GameStats;
import de.renoth.dt.domain.Enemy;
import de.renoth.dt.domain.IDescribable;
import de.renoth.dt.screen.game.GameWorld;

public class EnemyActor extends ActorWithDescription {
    private Enemy enemy;

    public EnemyActor(int x, int y, int width, int height, final GameWorld gameWorld, Texture tex, Enemy e) {
        super(x,y,width,height,gameWorld,tex);
        this.enemy = e;

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (EnemyActor.this.isHittableByHero(gameWorld)) {
                    if (enemy.takeDamage(gameWorld.hero, EnemyActor.this) <= 0) {
                        EnemyActor.this.die();
                    } else {
                        createDescriptionBox(enemy);
                    }
                    gameWorld.enemiesAttack();
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
    }

    private boolean isHittableByHero(GameWorld gameWorld) {
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
