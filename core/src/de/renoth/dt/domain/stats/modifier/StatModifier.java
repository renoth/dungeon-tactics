package de.renoth.dt.domain.stats.modifier;

import com.badlogic.gdx.graphics.Color;
import de.renoth.dt.domain.enums.ModifierType;
import de.renoth.dt.domain.enums.StatType;

public abstract class StatModifier {
    StatType statType;
    private ModifierType modifierType;
    private int modifier;
    private Color color;

    public StatModifier(ModifierType modifierType, int modifier) {
        this.modifierType = modifierType;
        this.modifier = modifier;
    }

    public Color getColor() {
        return Color.GRAY;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public StatType getStatType() {
        return statType;
    }

    public int getModifier() {
        return modifier;
    }

    public String getModifierLabel() {
        return (modifier > 0 ? "+ " : "- ") + modifier;
    }

    public ModifierType getModifierType() {
        return modifierType;
    }
}
