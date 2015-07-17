package com.phil.rogue.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.phil.rogue.screen.GameScreen;

public abstract class Render {

	private GameScreen game;
	private TextureAtlas atlas;

	public Render(GameScreen game) {
		this.game = game;
		this.atlas = new TextureAtlas(
				Gdx.files.internal("data/images/game.pack"));

	}

	abstract public void render();

	public GameScreen getGame() {
		return game;
	}

	public TextureAtlas getAtlas() {
		return atlas;
	}

	public void setAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
	}

}