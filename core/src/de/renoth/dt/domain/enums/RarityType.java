package de.renoth.dt.domain.enums;

import com.badlogic.gdx.graphics.Color;

public enum RarityType {
    COMMON(0,1, Color.WHITE),
    MAGIC(1,4, Color.BLUE),
    RARE(2,10, Color.YELLOW),
    UNIQUE(0,0, Color.PURPLE);

    private int minModifiers, maxModifiers;
    private Color color;

    RarityType(int minModifiers, int maxModifiers, Color c) {
        this.minModifiers = minModifiers;
        this.maxModifiers = maxModifiers;
        this.color = c;
    }

    public Color getColor() {
        return color;
    }

    public int getMaxModifiers() {
        return maxModifiers;
    }
}
