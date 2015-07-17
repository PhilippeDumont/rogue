package com.phil.rogue.factory;

import com.phil.rogue.constant.GameConstants.Cons;
import com.phil.rogue.model.Entity;
import com.phil.rogue.model.Tile;
import com.phil.rogue.screen.GameScreen;

public class TileFactory extends Factory {

	public TileFactory(GameScreen game) {
		super(game);
	}

	public Tile createTile(Cons name, int x, int y) {

		Tile tile = new Tile(name, this.getGame());

		tile.setRelativePosition(x, y);

		tile.addGroups(Cons.TILE);
		tile.addGroups(name);

		return tile;
	}

	public Entity createFloor(int x, int y) {
		Tile tile = this.createTile(Cons.FLOOR, x, y);
		return tile;

	}

	public Entity createWall(int x, int y) {
		Tile tile = this.createTile(Cons.WALL, x, y);
		return tile;

	}
	
	

}
