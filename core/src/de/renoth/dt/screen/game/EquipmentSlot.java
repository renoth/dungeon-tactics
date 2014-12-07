package de.renoth.dt.screen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import de.renoth.dt.common.Constants;
import de.renoth.dt.domain.enums.ItemType;
import de.renoth.dt.res.Resources;

public class EquipmentSlot extends InventorySlot {
    public final ItemType acceptedItemType;

    public EquipmentSlot(int x, int y, int width, int height, GameWorld gameWorld, Texture texture, ItemType acceptedItemType) {
        super(x, y, width, height, gameWorld, texture);

        this.acceptedItemType = acceptedItemType;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw((gameWorld.selectedItem != null && gameWorld.selectedItem.itemType == acceptedItemType) ? Resources.inventoryAccept : tex,
                getX(), getY(), Constants.INVENTORY_GRID_SIZE, Constants.INVENTORY_GRID_SIZE);

        drawItem(batch);
    }
}
