package de.renoth.dt.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.common.base.Joiner;
import de.renoth.dt.domain.Hero;
import de.renoth.dt.screen.GameScreen;
import de.renoth.dt.screen.game.GameWorld;

import java.util.ArrayList;

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
        ArrayList<String> stats = new ArrayList<>();
        Joiner joiner = Joiner.on(";");
        stats.add(gw.hero.getName());
        stats.add(gw.hero.getXp().getBaseValue() + "");
        stats.add(killCount + "");
        stats.add(damageDealt + "");
        return joiner.join(stats);
    }
}
