package de.renoth.dt.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.renoth.dt.DungeonTacticsGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        var config = new Lwjgl3ApplicationConfiguration();

        config.setWindowedMode(1280, 800);
        config.setResizable(false);
        // config.vSyncEnabled = true;
        config.setTitle("Dungeon Tactics 1.0.3");

        new Lwjgl3Application(new DungeonTacticsGame(), config);
    }
}
