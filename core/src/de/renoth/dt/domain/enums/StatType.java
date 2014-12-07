package de.renoth.dt.domain.enums;

public enum StatType {
    HEALTH("Health"),
    DAMAGE("Damage"),
    DEFENSE("Defense"),
    EXPERIENCE("XP"),
    CRITICAL_CHANCE("Crit.%");

    private String label;

    StatType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
