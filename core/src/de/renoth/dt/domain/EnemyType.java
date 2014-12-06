package de.renoth.dt.domain;

import com.badlogic.gdx.graphics.Texture;
import de.renoth.dt.res.Resources;

public enum EnemyType {
    GELATINOUS_CUBE(0, Resources.hero2, 15, 1, 0),
    GOBLIN(1, Resources.hero3, 10, 2, 0);


    private Texture texture;
    private int index;
    private int baseDamage, baseDefense;
    private int health;

    EnemyType(int index, Texture texture, int hp, int baseDamage, int baseDefense) {
        this.texture = texture;
        this.index = index;
        this.health = hp;
        this.baseDamage = baseDamage;
        this.baseDefense = baseDefense;
    }

    public static EnemyType getByIndex(int i) {
        for (EnemyType et : EnemyType.values()) {
            if (et.getIndex() == i) {
                return et;
            }
        }
        return GELATINOUS_CUBE;
    }

    public int getIndex() {
        return index;
    }

    public int getHealth() {
        return health;
    }


    public int getBaseDamage() {
        return baseDamage;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public Texture getTexture() {
        return texture;
    }
}
