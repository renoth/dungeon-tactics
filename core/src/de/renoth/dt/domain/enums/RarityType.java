package de.renoth.dt.domain.enums;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import de.renoth.dt.res.Resources;

public enum RarityType {
    COMMON(0, 1, Color.WHITE, Resources.commonFrame),
    MAGIC(1, 4, Color.BLUE, Resources.magicFrame),
    RARE(2, 10, Color.YELLOW, Resources.rareFrame),
    UNIQUE(8, 20, Color.PURPLE, Resources.uniqueFrame);

    private int minModifiers, maxModifiers;
    private Color color;
    private Texture frame;
    private Texture texture;

    RarityType(int minModifiers, int maxModifiers, Color c, Texture frame) {
        this.minModifiers = minModifiers;
        this.maxModifiers = maxModifiers;
        this.color = c;
        this.frame = frame;
        this.texture = frame;
    }

    public Color getColor() {
        return color;
    }

    public int getMaxModifiers() {
        return maxModifiers;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getMinModifiers() {
        return minModifiers;
    }
}
