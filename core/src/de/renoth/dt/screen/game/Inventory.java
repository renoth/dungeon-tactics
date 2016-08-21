package de.renoth.dt.screen.game;

import de.renoth.dt.actor.SimpleActor;
import de.renoth.dt.common.Constants;
import de.renoth.dt.domain.Enemy;
import de.renoth.dt.domain.Item;
import de.renoth.dt.domain.enums.ItemType;
import de.renoth.dt.domain.factory.ItemFactory;
import de.renoth.dt.res.Resources;
import de.renoth.dt.res.SoundResources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private final GameWorld gameWorld;
    public Map<Integer, InventorySlot> inventorySlots;
    public EquipmentSlot weaponSlot;
    public EquipmentSlot armorSlot;
    public List<EquipmentSlot> equipmentSlots;
    private EquipmentSlot helmetSlot;
    private EquipmentSlot shieldSlot;
    private EquipmentSlot bootSlot;
    private SellSlot sellSlot;
    private EquipmentSlot amuletSlot;


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
        inventorySlots.get(getFirstFreeInventorySlot()).setItem(ItemFactory.createRandomItem(1, 0));
        inventorySlots.get(getFirstFreeInventorySlot()).setItem(ItemFactory.createRandomItem(1, 0));
    }

    private void buildSlots() {
        gameWorld.stage.bg.addActor(new SimpleActor(0, 200, 140, 400, gameWorld, Resources.bgEquipment));
        gameWorld.stage.bg.addActor(new SimpleActor(20, 250, 100, 300, gameWorld, Resources.hero1));

        gameWorld.stage.bg.addActor(weaponSlot = new EquipmentSlot(10, 450, 40, 40, gameWorld, Resources.inventory, ItemType.WEAPON));
        equipmentSlots.add(weaponSlot);
        gameWorld.stage.bg.addActor(armorSlot = new EquipmentSlot(50, 350, 40, 40, gameWorld, Resources.inventory, ItemType.BODY_ARMOR));
        equipmentSlots.add(armorSlot);
        gameWorld.stage.bg.addActor(helmetSlot = new EquipmentSlot(50, 550, 40, 40, gameWorld, Resources.inventory, ItemType.HELMET));
        equipmentSlots.add(helmetSlot);
        gameWorld.stage.bg.addActor(shieldSlot = new EquipmentSlot(90, 450, 40, 40, gameWorld, Resources.inventory, ItemType.SHIELD));
        equipmentSlots.add(shieldSlot);
        gameWorld.stage.bg.addActor(bootSlot = new EquipmentSlot(50, 250, 40, 40, gameWorld, Resources.inventory, ItemType.BOOTS));
        equipmentSlots.add(bootSlot);
        gameWorld.stage.bg.addActor(amuletSlot = new EquipmentSlot(50, 400, 40, 40, gameWorld, Resources.inventory, ItemType.AMULET));
        equipmentSlots.add(amuletSlot);
    }

    private void buildInventory() {
        for (int column = 0; column < 7; column++) {
            for (int row = 0; row < 5; row++) {
                if (column == 6 && row == 0) {
                    sellSlot = new SellSlot(column * Constants.INVENTORY_GRID_SIZE, row * Constants.INVENTORY_GRID_SIZE,
                            Constants.INVENTORY_GRID_SIZE, Constants.INVENTORY_GRID_SIZE, gameWorld, Resources.sellSlot);
                    gameWorld.stage.bg.addActor(sellSlot);
                } else {
                    InventorySlot slot = new InventorySlot(column * Constants.INVENTORY_GRID_SIZE, row * Constants.INVENTORY_GRID_SIZE,
                            Constants.INVENTORY_GRID_SIZE, Constants.INVENTORY_GRID_SIZE, gameWorld, Resources.inventory);
                    gameWorld.stage.bg.addActor(slot);
                    inventorySlots.put(getIndex(column, row), slot);
                }
            }
        }
    }

    private int getIndex(int column, int row) {
        return row * 10 + column;
    }

    public int getFirstFreeInventorySlot() {
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 5; row++) {
                if (inventorySlots.get(getIndex(col, row)) != null && inventorySlots.get(getIndex(col, row)).getItem() == null) {
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

    public void spawnNewItemPerhaps(Enemy enemy) {
        int freeSlot = getFirstFreeInventorySlot();
        if (freeSlot < 0) {
            return;
        }
        if (Math.random() > 0.6f - 0.1f * enemy.getBonusLevels()) {
            SoundResources.newItem.play();
            inventorySlots.get(getFirstFreeInventorySlot()).setItem(ItemFactory.createRandomItem(enemy.getLevel(), enemy.getBonusLevels()));
        }
    }
}
