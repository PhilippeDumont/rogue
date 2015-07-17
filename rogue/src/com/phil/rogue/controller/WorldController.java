package com.phil.rogue.controller;

import java.util.Collections;
import java.util.Comparator;

import com.phil.rogue.model.Entity;
import com.phil.rogue.screen.GameScreen;

public class WorldController extends Controller {

	public WorldController(GameScreen game) {
		super(game);
	}

	@Override
	public void update() {

		// cette fonction trie les entite dans le monde

		Collections.sort(this.getGame().getWorld().getEntities(),
				new Comparator<Entity>() {
					@Override
					public int compare(Entity e1, Entity e2) {
						return e1.getDisplayOrder().compareTo(
								e2.getDisplayOrder());
					}
				});

		Collections.sort(this.getGame().getWorld().getEntities());

	}

}