package com.phil.rogue.model;

import com.badlogic.gdx.graphics.Color;
import com.phil.rogue.ai.CreatureAi;
import com.phil.rogue.constant.GameConstants.Cons;
import com.phil.rogue.info.Info;
import com.phil.rogue.info.Info.InfoEffet;
import com.phil.rogue.screen.GameScreen;

public class Creature extends Entity {

	public Creature(Cons name, GameScreen game, int maxHp,
			int attack, int defense) {
		super(name, game);
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attackValue = attack;
		this.defenseValue = defense;
	}

	private Inventory inventory;

	public Inventory getInventory() {
		return inventory;
	}

	public void pickup(Item item) {

		// implement verification for position in the inventory
		this.getInventory().add(item);
		this.getGame().getMap().removeItem(item);

	}

	public void drop(Item item) {

		// Implemet a free place in the world

		item.setRelativePosition(this.getRelativePosition().x,
				this.getRelativePosition().y);
		this.getGame().getMap().addItem(item);
		this.getInventory().remove(item);
	}

	

	private int age = 0;

	public int getAge() {
		return age;
	}

	public void addAge() {
		this.age++;
	}

	private int maxHp;

	public int maxHp() {
		return maxHp;
	}

	private int hp;

	public int hp() {
		return hp;
	}

	private int attackValue;

	public int attackValue() {
		return attackValue;
	}

	private int defenseValue;

	public int defenseValue() {
		return defenseValue;
	}

	private CreatureAi ai;

	public void setCreatureAi(CreatureAi ai) {
		this.ai = ai;
	}

	public void dig(int wx, int wy) {
		this.getGame().getMap().dig(wx, wy);
	}

	public void eat() {

	}

	public void moveBy(int mx, int my) {
		if (mx == 0 && my == 0) {
			return;
		}

		Creature other = this
				.getGame()
				.getMap()
				.creature(this.getRelativePosition().x + mx,
						this.getRelativePosition().y + my);

		if (other == null) {
			ai.onEnter(this.getRelativePosition().x + mx,
					this.getRelativePosition().y + my);
		} else if (this.getName() == other.getName()) {
			return;

		} else {
			this.attack(other);
		}

	}

	public void update() {
		this.ai.onUpdate();
	}

	public void attack(Creature other) {

		int amount = Math.max(0, attackValue() - other.defenseValue());

		amount = (int) (Math.random() * amount) + 1;

		other.modifyHp(-amount);
	}

	public void modifyHp(int amount) {
		hp += amount;

		this.getGame()
				.getInfoManager()
				.addInfo(
						new Info("" + amount, this.getPosition().getVector2(),
								0.5f, Color.DARK_GRAY, 1,
								InfoEffet.TREMBLEMENT, 1f));

		if (this.isDead()) {

			this.getGame()
					.getMap()
					.addItem(
							(Item) this
									.getGame()
									.getItemFactory()
									.createFood(this.getRelativePosition().x,
											this.getRelativePosition().y));

			this.getGame().getMap().removeCreature(this);
		}

	}

	public boolean isDead() {
		if (hp < 1)
			return true;
		return false;

	}

	private int visionRadius;

	public int getVisionRadius() {
		return visionRadius;
	}

	public void setVisionRadius(int radius) {
		this.visionRadius = radius;
	}

	public boolean canSee(int wx, int wy, int wz) {
		return ai.canSee(wx, wy, wz);
	}

}
