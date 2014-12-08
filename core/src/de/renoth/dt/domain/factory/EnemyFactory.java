package de.renoth.dt.domain.factory;

import de.renoth.dt.domain.Enemy;
import de.renoth.dt.domain.enums.AttackType;
import de.renoth.dt.domain.enums.EnemyType;
import de.renoth.dt.screen.GameScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactory {

    static Random random = new Random();
    private static int baseLevel = 1;

    public static Enemy createRandomEnemy() {
        EnemyType type = getRandomEnemyType();
        int bonuslevels = generateBonusLevelCount();
        Enemy e = new Enemy(createRandomName(type, bonuslevels), type);

        e.setResistances(generateRandomResistances());
        e.setWeakness(genrateRandomWeakness());
        e.setXp(1);
        e.setBonusLevels(bonuslevels);

        while (e.getLevel() < baseLevel + bonuslevels) {
            e.levelUp();
        }

        //monsters with higher base defense than hero attack shift from defense to health
        if (GameScreen.getGameWorld() != null) {
            int damageToDefenseDifference = GameScreen.getGameWorld().hero.getDamage().getValue() - e.getBaseDefense();
            if (damageToDefenseDifference < 0) {
                e.setBaseDefense(GameScreen.getGameWorld().hero.getDamage().getValue() - 1);
                e.setHealth(e.getHealth() + damageToDefenseDifference);
            }
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

        if (random < 0.3f) {
            bonusLevels = 0;
        } else if (random < 0.6f) {
            bonusLevels = 1;
        } else if (random < 0.8f) {
            bonusLevels = 2;
        } else if (random < 0.9f) {
            bonusLevels = 3;
        } else if (random < 0.97f) {
            bonusLevels = 4;
        } else {
            bonusLevels = 5;
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

    private static String createRandomName(EnemyType type, int bonuslevels) {
        return type.name() + generateSuffix(bonuslevels);
    }

    private static String generateSuffix(int bonuslevels) {
        switch (bonuslevels) {
            case 0:
                return "";
            case 1:
                return " the Experienced";
            case 2:
                return " the Fighter";
            case 3:
                return " the Elite Soldier";
            case 4:
                return " the Crusher";
            case 5:
                return " the Boss";
        }
        return null;
    }

    private static EnemyType getRandomEnemyType() {
        return EnemyType.getByIndex(random.nextInt(EnemyType.values().length));
    }

    public static void increaseBaseLevelByOne() {
        baseLevel++;
    }
}
