package de.renoth.dt.screen.game;

import de.renoth.dt.actor.SimpleActor;
import de.renoth.dt.common.Constants;
import de.renoth.dt.domain.Item;
import de.renoth.dt.domain.enums.ItemType;
import de.renoth.dt.domain.factory.ItemFactory;
import de.renoth.dt.res.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    public Map<Integer, InventorySlot> inventorySlots;
    private final GameWorld gameWorld;
    public EquipmentSlot weaponSlot;
    public EquipmentSlot armorSlot;
    public List<EquipmentSlot> equipmentSlots;


    public Inventory(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.inventorySlots = new HashMap<>();
        this.equipmentSlots = new ArrayList<>();

        gameWorld.stage.bg.addActor(new SimpleActor(0, 0, 280, 800, gameWorld, Resources.bgMenu));

        buildInventory();

        buildSlots();

        fillInitialItems();
    }

    private void fillInitialItems() {
        inventorySlots.get(getFirstFreeInventorySlot()).setItem(ItemFactory.createRandomItem(1));
    }

    private void buildSlots() {
        gameWorld.stage.bg.addActor(new SimpleActor(0, 200, 140, 400, gameWorld, Resources.bgEquipment));

        gameWorld.stage.bg.addActor(weaponSlot = new EquipmentSlot(10, 210, 40, 40, gameWorld, Resources.inventory, ItemType.WEAPON));
        equipmentSlots.add(weaponSlot);
        gameWorld.stage.bg.addActor(armorSlot = new EquipmentSlot(90, 210, 40, 40, gameWorld, Resources.inventory, ItemType.BODY_ARMOR));
        equipmentSlots.add(armorSlot);
    }

    private void buildInventory() {
        for (int column = 0; column < 7; column ++) {
            for(int row = 0 ; row < 5; row++) {
                InventorySlot slot = new InventorySlot(column * Constants.INVENTORY_GRID_SIZE, row * Constants.INVENTORY_GRID_SIZE,
                        Constants.INVENTORY_GRID_SIZE, Constants.INVENTORY_GRID_SIZE, gameWorld, Resources.inventory);
                gameWorld.stage.bg.addActor(slot);
                inventorySlots.put(getIndex(column, row), slot);
            }
        }
    }

    private int getIndex(int column, int row) {
        return row * 10 + column;
    }

    public int getFirstFreeInventorySlot() {
        for (int col = 0; col < 7; col ++) {
            for(int row = 0 ; row < 5; row++) {
                if (inventorySlots.get(getIndex(col, row)).getItem() == null) {
                    return getIndex(col, row);
                }
            }
        }

        return -1;
    }

    public List<Item> getAllEquippedItems() {
        ArrayList<Item> allEquippedItems = new ArrayList<>();

        for (EquipmentSlot es : equipmentSlots) {
            if (es.getItem() != null) allEquippedItems.add(es.getItem());
        }

        return allEquippedItems;
    }
}
