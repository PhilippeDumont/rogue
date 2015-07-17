package com.phil.rogue.factory;

import com.phil.rogue.ai.BatAi;
import com.phil.rogue.ai.FungusAi;
import com.phil.rogue.ai.PlayerAi;
import com.phil.rogue.ai.RatAi;
import com.phil.rogue.constant.GameConstants.Cons;
import com.phil.rogue.model.Creature;
import com.phil.rogue.model.Entity;
import com.phil.rogue.model.Entity.DisplayOrder;
import com.phil.rogue.screen.GameScreen;

public class CreatureFactory extends Factory {

	public CreatureFactory(GameScreen game) {
		super(game);
	}

	private Creature createCreature(Cons name, int x, int y, int maxHP,
			int attack, int defence) {
		Creature creature = new Creature(name, this.getGame(), maxHP, attack,
				defence);

		creature.setRelativePosition(x, y);

		creature.setDisplayOrder(DisplayOrder.UN);
		creature.addGroups(name);

		return creature;
	}

	public Entity createPlayer(int x, int y) {

		Creature creature = this.createCreature(Cons.PLAYER, x, y, 100, 20, 5);

		creature.setVisionRadius(50);

		PlayerAi ai = new PlayerAi(creature);
		creature.setCreatureAi(ai);

		return creature;
	}

	public Entity createFungus(int x, int y) {

		Creature creature = this.createCreature(Cons.FUNGUS, x, y, 10, 0, 0);
		creature.addGroups(Cons.CREATURE);

		FungusAi ai = new FungusAi(creature);
		creature.setCreatureAi(ai);

		return creature;
	}

	public Entity createBat(int x, int y) {

		Creature creature = this.createCreature(Cons.BAT, x, y, 15, 5, 0);

		creature.addGroups(Cons.CREATURE);

		BatAi ai = new BatAi(creature);
		creature.setCreatureAi(ai);

		return creature;
	}

	public Entity createRat(int x, int y) {

		Creature creature = this.createCreature(Cons.RAT, x, y, 50, 10, 0);

		creature.setVisionRadius(10);

		creature.addGroups(Cons.CREATURE);

		RatAi ai = new RatAi(creature);
		creature.setCreatureAi(ai);

		return creature;
	}

}
