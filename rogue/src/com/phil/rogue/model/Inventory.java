package com.phil.rogue.model;

import java.util.ArrayList;
import java.util.List;

import com.phil.rogue.screen.GameScreen;

public class Inventory {

	private List<Item> equipe;
	private List<Item> inventory;

	public Inventory(Creature owner, GameScreen game) {

		this.equipe = new ArrayList<Item>();
		this.inventory = new ArrayList<Item>();
	}

	public boolean add(Item i) {
		if (!this.isfull()) {
			this.inventory.add(i);
			return true;
		}

		return false;
	}

	public boolean isfull() {

		if (this.inventory.size() > 10) {
			return true;
		}
		return false;
	}

	public void remove(Item i) {
		this.inventory.remove(i);
	}

}