package com.phil.rogue.map;

import com.phil.rogue.constant.GameConstants.Cons;

public class MapBuilder {

	private int width;
	private int height;
	private Cons[][] tiles;

	public MapBuilder(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new Cons[width][height];
	}

	public Map build() {
		return new Map(tiles);
	}
	
	
	public MapBuilder makeGround(){
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = Cons.FLOOR ;
			}
		}
		return this;
	}

	private MapBuilder randomizeTiles() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = Math.random() < 0.5 ? Cons.FLOOR : Cons.WALL;
			}
		}
		return this;
	}

	private MapBuilder smooth(int times) {
		Cons[][] tiles2 = new Cons[width][height];
		for (int time = 0; time < times; time++) {

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					int floors = 0;
					int rocks = 0;

					for (int ox = -1; ox < 2; ox++) {
						for (int oy = -1; oy < 2; oy++) {
							if (x + ox < 0 || x + ox >= width || y + oy < 0
									|| y + oy >= height)
								continue;

							if (tiles[x + ox][y + oy] == Cons.FLOOR)
								floors++;
							else
								rocks++;
						}
					}
					tiles2[x][y] = floors >= rocks ? Cons.FLOOR : Cons.WALL;
				}
			}
			tiles = tiles2;
		}
		return this;
	}

	public MapBuilder makeCaves() {
		return randomizeTiles().smooth(8);
	}
	

}
