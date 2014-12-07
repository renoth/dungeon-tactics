package de.renoth.dt.domain.stats;

import de.renoth.dt.domain.enums.ModifierType;
import de.renoth.dt.domain.enums.StatType;

public abstract class StatModifier implements IStatModifier {
    private StatType statType;
    private ModifierType modifierType;
    private int modifier;

    public StatModifier(StatType statType, ModifierType modifierType, int modifier) {
        this.statType = statType;
        this.modifierType = modifierType;
        this.modifier = modifier;
    }

    @Override
    public StatModifier getModifier() {
        return this;
    }
}
