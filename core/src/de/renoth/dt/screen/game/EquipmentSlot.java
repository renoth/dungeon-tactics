package de.renoth.dt.screen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import de.renoth.dt.actor.SimpleActor;
import de.renoth.dt.common.Constants;
import de.renoth.dt.domain.ItemClass;
import de.renoth.dt.res.Resources;

public class EquipmentSlot extends InventorySlot {
    public final ItemClass itemClass;

    public EquipmentSlot(int x, int y, int width, int height, GameWorld gameWorld, Texture texture, ItemClass itemClass) {
        super(x, y, width, height, gameWorld, texture);

        this.itemClass = itemClass;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw((gameWorld.selectedItem != null && gameWorld.selectedItem.itemClass == itemClass) ? Resources.inventoryAccept : tex,
                getX(), getY(), Constants.INVENTORY_GRID_SIZE, Constants.INVENTORY_GRID_SIZE);

        drawItem(batch);
    }
}
