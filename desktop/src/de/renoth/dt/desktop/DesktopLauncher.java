package de.renoth.dt.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.renoth.dt.DungeonTacticsGame;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1280;
		config.height = 800;
		config.resizable = false;
		config.vSyncEnabled = true;
		config.title = "Dungeon Tactics 1.0.3";

		new LwjglApplication(new DungeonTacticsGame(), config);
	}
}
