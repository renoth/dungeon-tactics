package de.renoth.dt.domain.stats.modifier;

import de.renoth.dt.domain.enums.ModifierType;
import de.renoth.dt.domain.enums.StatType;

public class DamageModifier extends StatModifier {

    public DamageModifier(ModifierType modifierType, int modifier) {
        super(modifierType, modifier);
        statType = StatType.DAMAGE;
    }
}
