package de.renoth.dt.domain.stats.modifier;

import de.renoth.dt.domain.enums.ModifierType;
import de.renoth.dt.domain.enums.StatType;

public class CriticalChaceModifier extends StatModifier {
    public CriticalChaceModifier(ModifierType modifierType, int modifier) {
        super(modifierType, modifier);
        statType = StatType.CRITICAL_CHANCE;
    }
}
