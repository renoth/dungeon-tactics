package de.renoth.dt.actor;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.renoth.dt.res.Resources;
import de.renoth.dt.screen.game.GameWorld;

/**
 * User: hans
 * Date: 12/6/14
 */
public class DemoActor extends Actor {
    private final Body body;
    private final Texture tex;
    private final HoverListener hoverlistener;
    private PointLight light;
    private int width, height;

    public DemoActor(int x, int y,int width, int height, GameWorld gameWorld, Texture tex) {
        this.tex = tex;
        this.width = width;
        this.height = height;

        setPosition(x,y);

        setOrigin(x, y);
        setBounds(getX(), getY(), width, height);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        body = gameWorld.getWorld().createBody(bodyDef);

        body.setUserData(this);

        PolygonShape gb = new PolygonShape();
        gb.setAsBox(width/2,height/2, new Vector2(width/2, height/2), 0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = gb;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0f;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef);

/*        light = new PointLight(gameWorld.rayhandler, 20, Color.WHITE, 5f, 0, 0);
        light.attachToBody(body, width/2, height/2);*/

        gb.dispose();

        addListener(hoverlistener = new HoverListener());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (hoverlistener.isOver()) batch.draw(tex, getX(), getY(), width, height);
    }
}
