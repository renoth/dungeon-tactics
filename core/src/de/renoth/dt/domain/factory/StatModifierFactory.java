package de.renoth.dt.domain.factory;

import de.renoth.dt.domain.enums.ItemType;
import de.renoth.dt.domain.enums.ModifierType;
import de.renoth.dt.domain.enums.StatType;
import de.renoth.dt.domain.stats.modifier.*;

import java.util.Random;

public class StatModifierFactory {

    static Random random = new Random();

    public static StatModifier createRandomModifier(int level, ItemType itemType) {
        StatType statType = itemType.getStatTypeByIndex(itemType.getMaxStatTypeIndex() == 0 ? 0 : random.nextInt(itemType.getMaxStatTypeIndex() + 1));
        switch (statType) {
            case HEALTH:
                return new HealthModifier(ModifierType.ABSOLUTE, getHealthBonus(level));
            case DAMAGE:
                return new DamageModifier(ModifierType.ABSOLUTE, getDamageBonus(level));
            case DEFENSE:
                return new DefenseModifier(ModifierType.ABSOLUTE, getDefenseBonus(level));
            case CRITICAL_CHANCE:
                return new CriticalChaceModifier(ModifierType.ABSOLUTE, getCriticalBonus(level));
        }
        return null;
    }

    private static int getCriticalBonus(int level) {
        return Math.max(Math.round(random.nextInt(level) / 4), 1);
    }

    private static int getDefenseBonus(int level) {
        return Math.max(Math.round(random.nextInt(level) / 8), 1);
    }

    private static int getDamageBonus(int level) {
        return Math.max(Math.round(random.nextInt(level) / 3), 1);
    }

    private static int getHealthBonus(int level) {
        return random.nextInt(level) + 5;
    }
}
