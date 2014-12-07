package de.renoth.dt.domain.factory;

import de.renoth.dt.domain.Item;
import de.renoth.dt.domain.enums.ItemType;
import de.renoth.dt.domain.enums.RarityType;
import de.renoth.dt.domain.stats.modifier.StatModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemFactory {

    static Random random = new Random();

    public static Item createRandomItem(int level) {
        ItemType type = getRandomItemType();
        RarityType rarity = getRandomRarity();
        Item item = new Item(type);
        item.setGeneratedName();
        item.setRarityType(rarity);
        item.setModifiers(generateModifiers(rarity, level, type));
        return item;
    }

    private static List<StatModifier> generateModifiers(RarityType rarity, int level, ItemType type) {
        ArrayList<StatModifier> modifiers = new ArrayList<>();

        for (int i = 0; i < rarity.getMaxModifiers(); i++) {
            modifiers.add(StatModifierFactory.createRandomModifier(level, type));
        }
        return modifiers;
    }

    private static ItemType getRandomItemType() {
        return ItemType.getByIndex(random.nextInt(ItemType.values().length));
    }

    public static RarityType getRandomRarity() {
        double random = Math.random();

        if (random < 0.7f) {
            return RarityType.COMMON;
        } else if (random < 0.9f) {
            return RarityType.MAGIC;
        } else if (random < 1.1f) {
            return RarityType.RARE;
        } else {
            return RarityType.UNIQUE;
        }
    }
}
