package de.renoth.dt.domain.stats.modifier;

import de.renoth.dt.domain.enums.ModifierType;
import de.renoth.dt.domain.enums.StatType;

public class HealthModifier extends StatModifier {

    public HealthModifier(ModifierType modifierType, int modifier) {
        super(modifierType, modifier);
        statType = StatType.HEALTH;
    }
}
