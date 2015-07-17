package com.phil.rogue.controller;

import com.phil.rogue.screen.GameScreen;

public class CameraController extends Controller {

	public CameraController(GameScreen game) {
		super(game);
	}

	@Override
	public void update() {

		this.getGame().getCamera().position
				.set(this.getGame().getPlayer().getPosition().x, this.getGame()
						.getPlayer().getPosition().y, 0);

		this.getGame().getCamera().update();
	}

}
