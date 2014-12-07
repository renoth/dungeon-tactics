package de.renoth.dt.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import de.renoth.dt.domain.enums.ItemType;
import de.renoth.dt.domain.enums.RarityType;
import de.renoth.dt.domain.stats.modifier.StatModifier;
import de.renoth.dt.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class Item implements IDescribable {
    public ItemType itemType;
    public RarityType rarityType;
    public String name;
    public Texture texture;
    public int level;
    public List<StatModifier> modifiers;

    public Item(ItemType itemType) {
        this.itemType = itemType;
        this.texture = itemType.getTexture();
        level = 0;
        modifiers = new ArrayList<>();
    }

    @Override
    public List<StyledText> getDescription() {
        ArrayList<StyledText> description = new ArrayList<>();

        description.add(new StyledText(name, Resources.mplus20, rarityType.getColor()));

        description.add(new StyledText("XP Value: " + getXpValue(), Resources.mplus12, Color.WHITE));

        for (StatModifier m : modifiers) {
            description.add(new StyledText(m.getStatType().getLabel() + ": " + m.getModifierLabel(), Resources.mplus12, m.getColor()));
        }

        return description;
    }

    public void setGeneratedName() {
        this.name = generatePrefix() + itemType.getBaseName() + generateSuffix();
    }

    private String generateSuffix() {
        return "";
    }

    private String generatePrefix() {
        return "";
    }

    public void setModifiers(List<StatModifier> modifiers) {
        this.modifiers = modifiers;
        groupModifiers();
    }

    private void groupModifiers() {
        List<StatModifier> condensedModifiers = new ArrayList<>();
        for (StatModifier modifier : modifiers) {
            boolean alreadExisting = false;
            for (StatModifier existingModifier : condensedModifiers) {
                if (existingModifier.getStatType() == modifier.getStatType() && existingModifier.getModifierType() == modifier.getModifierType()) {
                    existingModifier.setModifier(existingModifier.getModifier() + modifier.getModifier());
                    alreadExisting = true;
                    break;
                }
            }
            if (!alreadExisting) {
                condensedModifiers.add(modifier);
            }
        }
        modifiers = condensedModifiers;
    }

    public void setRarityType(RarityType rarityType) {
        this.rarityType = rarityType;
    }

    public int getXpValue() {
        return (1 + 3 * modifiers.size() + level * 4);
    }
}
