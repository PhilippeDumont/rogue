package com.phil.rogue.screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.phil.rogue.Rogue;
import com.phil.rogue.constant.GameConstants;
import com.phil.rogue.constant.GameConstants.Cons;
import com.phil.rogue.controller.CameraController;
import com.phil.rogue.controller.Controller;
import com.phil.rogue.controller.InfoController;
import com.phil.rogue.controller.PlayerController;
import com.phil.rogue.controller.WorldController;
import com.phil.rogue.factory.CreatureFactory;
import com.phil.rogue.factory.ItemFactory;
import com.phil.rogue.factory.TileFactory;
import com.phil.rogue.info.InfoManager;
import com.phil.rogue.map.Map;
import com.phil.rogue.map.MapBuilder;
import com.phil.rogue.model.Creature;
import com.phil.rogue.model.Entity;
import com.phil.rogue.view.InfoRender;
import com.phil.rogue.view.Render;
import com.phil.rogue.view.WorldRender;
import com.phil.rogue.world.World;

public class GameScreen implements Screen, InputProcessor {

	private Rogue rogue;

	private World world;
	private Map map;
	private OrthographicCamera camera;
	private InfoManager infoManager;

	private boolean update = true;

	private Render worldRender;
	private Render infoRender;

	private Controller infoController;
	private Controller cameraController;
	private Controller worldController;
	private PlayerController playerController;

	private TileFactory tileFactory;
	private CreatureFactory creatureFactory;
	private ItemFactory itemFactory;

	private Creature player;

	private boolean[][] rememberMap = new boolean[GameConstants.MAP_SIZE * 2][GameConstants.MAP_SIZE * 2];

	public GameScreen(Rogue rogue) {
		this.setRogue(rogue);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		this.playerController.update();

		if (this.update) {
			this.update = false;

			this.updateCreature();
			this.loadTiles();

			this.worldController.update();
			if (this.player.isDead()) {
				this.rogue.setScreen(new LoseScreen(this.rogue));
			}

		}

		this.cameraController.update();
		this.infoController.update();

		this.worldRender.render();
		this.infoRender.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);

		this.world = new World();
		this.map = new MapBuilder(GameConstants.MAP_SIZE,
				GameConstants.MAP_SIZE).makeCaves().build();
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		this.infoManager = new InfoManager();

		this.infoRender = new InfoRender(this);
		this.worldRender = new WorldRender(this);

		this.infoController = new InfoController(this);
		this.worldController = new WorldController(this);
		this.cameraController = new CameraController(this);

		this.playerController = new PlayerController(this);

		this.tileFactory = new TileFactory(this);
		this.creatureFactory = new CreatureFactory(this);
		this.itemFactory = new ItemFactory(this);

