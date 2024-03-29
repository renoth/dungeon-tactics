package de.renoth.dt.domain.stats;

import de.renoth.dt.domain.enums.ModifierType;
import de.renoth.dt.domain.enums.StatType;
import de.renoth.dt.domain.stats.modifier.StatModifier;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseStat {
    public List<StatModifier> modifiers;
    private int baseValue;
    private StatType statType;
    private int maxValue;

    public BaseStat(int baseValue, StatType statType) {
        this.baseValue = baseValue;
        this.statType = statType;
        modifiers = new ArrayList<>();
        this.maxValue = baseValue;
    }

    public int getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(int value) {
        this.baseValue = value;
    }

    public StatType getStatType() {
        return statType;
    }

    public void setStatType(StatType statType) {
        this.statType = statType;
    }

    public int getValue() {
        int value = baseValue;
        for (StatModifier sm : modifiers) {
            if (sm.getModifierType() == ModifierType.ABSOLUTE) {
                value += sm.getModifier();
            }
        }
        for (StatModifier sm : modifiers) {
            if (sm.getModifierType() == ModifierType.PERCENTAGE) {
                value *= (100 + sm.getModifier());
                value /= 100;
            }
        }
        return value;
    }

    public int getMaxPossibleValue() {
        int maxPossibleValue = maxValue;
        for (StatModifier sm : modifiers) {
            if (sm.getModifierType() == ModifierType.ABSOLUTE) {
                maxPossibleValue += sm.getModifier();
            }
        }
        for (StatModifier sm : modifiers) {
            if (sm.getModifierType() == ModifierType.PERCENTAGE) {
                maxPossibleValue *= (100 + sm.getModifier());
                maxPossibleValue /= 100;
            }
        }
        return maxPossibleValue;
    }

    public int getBonus() {
        return getValue() - getBaseValue();
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void increaseBaseValue(int i) {
        baseValue += i;
        maxValue += i;
    }
}
