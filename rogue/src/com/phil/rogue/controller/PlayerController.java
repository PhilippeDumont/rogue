package com.phil.rogue.controller;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.phil.rogue.screen.GameScreen;

public class PlayerController extends Controller {

	enum Keys {
		LEFT, RIGHT, UP, DOWN, UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT, TAKE, ATTACK, MOUSERIGHT, MOUSELEFT;
	}

	private final Map<Keys, Boolean> keys;
	private int reactionTouche;
	private Vector2 positionSourisLeft;
	private Vector2 positionSourisRight;

	public PlayerController(GameScreen game) {
		super(game);
		this.keys = new HashMap<PlayerController.Keys, Boolean>();
		{
			keys.put(Keys.LEFT, false);
			keys.put(Keys.RIGHT, false);
			keys.put(Keys.UP, false);
			keys.put(Keys.DOWN, false);
			keys.put(Keys.UPRIGHT, false);
			keys.put(Keys.UPLEFT, false);
			keys.put(Keys.DOWNRIGHT, false);
			keys.put(Keys.DOWNLEFT, false);

			keys.put(Keys.TAKE, false);
			keys.put(Keys.ATTACK, false);
			keys.put(Keys.MOUSERIGHT, false);
			keys.put(Keys.MOUSELEFT, false);

		}
		;

		reactionTouche = 0;
	}

	@Override
	public void update() {
		this.input();
	}

	private void input() {

		// Movements
		reactionTouche++;
		if (reactionTouche > 6) {
			reactionTouche = 0;

			if (keys.get(Keys.LEFT)) {
				this.getGame().getPlayer().moveBy(-1, 1);
				this.getGame().setUpdate(true);

			}
			if (keys.get(Keys.RIGHT)) {
				this.getGame().getPlayer().moveBy(1, -1);
				this.getGame().setUpdate(true);

			}
			if (keys.get(Keys.UP)) {
				this.getGame().getPlayer().moveBy(1, 1);
				this.getGame().setUpdate(true);

			}
			if (keys.get(Keys.DOWN)) {
				this.getGame().getPlayer().moveBy(-1, -1);
				this.getGame().setUpdate(true);

			}

			if (keys.get(Keys.UPRIGHT)) {
				this.getGame().getPlayer().moveBy(1, 0);
				this.getGame().setUpdate(true);

			}

			if (keys.get(Keys.UPLEFT)) {
				this.getGame().getPlayer().moveBy(-1, 0);
				this.getGame().setUpdate(true);

			}

			if (keys.get(Keys.DOWNRIGHT)) {
				this.getGame().getPlayer().moveBy(0, -1);
				this.getGame().setUpdate(true);

			}

			if (keys.get(Keys.DOWNLEFT)) {
				this.getGame().getPlayer().moveBy(0, -1);
				this.getGame().setUpdate(true);

			}
		}

		if (keys.get(Keys.MOUSELEFT)) {

			keys.get(keys.put(Keys.MOUSELEFT, false));
		}

		if (keys.get(Keys.MOUSERIGHT)) {

			keys.get(keys.put(Keys.MOUSERIGHT, false));
		}

	}

	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}

	public void upPressed() {
		keys.get(keys.put(Keys.UP, true));
	}

	public void downPressed() {
		keys.get(keys.put(Keys.DOWN, true));
	}

	public void downRightPressed() {
		keys.get(keys.put(Keys.DOWNRIGHT, true));
	}

	public void downLeftPressed() {
		keys.get(keys.put(Keys.DOWNLEFT, true));
	}

	public void upRightPressed() {
		keys.get(keys.put(Keys.UPRIGHT, true));
	}

	public void upLeftPressed() {
		keys.get(keys.put(Keys.UPLEFT, true));
	}

	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}

	public void upReleased() {
		keys.get(keys.put(Keys.UP, false));
	}

	public void downReleased() {
		keys.get(keys.put(Keys.DOWN, false));
	}

	public void downRightReleased() {
		keys.get(keys.put(Keys.DOWNRIGHT, false));
	}

	public void downLeftReleased() {
		keys.get(keys.put(Keys.DOWNLEFT, false));
	}

	public void upRightReleased() {
		keys.get(keys.put(Keys.UPRIGHT, false));
	}

	public void upLeftReleased() {
		keys.get(keys.put(Keys.UPLEFT, false));
	}

	public void mouseLeftPressed(int screenX, int screenY, int pointer,
			int button) {

		if (button == 0) {

			keys.get(keys.put(Keys.MOUSELEFT, true));
		}
	}

	public void mouseRighttPressed(int screenX, int screenY, int pointer,
			int button) {

		if (button == 1) {

			keys.get(keys.put(Keys.MOUSERIGHT, true));
		}
	}

}
