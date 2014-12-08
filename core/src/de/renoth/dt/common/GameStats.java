package de.renoth.dt.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.renoth.dt.domain.Hero;
import de.renoth.dt.screen.GameScreen;
import de.renoth.dt.screen.game.GameWorld;

import java.util.StringJoiner;

public class GameStats {
    public static int killCount = 0;
    public static int damageDealt = 0;
    public static int damageTaken = 0;
    public static Hero hero = null;

    public static void reset() {
        killCount = 0;
        damageDealt = 0;
        damageTaken = 0;
    }

    ;

    public static void writeScoreToDisk() {
        FileHandle scoreFile = Gdx.files.local("dungeonTacticsHighScores.txt");
        scoreFile.writeString(generateStatsLine() + "\n", true);
    }

    private static String generateStatsLine() {
        GameWorld gw = GameScreen.getGameWorld();
        StringJoiner joiner = new StringJoiner(";");
        joiner.add(gw.hero.getName()).add(gw.hero.getXp().getBaseValue() + "").add(killCount + "").add(damageDealt + "");
        return joiner.toString();
    }

}
