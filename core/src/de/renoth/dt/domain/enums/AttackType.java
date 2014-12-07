package de.renoth.dt.domain.enums;

public enum AttackType {
    SLICE(0),
    STAB(1),
    BLUDGEON(2);

    private int index;

    AttackType(int index) {
        this.index = index;
    }

    public static AttackType getByIndex(int i) {
        for (AttackType at : AttackType.values()) {
            if (at.index == i) return at;
        }
        return null;
    }
}
