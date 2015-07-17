package com.phil.rogue.ai;

import com.badlogic.gdx.math.MathUtils;
import com.phil.rogue.model.Creature;

public class FungusAi extends CreatureAi {


	public FungusAi(Creature creature) {
		super(creature);
	}

	public void onUpdate() {

		if (this.creature.getAge() > 100) {

			int chanceDeReproduction = MathUtils.random(50);
			if (chanceDeReproduction == 1) {
				this.spread(3);
			}

		} else {
			this.creature.addAge();
		}

	}

}
