package com.phil.rogue.factory;

import com.phil.rogue.constant.GameConstants.Cons;
import com.phil.rogue.model.Entity;
import com.phil.rogue.model.Item;
import com.phil.rogue.model.Entity.DisplayOrder;
import com.phil.rogue.screen.GameScreen;

public class ItemFactory extends Factory {

	public ItemFactory(GameScreen game) {
		super(game);
	}

	public Item createItem(Cons name, int x, int y) {

		Item item = new Item(name, this.getGame());

		item.setRelativePosition(x, y);

		item.setDisplayOrder(DisplayOrder.UN);
		item.addGroups(Cons.ITEM);
		item.addGroups(name);

		return item;
	}

	public Entity createFood(int x, int y) {

		Item food = this.createItem(Cons.FOOD, x, y);
		food.setFood(20);

		return food;
	}

}
