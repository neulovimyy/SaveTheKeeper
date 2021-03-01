package com.stk.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.stk.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = (int) MainClass.SCREEN_HEIGHT;
		config.width = (int) MainClass.SCREEN_WIDTH;
		new LwjglApplication(new MainClass(), config);
	}
}
