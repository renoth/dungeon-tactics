package de.renoth.dt.actor;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import de.renoth.dt.domain.Enemy;
import de.renoth.dt.domain.IDescribable;
import de.renoth.dt.screen.game.GameWorld;

public class EnemyActor extends ActorWithDescription {
    private  PointLight light;
    private  Body body;
    private Enemy enemy;

    public EnemyActor(int x, int y, int width, int height, final GameWorld gameWorld, Texture tex, Enemy e) {
        super(x,y,width,height,gameWorld,tex);
        this.enemy = e;

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Hit");
                if (EnemyActor.this.isHittableByHero(gameWorld)) {
                    if (enemy.takeDamage(gameWorld.hero) <= 0) {
                        EnemyActor.this.die();
                        System.out.println("Enemy dies");
                    } else {
                        System.out.println("Update info");
                        createDescriptionBox(enemy);
                    }
                    gameWorld.enemiesAttack();
                }
                return false;
            }
        });

        createDescriptionBox(enemy);

/*        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x + (float) Math.random() * 1, y + (float) Math.random() * 1);

        body = gameWorld.getGameWorld().createBody(bodyDef);

        light = new PointLight(gameWorld.rayhandler, 20, Color.RED, 50f, 0f, 0f);
        light.attachToBody(body, 0f, 0f);
        light.setXray(true);*/
    }

    private void die() {
        remove();
        gameWorld.enemyActors.remove(this);
        //TODO spawn blood

        descriptionBox.destroy();

        gameWorld.shiftEnemiesByOne();

        gameWorld.addNewEnemy();
        //TODO animate shift, new enemies
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
