package de.renoth.dt.domain.stats;

import de.renoth.dt.domain.IKillable;
import de.renoth.dt.domain.enums.StatType;

public class Health extends BaseStat {

    IKillable owner;

    public Health(int value, StatType statType, IKillable owner) {
        super(value, statType);
        this.owner = owner;
    }
}
