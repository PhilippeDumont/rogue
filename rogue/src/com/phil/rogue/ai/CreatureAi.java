package com.phil.rogue.ai;

import java.util.List;

import com.badlogic.gdx.math.MathUtils;
import com.phil.rogue.model.Creature;
import com.phil.rogue.utils.Line;
import com.phil.rogue.utils.Path;
import com.phil.rogue.utils.Point;

public class CreatureAi {

	protected Creature creature;

	public CreatureAi(Creature creature) {
		this.creature = creature;
		this.creature.setCreatureAi(this);
	}

	public void onEnter(int x, int y) {
		if (this.creature.getGame().getMap().isGround(x, y)) {
			this.creature.setRelativePosition(x, y);
		}

	}

	public void onUpdate() {
	}

	public boolean canSee(int wx, int wy, int wz) {
		if (creature.getPosition().z != wz)
			return false;

		if ((creature.getRelativePosition().x - wx)
				* (creature.getRelativePosition().x - wx)
				+ (creature.getRelativePosition().y - wy)
				* (creature.getRelativePosition().y - wy) > creature
				.getVisionRadius() * creature.getVisionRadius())
			return false;

		for (Point p : new Line(creature.getRelativePosition().x,
				creature.getRelativePosition().y, wx, wy)) {
			if (creature.getGame().getMap().isGround(p.x, p.y) || p.x == wx
					&& p.y == wy)
				continue;

			return false;
		}

		return true;
	}

	public void spread(int rayon) {

		int randomX = MathUtils.random(-rayon, rayon);
		int randomY = MathUtils.random(-rayon, rayon);

		int x = (int) this.creature.getRelativePosition().x;
		int y = (int) this.creature.getRelativePosition().y;

		x += randomX;
		y += randomY;

		if (this.creature.getGame().getMap().canEnter(x, y) && x % 2 == 0
				&& y % 2 == 0) {

			this.creature
					.getGame()
					.getMap()
					.addCreature(
							(Creature) this.creature.getGame()
									.getCreatureFactory().createFungus(x, y));

		}

	}

	public void wander(int range) {

		int mx = MathUtils.random(-range, range);
		int my = MathUtils.random(-range, range);

		Creature other = this.creature.getGame().getMap().creature(mx, my);

		if (other != null)
			return;
		else
			this.creature.moveBy(mx, my);

	}

	public void hunt(Creature target) {

		List<Point> points = new Path(creature, target.getRelativePosition().x,
				target.getRelativePosition().y).points();

		int mx = points.get(0).x - creature.getRelativePosition().x;
		int my = points.get(0).y - creature.getRelativePosition().y;

		creature.moveBy(mx, my);

	}

	public void go(Point target) {

		List<Point> points = new Path(creature, target.x, target.y).points();

		int mx = points.get(0).x - creature.getRelativePosition().x;
		int my = points.get(0).y - creature.getRelativePosition().y;

		creature.moveBy(mx, my);

	}

}