		this.init();

	}

	public void init() {
		this.createCreatures();
		this.loadTiles();
	}

	private void updateCreature() {
		List<Creature> toUpdate = new ArrayList<Creature>(
				this.map.getCreatures());
		for (Creature creature : toUpdate) {
			creature.update();
		}

	}

	private void loadTiles() {

		// Delete all Tiles
		for (Entity tile : this.world.getEntities(Cons.TILE)) {
			this.world.remove(tile);
		}

		// Delete creature
		for (Entity creature : this.world.getEntities(Cons.CREATURE)) {
			this.world.remove(creature);
		}

		// Delete all Item
		for (Entity item : this.world.getEntities(Cons.ITEM)) {
			this.world.remove(item);
		}

		int xBegin = (int) this.player.getRelativePosition().x
				- GameConstants.MAP_SIZE_DISPLAY / 2;

		int yBegin = (int) this.player.getRelativePosition().y
				- GameConstants.MAP_SIZE_DISPLAY / 2;

		xBegin = (xBegin < 0) ? 0 : xBegin;
		yBegin = (yBegin < 0) ? 0 : yBegin;

		int xEnd = xBegin + GameConstants.MAP_SIZE_DISPLAY;
		int yEnd = yBegin + GameConstants.MAP_SIZE_DISPLAY;

		for (int x = xBegin; x < xEnd; x++) {
			for (int y = yBegin; y < yEnd; y++) {

				if (this.player.canSee(x, y, 0)) {
					this.rememberMap[x][y] = true;
				}

				if (this.rememberMap[x][y]) {

					// add creature
					if (this.map.creature(x, y) != null)
						this.world.add(this.map.creature(x, y));

					// add Item
					if (this.map.item(x, y) != null)
						this.world.add(this.map.item(x, y));

					// add tile
					if (map.tile(x, y) == Cons.FLOOR)
						this.world.add(this.tileFactory.createFloor(x, y));

					if (map.tile(x, y) == Cons.WALL)
						this.world.add(this.tileFactory.createWall(x, y));
					
					if (map.tile(x, y) == Cons.GRASS)
						this.world.add(this.tileFactory.createFloor(x, y));

				}

			}
		}
	}

	private void createCreatures() {

		this.player = (Creature) this.creatureFactory.createPlayer(0, 0);
		this.addAtEmptyLocation(player);
		this.world.add(player);

		this.map.addCreature(player);

		for (int i = 0; i < 8; i++) {
			this.map.addCreature((Creature) this
					.addAtEmptyLocation(this.creatureFactory.createFungus(0, 0)));
		}

		for (int i = 0; i < 20; i++) {
			this.map.addCreature((Creature) this
					.addAtEmptyLocation(this.creatureFactory.createBat(0, 0)));
		}

		for (int i = 0; i < 10; i++) {
			this.map.addCreature((Creature) this
					.addAtEmptyLocation(this.creatureFactory.createRat(0, 0)));
		}

	}

	private boolean positionPair(int x, int y) {

		if (x % 2 != 0) {
			return false;
		}

		if (y % 2 != 0) {
			return false;
		}

		return true;

	}

	public Entity addAtEmptyLocation(Entity creature) {
		int x;
		int y;

		do {
			x = (int) (Math.random() * GameConstants.MAP_SIZE);
			y = (int) (Math.random() * GameConstants.MAP_SIZE);
		} while (!this.map.isGround(x, y) || !this.positionPair(x, y));

		creature.setRelativePosition(x, y);

		return creature;
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT)
			playerController.leftPressed();
		if (keycode == Keys.RIGHT)
			playerController.rightPressed();
		if (keycode == Keys.UP)
			playerController.upPressed();
		if (keycode == Keys.DOWN)
			playerController.downPressed();

		if (keycode == Keys.H)
			playerController.leftPressed();
		if (keycode == Keys.D)
			playerController.rightPressed();
		if (keycode == Keys.K)
			playerController.upPressed();
		if (keycode == Keys.L)
			playerController.downPressed();

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT)
			playerController.leftReleased();
		if (keycode == Keys.RIGHT)
			playerController.rightReleased();
		if (keycode == Keys.UP)
			playerController.upReleased();
		if (keycode == Keys.DOWN)
			playerController.downReleased();

		if (keycode == Keys.H)
			playerController.leftReleased();
		if (keycode == Keys.D)
			playerController.rightReleased();
		if (keycode == Keys.Z)
			playerController.upReleased();
		if (keycode == Keys.S)
			playerController.downReleased();
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public OrthographicCamera getCamera() {
		return this.camera;
	}

	public World getWorld() {
		return world;
	}

	public Map getMap() {
		return map;
	}

	public Render getWorldRender() {
		return worldRender;
	}

	public InfoManager getInfoManager() {
		return infoManager;
	}

	public Controller getInfoController() {
		return infoController;
	}

	public Render getInfoRender() {
		return infoRender;
	}

	public Controller getCameraController() {
		return cameraController;
	}

	public Controller getWorldController() {
		return worldController;
	}

	public Creature getPlayer() {
		return player;
	}

	public void setPlayer(Creature player) {
		this.player = player;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public CreatureFactory getCreatureFactory() {
		return creatureFactory;
	}

	public Rogue getRogue() {
		return rogue;
	}

	public void setRogue(Rogue rogue) {
		this.rogue = rogue;
	}

	public ItemFactory getItemFactory() {
		return itemFactory;
	}

}
