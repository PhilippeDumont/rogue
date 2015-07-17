package com.phil.rogue.constant;

import java.util.HashMap;
import java.util.Map;

public class GameConstants {

	public static final int MAP_SIZE = 100;
	public static final int MAP_SIZE_DISPLAY = 20;
	public static final int TILE_SIZE = 64;

	public enum Cons {
		ENTITY, FLOOR, WALL, TILE, BOUND, PLAYER, FUNGUS, CREATURE, BAT, RAT, FOOD, ITEM, STONE, GRASS;
	}
	
	public enum Group {
		ENTITY, FLOOR, WALL, TILE, BOUND, PLAYER,  CREATURE, ITEM;
	}
	
	public enum IA{}
	
	public enum Physique{
		
	}
	
	


	// Transformer "Cons" en int et inversement (utilie pour sauver la map)
	private static final Map<Integer, Cons> intToTypeMap = new HashMap<Integer, Cons>();

	static {
		for (Cons type : Cons.values()) {
			intToTypeMap.put(type.ordinal(), type);
		}
	}

	public static Cons fromInt(int i) {
		Cons type = intToTypeMap.get(Integer.valueOf(i));
		if (type == null)
			return null;
		return type;
	}

}
