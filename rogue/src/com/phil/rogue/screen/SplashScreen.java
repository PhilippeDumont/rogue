package com.phil.rogue.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.phil.rogue.Rogue;

public class SplashScreen implements Screen {

	private SpriteBatch batch;
	private Rogue game;
	private OrthographicCamera camera;
	private TextureRegion texture;

	private TextureAtlas atlas = new TextureAtlas(
			Gdx.files.internal("data/images/game.pack"));

	public SplashScreen(Rogue game) {
		super();
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(this.camera.combined);

		batch.begin();
		batch.draw(texture, 0, 0);
		batch.end();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

		this.texture = this.atlas.findRegion("player");

		this.batch = new SpriteBatch();

		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}