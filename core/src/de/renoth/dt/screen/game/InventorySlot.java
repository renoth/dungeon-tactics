package de.renoth.dt.screen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import de.renoth.dt.actor.ActorWithDescription;
import de.renoth.dt.common.Constants;
import de.renoth.dt.domain.IDescribable;
import de.renoth.dt.domain.Item;

public class InventorySlot extends ActorWithDescription {

    private Item item;

    public InventorySlot(int x, int y, int width, int height, final GameWorld gameWorld, Texture tex) {
        super(x,y,width,height,gameWorld, tex);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                
                if (item != null && gameWorld.selectedItem == null) {
                    gameWorld.setSelectedItem(item);
                    item = null;

                } else if (item == null && gameWorld.selectedItem != null && (((InventorySlot.this instanceof EquipmentSlot) &&
                        ((EquipmentSlot)InventorySlot.this).acceptedItemType == gameWorld.selectedItem.itemType) || !(InventorySlot.this instanceof EquipmentSlot)))  {
                    item = gameWorld.selectedItem;
                    gameWorld.setSelectedItem(null);
                }

                createDescriptionBox(item);

                return true;
            }
        });
    }

    @Override
    protected IDescribable getEntity() {
        return item;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        drawItem(batch);
    }

    void drawItem(Batch batch) {
        if (item != null) {
            batch.draw(item.texture, getX() + 4, getY() + 4, Constants.ITEM_SIZE, Constants.ITEM_SIZE);
        }
    }

    public void setItem(Item item) {
        createDescriptionBox(item);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
