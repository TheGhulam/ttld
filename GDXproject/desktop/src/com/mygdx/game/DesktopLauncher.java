package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowSizeLimits(720, 480, 1920, 1080);
		config.setForegroundFPS(60);
		config.setWindowedMode(720, 480);
		config.setIdleFPS(60);
		new Lwjgl3Application(new Application(),config);
		
	}
}
	