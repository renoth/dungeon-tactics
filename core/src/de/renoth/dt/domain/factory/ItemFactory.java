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

    public static Item createRandomItem(int level, int bonusLevels) {
        ItemType type = getRandomItemType();
        RarityType rarity = getRandomRarity(bonusLevels);
        Item item = new Item(type);
        item.setLevel(level + bonusLevels);
        item.setGeneratedName();
        item.setRarityType(rarity);
        item.setModifiers(generateModifiers(rarity, level, type));
        return item;
    }

    private static List<StatModifier> generateModifiers(RarityType rarity, int level, ItemType type) {
        ArrayList<StatModifier> modifiers = new ArrayList<>();

        for (int i = 0; i < rarity.getMaxModifiers(); i++) {
            modifiers.add(StatModifierFactory.createRandomModifier(level, type));
            if (i >= rarity.getMinModifiers() && Math.random() > 0.65f) {
                break;
            }
        }
        return modifiers;
    }

    private static ItemType getRandomItemType() {
        return ItemType.getByIndex(random.nextInt(ItemType.values().length));
    }

    public static RarityType getRandomRarity(int bonusLevels) {
        double random = Math.random();

        if (random < 0.75f - bonusLevels * 0.06f) {
            return RarityType.COMMON;
        } else if (random < 0.94f - bonusLevels * 0.03f) {
            return RarityType.MAGIC;
        } else if (random < 1f  - bonusLevels * 0.01f) {
            return RarityType.RARE;
        } else {
            return RarityType.UNIQUE;
        }
    }
}
