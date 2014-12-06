package de.renoth.dt.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import de.renoth.dt.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class Item implements IDescribable {
    public ItemClass itemClass;
    public String name;
    public Texture texture;
    public int baseDamage, baseProtection, baseValue;
    public List<IModifier> modifiers;

    public Item(ItemClass itemClass, String name, Texture texture) {
        this.itemClass = itemClass;
        this.name = name;
        this.texture = texture;
    }

    @Override
    public List<StyledText> getDescription() {
        ArrayList<StyledText> description = new ArrayList<>();

        description.add(new StyledText(name, Resources.mplus20, Color.WHITE));

        return description;
    }
}
