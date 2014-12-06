package de.renoth.dt.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.renoth.dt.domain.IDescribable;
import de.renoth.dt.res.Resources;
import de.renoth.dt.screen.game.GameWorld;

public abstract class ActorWithDescription extends Actor {
    public final Texture tex;
    private final int posX;
    private final int posY;
    private DescriptionHoverListener hoverlistener;
    private int width, height;
    protected GameWorld gameWorld;

    protected DescriptionBox descriptionBox;

    public ActorWithDescription(int x, int y, int width, int height, GameWorld gameWorld, Texture tex) {
        this.tex = tex;
        this.width = width;
        this.height = height;
        this.gameWorld = gameWorld;
        this.posX = x;
        this.posY = y;

        setPosition(x,y);

        setOrigin(x, y);
        setBounds(getX(), getY(), width, height);

        createDescriptionBox(getEntity());
    }

    protected abstract IDescribable getEntity();

    public void createDescriptionBox(IDescribable entity) {
        if (descriptionBox != null) {
            descriptionBox.setVisible(false);
            descriptionBox.setLabelsInivisbleAndDispose();
            removeListener(hoverlistener);
            hoverlistener = null;
            descriptionBox.remove();
        }
        if (entity == null) {
            if (descriptionBox != null) {
                descriptionBox.setVisible(false);

                removeListener(hoverlistener);
                hoverlistener = null;
                descriptionBox.remove();
            }
            return;
        }
        addListener(hoverlistener = new DescriptionHoverListener());
        descriptionBox = new DescriptionBox(posX,posY,Resources.descriptionBg, entity.getDescription(), gameWorld, hoverlistener);
        hoverlistener.setDescriptionBox(descriptionBox);
        descriptionBox.setVisible(false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(tex, getX(), getY(), width, height);


    }
}
