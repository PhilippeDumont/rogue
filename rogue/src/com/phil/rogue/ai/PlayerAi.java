package com.phil.rogue.ai;

import com.phil.rogue.model.Creature;

public class PlayerAi extends CreatureAi {

	public PlayerAi(Creature creature) {
		super(creature);
	}

	public void onEnter(int x, int y) {

		// If It the ground and not a wall or obstacle
		if (creature.getGame().getMap().isGround(x, y)
				&& creature.getGame().getMap().creature(x, y) == null) {
			creature.setRelativePosition(x, y);

		} else if (creature.getGame().getMap().isDiggable(x, y)) {
			creature.dig(x, y);
		}
	}

}
