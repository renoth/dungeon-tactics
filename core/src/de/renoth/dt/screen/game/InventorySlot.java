package de.renoth.dt.screen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import de.renoth.dt.actor.ActorWithDescription;
import de.renoth.dt.common.Constants;
import de.renoth.dt.domain.IDescribable;
import de.renoth.dt.domain.Item;
import de.renoth.dt.res.SoundResources;
import de.renoth.dt.screen.GameScreen;

public class InventorySlot extends ActorWithDescription {

    private Item item;

    public InventorySlot(int x, int y, int width, int height, final GameWorld gameWorld, Texture tex) {
        super(x, y, width, height, gameWorld, tex);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                if (item != null && gameWorld.selectedItem == null) {
                    //take something from slot
                    gameWorld.setSelectedItem(item);
                    setItem(null);
                    if (InventorySlot.this instanceof EquipmentSlot) {
                        updateHero(GameScreen.getGameWorld().heroActor);

                    }
                    SoundResources.blipSelect.play();

                } else if (gameWorld.selectedItem != null && (((InventorySlot.this instanceof EquipmentSlot) &&
                        ((EquipmentSlot) InventorySlot.this).acceptedItemType == gameWorld.selectedItem.itemType) || !(InventorySlot.this instanceof EquipmentSlot))) {
                    //put something into slot
                    Item existingItem = item;

                    setItem(gameWorld.selectedItem);
                    gameWorld.setSelectedItem(existingItem);

                    if (InventorySlot.this instanceof EquipmentSlot) {
                        updateHero(GameScreen.getGameWorld().heroActor);
                    }
                    SoundResources.blipPut.play();
                }

                createDescriptionBox(item);

                return true;
            }
        });
    }

    private void updateHero(HeroActor heroActor) {
        heroActor.getHero().applyModifiers();
        heroActor.createDescriptionBox(heroActor.getHero());
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
            batch.draw(item.rarityType.getTexture(), getX() + 4, getY() + 4, Constants.ITEM_SIZE, Constants.ITEM_SIZE);
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        if (item != null) createDescriptionBox(item);
        this.item = item;
    }
}
