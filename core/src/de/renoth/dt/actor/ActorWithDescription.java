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
    protected GameWorld gameWorld;
    protected DescriptionBox descriptionBox;
    private DescriptionHoverListener hoverlistener;
    private int width, height;

    public ActorWithDescription(int x, int y, int width, int height, GameWorld gameWorld, Texture tex) {
        this.tex = tex;
        this.width = width;
        this.height = height;
        this.gameWorld = gameWorld;
        this.posX = x;
        this.posY = y;

        setPosition(x, y);

        setOrigin(x, y);
        setBounds(getX(), getY(), width, height);

        if (!(this instanceof AttackTypeSwitch)) {
            createDescriptionBox(getEntity());
        }
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

        if (entity.equals(gameWorld.hero) && gameWorld.heroStatsLabel != null) {
            gameWorld.heroStatsLabel.remove();
            gameWorld.addPlayerStats();
        }

        addListener(hoverlistener = new DescriptionHoverListener());
        descriptionBox = new DescriptionBox(posX, posY, Resources.descriptionBg, entity.getDescription(), gameWorld, hoverlistener);
        hoverlistener.setDescriptionBox(descriptionBox);
        descriptionBox.setVisible(false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(1, 1, 1, getColor().a);
        batch.draw(tex, getX(), getY(), width, height);
        batch.setColor(1, 1, 1, 1);
    }
}
