package de.renoth.dt.domain.enums;

public enum StatType {
    HEALTH("Health"),
    DAMAGE("Damage"),
    DEFENSE("Defense");

    private String label;

    StatType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
