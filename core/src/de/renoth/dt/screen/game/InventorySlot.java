package de.renoth.dt.screen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.renoth.dt.actor.SimpleActor;
import de.renoth.dt.common.Constants;
import de.renoth.dt.domain.Item;

/**
 * User: hans
 * Date: 12/6/14
 */
public class InventorySlot extends SimpleActor {

    public Item item;

    public InventorySlot(int x, int y, int width, int height, GameWorld gameWorld, Texture tex) {
        super(x,y,width,height,gameWorld, tex);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (item != null) {
            batch.draw(item.texture, getX(), getY(), Constants.ITEM_SIZE, Constants.ITEM_SIZE);
        }
    }
}
