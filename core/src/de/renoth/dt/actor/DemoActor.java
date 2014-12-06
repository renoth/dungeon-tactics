package de.renoth.dt.actor;

import box2dLight.PointLight;
import com.badlogic.gdx.Gdx;
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
    private Body body;
    private final Texture tex;
    private final HoverListener hoverlistener;
    private PointLight light;
    private int width, height;

    private DescriptionBox descriptionBox;

    public DemoActor(int x, int y,int width, int height, GameWorld gameWorld, Texture tex) {
        this.tex = tex;
        this.width = width;
        this.height = height;

        setPosition(x,y);

        setOrigin(x, y);
        setBounds(getX(), getY(), width, height);

        addListener(hoverlistener = new HoverListener());

        descriptionBox = new DescriptionBox(x + 10, y + 10, Resources.bgMenu);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(tex, getX(), getY(), width, height);

        if(hoverlistener.isOver()) {
            descriptionBox.setPosition(Gdx.input.getX(), 800 - Gdx.input.getY());
            descriptionBox.draw(batch, parentAlpha);
        }
    }
}
