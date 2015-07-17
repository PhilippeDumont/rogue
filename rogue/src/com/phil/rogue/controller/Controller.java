package com.phil.rogue.controller;

import com.phil.rogue.screen.GameScreen;

public abstract class Controller {

	private GameScreen game;

	public Controller(GameScreen game) {
		this.game = game;
	}

	public abstract void update();

	public GameScreen getGame() {
		return game;
	}

}
