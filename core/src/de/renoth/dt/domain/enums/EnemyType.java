package de.renoth.dt.domain.enums;

import com.badlogic.gdx.graphics.Texture;
import de.renoth.dt.res.Resources;

public enum EnemyType {
    GELATINOUS_CUBE(0, Resources.cube_blue, 8, 1, 0),
    GELATINOUS_ELITE_CUBE(1, Resources.cube_pink, 15, 2, 1),
    GELATINOUS_GREEN_CUBE(2, Resources.cube_green, 12, 2, 0),
    SNAKE(3, Resources.snake_blue, 10, 2, 0),
    SNAKE_GREEN(4, Resources.snake_green, 15, 3, 0),
    SNAKE_RED(5, Resources.snake_green, 20, 4, 0),
    GOBLIN(6, Resources.goblin, 10, 1, 0),
    GOBLIN_LOW(7, Resources.goblin_baton, 15, 3, 0),
    GOBLIN_MED(8, Resources.goblin_baton_helmet, 15, 3, 1),
    GOBLIN_HIGH(9, Resources.goblin_baton_helmet_armor, 20, 2, 3);

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
