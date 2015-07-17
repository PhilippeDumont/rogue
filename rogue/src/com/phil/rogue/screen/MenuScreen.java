package com.phil.rogue.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.phil.rogue.Rogue;

public class MenuScreen implements Screen {

	private Rogue game;

	private Stage stage;
	private Label label;
	private LabelStyle style;
	private BitmapFont font;

	private TextureAtlas buttonAtlas;
	private TextButtonStyle buttonStyle;
	private TextButton button;
	private Skin skin;

	public MenuScreen(Rogue game) {
		super();
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		this.stage.draw();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

		this.font = new BitmapFont(Gdx.files.internal("data/fonts/orange.fnt"),
				false);
		this.stage = new Stage(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), true);

		this.style = new LabelStyle(font, Color.WHITE);

		this.label = new Label("MENU", style);
		this.label.setPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 + 60);
		this.stage.addActor(label);

		this.skin = new Skin();
		this.buttonAtlas = new TextureAtlas(
				Gdx.files.internal("data/images/game.pack"));

		this.skin.addRegions(buttonAtlas);

		this.buttonStyle = new TextButtonStyle();
		this.buttonStyle.up = skin.getDrawable("button");
		this.buttonStyle.over = skin.getDrawable("buttonpress");
		this.buttonStyle.down = skin.getDrawable("buttonpress");
		this.buttonStyle.font = font;

		this.button = new TextButton("PLAY", this.buttonStyle);

		this.button.setPosition(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);

		this.stage.addActor(button);

		Gdx.input.setInputProcessor(stage);

		this.button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				game.setScreen(new GameScreen(game));
				return true;
			}

		});

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