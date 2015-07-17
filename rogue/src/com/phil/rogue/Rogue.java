package com.phil.rogue;

import com.badlogic.gdx.Game;
import com.phil.rogue.screen.GameScreen;
import com.phil.rogue.screen.LoseScreen;
import com.phil.rogue.screen.MenuScreen;
import com.phil.rogue.screen.SplashScreen;

public class Rogue extends Game {

	public static final String VERSION = "0.0.0.1";
	public static final String LOG = "rogue";


	@Override
	public void create() {

		this.setScreen(new GameScreen(this));
		//this.setScreen(new MenuScreen(this));

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
