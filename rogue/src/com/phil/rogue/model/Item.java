package com.phil.rogue.model;

import com.phil.rogue.constant.GameConstants.Cons;
import com.phil.rogue.screen.GameScreen;

public class Item extends Entity {

	private int food;
	private int attack;
	private int deffence;

	public Item(Cons name, GameScreen game) {
		super(name, game);

		this.food = 0;
		this.attack = 0;
		this.deffence = 0;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDeffence() {
		return deffence;
	}

	public void setDeffence(int deffence) {
		this.deffence = deffence;
	}

}
