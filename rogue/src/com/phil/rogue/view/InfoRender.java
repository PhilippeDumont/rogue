package com.phil.rogue.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.phil.rogue.constant.GameConstants;
import com.phil.rogue.info.Info;
import com.phil.rogue.screen.GameScreen;

public class InfoRender extends Render {

	private SpriteBatch batch;
	private BitmapFont font;

	public InfoRender(GameScreen game) {
		super(game);

		this.init();
	}

	private void init() {
		batch = new SpriteBatch();

		Texture fontTexture = new Texture(
				Gdx.files.internal("data/fonts/orange.png"));

		fontTexture.setFilter(TextureFilter.Linear,
				TextureFilter.MipMapLinearLinear);

		TextureRegion fontRegion = new TextureRegion(fontTexture);

		font = new BitmapFont(Gdx.files.internal("data/fonts/orange.fnt"),
				fontRegion, false);
		font.setUseIntegerPositions(false);

	}

	@Override
	public void render() {
		batch.setProjectionMatrix(this.getGame().getCamera().combined);
		batch.begin();
		this.drawInfo();
		batch.end();
	}

	private void drawInfo() {

		for (Info info : this.getGame().getInfoManager().getInfos()) {
			font.setColor(Color.WHITE);
			font.draw(batch, info.getData(), info.getPosition().x
					+ GameConstants.TILE_SIZE / 2, info.getPosition().y
					+ GameConstants.TILE_SIZE);
		}

	}

}
