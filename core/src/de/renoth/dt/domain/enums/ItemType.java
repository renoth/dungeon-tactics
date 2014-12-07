package de.renoth.dt.domain.enums;

import com.badlogic.gdx.graphics.Texture;
import de.renoth.dt.res.Resources;

public enum ItemType {
    WEAPON(0,"Sword", Resources.sword),
    BODY_ARMOR(1,"Armor", Resources.armor),
    HELMET(2,"Helmet",Resources.helmet),
    BOOTS(3,"Boots", Resources.item),
    SHIELD(4,"Shield", Resources.shield);

    private int index;
    private Texture texture;
    private String baseName;

    ItemType(int index, String baseName, Texture texture) {
        this.index = index;
        this.texture = texture;
        this.baseName = baseName;
    }

    public static ItemType getByIndex(int i) {
        for (ItemType it : ItemType.values()) {
            if (it.getIndex() == i) {
                return it;
            }
        }
        return WEAPON;
    }

    public Texture getTexture() {
        return texture;
    }

    public String getBaseName() {
        return baseName;
    }

    public int getIndex() {
        return index;
    }
}
