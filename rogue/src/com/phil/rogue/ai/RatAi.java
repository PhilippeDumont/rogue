package com.phil.rogue.ai;

import com.phil.rogue.model.Creature;
import com.phil.rogue.utils.Point;

public class RatAi extends CreatureAi {
	private Creature player;
	private Point lastPosition;
	private boolean hunt;

	public RatAi(Creature creature) {
		super(creature);
		this.player = this.creature.getGame().getPlayer();
		this.hunt = false;
		this.lastPosition = this.creature.getPosition();
	}

	public void onUpdate() {
		if (Math.random() < 0.5) {

			if (creature.canSee(player.getRelativePosition().x,
					player.getRelativePosition().y, player.getPosition().z)) {

				this.hunt = true;
				this.lastPosition = new Point(player.getRelativePosition().x,
						player.getRelativePosition().y, player.getPosition().z);

				hunt(player);
			} else {

				if (!this.creature.getRelativePosition().equals(lastPosition)
						&& this.hunt) {
					this.go(lastPosition);
				} else {

					this.hunt = false;
					wander(1);
				}
			}
		}
	}
}
