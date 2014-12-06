package de.renoth.dt.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
                System.out.println("Hit");
                if (EnemyActor.this.isHittableByHero(gameWorld)) {
                    /*if (enemy.takeDamage(gameWorld.hero) <= 0) {
                        EnemyActor.this.die();
                        System.out.println("Enemy dies");
                    } else {
                        System.out.println("Update info");
                        createDescriptionBox(enemy, true);
                    }*/
                }

                //TODO enemies turn

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        createDescriptionBox(enemy);
    }

    private void die() {
        remove();
        //gameWorld.enemyActors.remove(this);
        //descriptionBox.destroy();
        //TODO animate shift, new enemies
    }

    private boolean isHittableByHero(GameWorld gameWorld) {
/*        if (gameWorld.enemyActors.get(0).equals(this)) {
            return true;
        }*/
        //TODO check for side attacks
        return true;
    }

    @Override
    protected IDescribable getEntity() {
        return enemy;
    }
}
