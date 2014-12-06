package de.renoth.dt.screen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import de.renoth.dt.common.Constants;
import de.renoth.dt.domain.ItemClass;
import de.renoth.dt.res.Resources;

public class EquipmentSlot extends InventorySlot {
    public final ItemClass acceptedItemClass;

    public EquipmentSlot(int x, int y, int width, int height, GameWorld gameWorld, Texture texture, ItemClass acceptedItemClass) {
        super(x, y, width, height, gameWorld, texture);

        this.acceptedItemClass = acceptedItemClass;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw((gameWorld.selectedItem != null && gameWorld.selectedItem.itemClass == acceptedItemClass) ? Resources.inventoryAccept : tex,
                getX(), getY(), Constants.INVENTORY_GRID_SIZE, Constants.INVENTORY_GRID_SIZE);

        drawItem(batch);
    }
}
