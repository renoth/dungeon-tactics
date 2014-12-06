package de.renoth.dt.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.renoth.dt.DungeonTacticsGame;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		config.width = screenSize.width;
		config.height = screenSize.height;

		config.fullscreen = true;
		config.vSyncEnabled = true;
		new LwjglApplication(new DungeonTacticsGame(), config);
	}
}
