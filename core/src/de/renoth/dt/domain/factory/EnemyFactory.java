package de.renoth.dt.domain.factory;

import de.renoth.dt.domain.Enemy;
import de.renoth.dt.domain.enums.AttackType;
import de.renoth.dt.domain.enums.EnemyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactory {

    private static int baseLevel = 1;
    static Random random = new Random();



    public static Enemy createRandomEnemy() {
        EnemyType type = getRandomEnemyType();
        Enemy e = new Enemy(createRandomName(type), type);

        e.setResistances(generateRandomResistances());
        e.setWeakness(genrateRandomWeakness());
        e.setXp(1);

        while (e.getLevel() < baseLevel + generateBonusLevelCount()) {
            e.levelUp();
        }

        return e;
    }

    private static AttackType genrateRandomWeakness() {
        if (Math.random() > 0.5f + 0.03 * baseLevel) {
            return AttackType.getByIndex(random.nextInt(2));
        }
        return null;
    }

    private static int generateBonusLevelCount() {
        int bonusLevels = 0;

        double random = Math.random();

        if (random < 0.6f) {
            bonusLevels = 0;
        } else if (random < 0.9f) {
            bonusLevels = 1;
        } else if (random < 0.98f) {
            bonusLevels = 2;
        } else {
            bonusLevels = 3;
        }
        return bonusLevels;
    }

    private static List<AttackType> generateRandomResistances() {
        ArrayList<AttackType> resistances = new ArrayList<>();

        for (int i = 0; i < random.nextInt(3); i++) {
            resistances.add(AttackType.getByIndex(random.nextInt(2)));
        }

        return resistances;
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
