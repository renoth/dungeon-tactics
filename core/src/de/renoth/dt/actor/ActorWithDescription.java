package de.renoth.dt.actor;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.renoth.dt.domain.StyledText;
import de.renoth.dt.res.Resources;
import de.renoth.dt.screen.game.GameWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hans
 * Date: 12/6/14
 */
public class ActorWithDescription extends Actor {
    private Body body;
    private final Texture tex;
    private final DescriptionHoverListener hoverlistener;
    private PointLight light;
    private int width, height;

    private DescriptionBox descriptionBox;

    public ActorWithDescription(int x, int y, int width, int height, GameWorld gameWorld, Texture tex) {
        this.tex = tex;
        this.width = width;
        this.height = height;

        setPosition(x,y);

        setOrigin(x, y);
        setBounds(getX(), getY(), width, height);

        descriptionBox = new DescriptionBox(x,y, Resources.descriptionBg, createDescription(), gameWorld);

        addListener(hoverlistener = new DescriptionHoverListener(descriptionBox));
    }

    private List<StyledText> createDescription() {
        ArrayList<StyledText> description = new ArrayList<>();

        description.add(new StyledText("Item  abc 123",Resources.mplus20, Color.CYAN));
        description.add(new StyledText("Item  abc 123",Resources.mplus20, Color.CYAN));
        description.add(new StyledText("Item  abc 123",Resources.mplus20, Color.CYAN));

        return description;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(tex, getX(), getY(), width, height);

        if(hoverlistener.isOver()) {
            descriptionBox.updatePositions();

            descriptionBox.draw(batch, parentAlpha);
        }
    }
}
