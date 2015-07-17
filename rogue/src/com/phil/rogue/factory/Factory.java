package com.phil.rogue.factory;

import com.phil.rogue.screen.GameScreen;

public abstract class Factory {

	private GameScreen game;

	public Factory(GameScreen game) {
		this.game = game;
	}

	public GameScreen getGame() {
		return game;
	}

}
