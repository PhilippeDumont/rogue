package com.phil.rogue.map;

import java.util.ArrayList;
import java.util.List;

import com.phil.rogue.constant.GameConstants;
import com.phil.rogue.constant.GameConstants.Cons;
import com.phil.rogue.model.Creature;
import com.phil.rogue.model.Item;

public class Map {

	private Cons[][] tiles;
	private int width;
	private int height;

	private List<Item> items = new ArrayList<Item>();

	public Item item(int x, int y) {
		for (Item i : items) {
			if (i.getRelativePosition().x == x
					&& i.getRelativePosition().y == y)
				return i;
		}
		return null;
	}

	public List<Item> getItems() {
		return items;
	}

	public boolean addItem(Item i) {
		return this.items.add(i);
	}

	public boolean removeItem(Item i) {
		return this.items.remove(i);
	}

	private List<Creature> creatures = new ArrayList<Creature>();

	public Creature creature(int x, int y) {
		for (Creature c : creatures) {
			if (c.getRelativePosition().x == x
					&& c.getRelativePosition().y == y)
				return c;
		}
		return null;
	}

	public List<Creature> getCreatures() {
		return creatures;
	}

	public boolean addCreature(Creature c) {
		return this.creatures.add(c);
	}

	public boolean removeCreature(Creature c) {
		return this.creatures.remove(c);
	}

	public Map(Cons[][] tiles) {
		this.tiles = tiles;
		this.width = GameConstants.MAP_SIZE;
		this.height = GameConstants.MAP_SIZE;
	}

	public boolean isGround(int x, int y) {
		return this.tile(x, y) == Cons.FLOOR;

	}

	public boolean isDiggable(int x, int y) {
		return this.tile(x, y) == Cons.WALL;
	}

	public Cons tile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return Cons.BOUND;
		else
			return tiles[x][y];
	}

	public void dig(int x, int y) {
		if (this.isDiggable(x, y))
			tiles[x][y] = Cons.FLOOR;
	}

	public boolean canEnter(int x, int y) {
		return this.isGround(x, y) && this.creature(x, y) == null;
	}

}
