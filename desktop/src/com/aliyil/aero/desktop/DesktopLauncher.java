package com.aliyil.aero.desktop;

import com.aliyil.aero.IOsBridge;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.aliyil.aero.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		config.vSyncEnabled = true;
		config.samples = 4;
		config.title = "Aero";
		new LwjglApplication(new Game(new IOsBridge() {
		}), config);
	}
}
