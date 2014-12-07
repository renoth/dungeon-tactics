package de.renoth.dt.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import de.renoth.dt.domain.enums.ItemType;
import de.renoth.dt.domain.enums.RarityType;
import de.renoth.dt.domain.stats.IStatModifier;
import de.renoth.dt.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class Item implements IDescribable {
    public ItemType itemType;
    public RarityType rarityType;
    public String name;
    public Texture texture;
    public int baseDamage, baseDefense, baseValue, level;
    public List<IStatModifier> modifiers;

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

    public void setModifiers(List<IStatModifier> modifiers) {
        this.modifiers = modifiers;
    }

    public void setRarityType(RarityType rarityType) {
        this.rarityType = rarityType;
    }
}
