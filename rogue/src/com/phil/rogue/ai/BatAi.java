package com.phil.rogue.ai;

import com.phil.rogue.model.Creature;

public class BatAi extends CreatureAi {

	public BatAi(Creature creature) {
		super(creature);
	}

	public void onUpdate() {
		this.wander(1);
		this.wander(1);
	}

}
