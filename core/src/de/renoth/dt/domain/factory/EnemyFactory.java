package de.renoth.dt.domain.factory;

import de.renoth.dt.domain.Enemy;
import de.renoth.dt.domain.EnemyType;

import java.util.Random;

public class EnemyFactory {

    private static int baseLevel = 1;
    static Random random = new Random();



    public static Enemy createRandomEnemy() {
        EnemyType type = getRandomEnemyType();
        Enemy e = new Enemy(createRandomName(type), type);

        while (e.getLevel() < baseLevel) {
            e.levelUp();
        }

        return e;
    }

    private static String createRandomName(EnemyType type) {
        return type.name() + " the Killer";
    }

    private static EnemyType getRandomEnemyType() {
        return EnemyType.getByIndex(random.nextInt(EnemyType.values().length));
    }

    public static void increaseBaseLevelByOne() {
        baseLevel++;
    }
}
