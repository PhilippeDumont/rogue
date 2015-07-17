package com.phil.rogue.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.phil.rogue.constant.GameConstants;
import com.phil.rogue.constant.GameConstants.Cons;
import com.phil.rogue.model.Creature;
import com.phil.rogue.model.Entity;
import com.phil.rogue.screen.GameScreen;

public class WorldRender extends Render {

	private static final float RUNNING_FRAME_DURATION = 0.1f;

	private SpriteBatch batch;

	private TextureRegion blocTexture;
	private TextureRegion dalleTexture;
	private TextureRegion playerTexture;
	private TextureRegion fungusTexture;
	private TextureRegion foodTexture;
	private TextureRegion grassTexture;


	private Animation batAnimation;
	private TextureRegion[] batFrames;

	private Animation ratAnimation;
	private TextureRegion[] ratFrames;

	private TextureRegion currentFrame;

	private float stateTime = 0f;

	public WorldRender(GameScreen game) {
		super(game);
		this.init();
	}

	private void init() {
		batch = new SpriteBatch();
		this.loadTextures();
	}

	private void loadTextures() {

		this.blocTexture = this.getAtlas().findRegion("wall");
		this.dalleTexture = this.getAtlas().findRegion("floor");
		this.playerTexture = this.getAtlas().findRegion("player");
		this.fungusTexture = this.getAtlas().findRegion("fungus");
		this.foodTexture = this.getAtlas().findRegion("food");
		this.grassTexture = this.getAtlas().findRegion("grass");


		batFrames = new TextureRegion[2];
		for (int i = 0; i < 2; i++) {
			batFrames[i] = this.getAtlas().findRegion("bat-0" + (i + 1));
		}
		batAnimation = new Animation(RUNNING_FRAME_DURATION, batFrames);

		ratFrames = new TextureRegion[6];
		for (int i = 0; i < 6; i++) {
			ratFrames[i] = this.getAtlas().findRegion("rat-0" + (i + 1));
		}
		ratAnimation = new Animation(RUNNING_FRAME_DURATION, ratFrames);

	}

	@Override
	public void render() {

		stateTime += Gdx.graphics.getDeltaTime();

		batch.setProjectionMatrix(this.getGame().getCamera().combined);
		batch.begin();
		this.draw();
		batch.end();
	}

	private void draw() {

		for (Entity entity : this.getGame().getWorld().getEntities()) {

			if (entity.isInGroup(Cons.WALL)) {

				this.batch.setColor(entity.getColor());

				this.batch.draw(this.blocTexture, entity.getPosition().x,
						entity.getPosition().y, GameConstants.TILE_SIZE / 2,
						GameConstants.TILE_SIZE / 2, GameConstants.TILE_SIZE,
						GameConstants.TILE_SIZE, entity.getSizeX(),
						entity.getSizeY(), entity.getRotation());

			} else if (entity.isInGroup(Cons.FLOOR)) {

				this.batch.setColor(entity.getColor());

				this.batch.draw(this.dalleTexture, entity.getPosition().x,
						entity.getPosition().y, GameConstants.TILE_SIZE / 2,
						GameConstants.TILE_SIZE / 2, GameConstants.TILE_SIZE,
						GameConstants.TILE_SIZE, entity.getSizeX(),
						entity.getSizeY(), entity.getRotation());
				
			} else if (entity.isInGroup(Cons.GRASS)) {

				this.batch.setColor(entity.getColor());

				this.batch.draw(this.grassTexture,
						entity.getPosition().x, entity.getPosition().y,
						GameConstants.TILE_SIZE / 2,
						GameConstants.TILE_SIZE / 2, GameConstants.TILE_SIZE,
						GameConstants.TILE_SIZE, entity.getSizeX(),
						entity.getSizeY(), entity.getRotation());

			} else if (entity.isInGroup(Cons.PLAYER)) {

				this.batch.setColor(entity.getColor());

				this.batch.draw(this.playerTexture, entity.getPosition().x,
						entity.getPosition().y, GameConstants.TILE_SIZE / 2,
						GameConstants.TILE_SIZE / 2, GameConstants.TILE_SIZE,
						GameConstants.TILE_SIZE, entity.getSizeX(),
						entity.getSizeY(), entity.getRotation());

			} else if (entity.isInGroup(Cons.FUNGUS)) {
				Creature creature = (Creature) entity;

				this.batch.setColor(entity.getColor());

				this.batch.draw(this.fungusTexture, entity.getPosition().x,
						entity.getPosition().y, GameConstants.TILE_SIZE / 2,
						GameConstants.TILE_SIZE / 2, GameConstants.TILE_SIZE,
						GameConstants.TILE_SIZE, entity.getSizeX()
								* ((float) creature.getAge() / 100),
						entity.getSizeY() * ((float) creature.getAge() / 100),
						entity.getRotation());

			} else if (entity.isInGroup(Cons.BAT)) {

				this.batch.setColor(entity.getColor());

				this.currentFrame = batAnimation.getKeyFrame(stateTime, true);

				this.batch.draw(currentFrame, entity.getPosition().x,
						entity.getPosition().y, GameConstants.TILE_SIZE,
						GameConstants.TILE_SIZE);

			} else if (entity.isInGroup(Cons.RAT)) {

				this.batch.setColor(entity.getColor());

				this.currentFrame = ratAnimation.getKeyFrame(stateTime, true);

				this.batch.draw(currentFrame, entity.getPosition().x,
						entity.getPosition().y, GameConstants.TILE_SIZE,
						GameConstants.TILE_SIZE);

			} else if (entity.isInGroup(Cons.FOOD)) {

				this.batch.setColor(entity.getColor());

				this.batch.draw(this.foodTexture, entity.getPosition().x,
						entity.getPosition().y, GameConstants.TILE_SIZE / 2,
						GameConstants.TILE_SIZE / 2, GameConstants.TILE_SIZE,
						GameConstants.TILE_SIZE, entity.getSizeX(),
						entity.getSizeY(), entity.getRotation());

			}
		}

	}

}
