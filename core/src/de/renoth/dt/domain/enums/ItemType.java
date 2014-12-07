package de.renoth.dt.domain.enums;

import com.badlogic.gdx.graphics.Texture;
import de.renoth.dt.res.Resources;

public enum ItemType {
    WEAPON(0, "Sword", Resources.sword, new StatType[]{StatType.DAMAGE}),
    BODY_ARMOR(1, "Armor", Resources.armor, new StatType[]{StatType.HEALTH, StatType.DEFENSE}),
    HELMET(2, "Helmet", Resources.helmet, new StatType[]{StatType.HEALTH, StatType.DEFENSE}),
    BOOTS(3, "Boots", Resources.boots, new StatType[]{StatType.HEALTH, StatType.DEFENSE}),
    SHIELD(4, "Shield", Resources.shield, new StatType[]{StatType.HEALTH, StatType.DEFENSE});

    private int index;
    private Texture texture;
    private String baseName;
    private StatType[] possibleStatModifierTypes;

    ItemType(int index, String baseName, Texture texture, StatType[] statTypeList) {
        this.index = index;
        this.texture = texture;
        this.baseName = baseName;
        this.possibleStatModifierTypes = statTypeList;
    }

    public static ItemType getByIndex(int i) {
        for (ItemType it : ItemType.values()) {
            if (it.getIndex() == i) {
                return it;
            }
        }
        return WEAPON;
    }

    public StatType getStatTypeByIndex(int i) {
        return possibleStatModifierTypes[i];
    }

    public int getMaxStatTypeIndex() {
        return possibleStatModifierTypes.length - 1;
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
