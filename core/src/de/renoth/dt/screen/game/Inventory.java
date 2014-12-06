package de.renoth.dt.screen.game;

import de.renoth.dt.actor.SimpleActor;
import de.renoth.dt.common.Constants;
import de.renoth.dt.domain.Item;
import de.renoth.dt.domain.ItemClass;
import de.renoth.dt.res.Resources;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public Map<Integer, InventorySlot> inventory;
    private final GameWorld gameWorld;
    public EquipmentSlot weaponSlot;
    public EquipmentSlot armorSlot;

    public Inventory(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.inventory = new HashMap<>();

        gameWorld.stage.addActor(new SimpleActor(0, 0, 280, 800, gameWorld, Resources.bgMenu));

        buildInventory();

        buildSlots();

        fillInitialItems();
    }

    private void fillInitialItems() {
        inventory.get(getFirstFreeInventorySlot()).item = new Item(ItemClass.WEAPON, "Sword", Resources.item);
    }

    private void buildSlots() {
        gameWorld.stage.addActor(new SimpleActor(0, 200, 140, 400, gameWorld, Resources.bgEquipment));

        gameWorld.stage.addActor(weaponSlot = new EquipmentSlot(10, 210, 40, 40, gameWorld, Resources.inventory, ItemClass.WEAPON));
        gameWorld.stage.addActor(armorSlot = new EquipmentSlot(90, 210, 40, 40, gameWorld, Resources.inventory, ItemClass.BODY_ARMOR));

    }

    private void buildInventory() {
        for (int column = 0; column < 7; column ++) {
            for(int row = 0 ; row < 5; row++) {
                InventorySlot slot = new InventorySlot(column * Constants.INVENTORY_GRID_SIZE, row * Constants.INVENTORY_GRID_SIZE,
                        Constants.INVENTORY_GRID_SIZE, Constants.INVENTORY_GRID_SIZE, gameWorld, Resources.inventory);
                gameWorld.stage.addActor(slot);
                inventory.put(getIndex(column, row), slot);
            }
        }
    }

    private int getIndex(int column, int row) {
        return row * 10 + column;
    }

    public int getFirstFreeInventorySlot() {
        for (int col = 0; col < 7; col ++) {
            for(int row = 0 ; row < 5; row++) {
                if (inventory.get(getIndex(col, row)).item == null) {
                    return getIndex(col, row);
                }
            }
        }

        return -1;
    }
}
