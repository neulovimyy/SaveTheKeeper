package com.stk;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.stk.screens.HomeScreen;


public class MainClass extends Game {

	public static final float SCREEN_HEIGHT = 720;
	public static final float SCREEN_WIDTH = 1280;
	public static final float PPM = 100;

	OrthographicCamera camera;
	FitViewport viewport;
	@Override
	public void create () {
		camera = new OrthographicCamera();
		viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT,camera);
		camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
		
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
			setScreen(new HomeScreen(this));
		}

		super.render();
	}
	
	@Override
	public void dispose () {
		
	}
}
