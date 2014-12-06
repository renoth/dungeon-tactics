package de.renoth.dt.domain;

import com.badlogic.gdx.graphics.Texture;

public class Item {
    public ItemClass itemClass;
    public String name;
    public Texture texture;
    public int baseDamage, baseProtection, baseValue;

    public Item(ItemClass itemClass, String name, Texture texture) {
        this.itemClass = itemClass;
        this.name = name;
        this.texture = texture;
    }
}
