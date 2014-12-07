package de.renoth.dt.domain.stats;

import de.renoth.dt.domain.IKillable;
import de.renoth.dt.domain.enums.StatType;

public abstract class BaseStat {
    private int value;
    private StatType statType;


    public BaseStat(int value, StatType statType) {
        this.value = value;
        this.statType = statType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public StatType getStatType() {
        return statType;
    }

    public void setStatType(StatType statType) {
        this.statType = statType;
    }
}
