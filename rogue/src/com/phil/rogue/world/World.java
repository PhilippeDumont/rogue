package com.phil.rogue.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.phil.rogue.constant.GameConstants.Cons;
import com.phil.rogue.model.Entity;

public class World {

	List<Entity> entities;

	public World() {
		this.entities = new ArrayList<Entity>();
	}

	public int getNbrOfEntities() {
		return this.entities.size();
	}

	public void add(Entity entity) {
		this.entities.add(entity);
	}

	public void addAll(Collection<Entity> entities) {
		for (Entity e : entities) {
			this.add(e);
		}
	}

	public List<Entity> getEntities(Vector2 position) {

		List<Entity> res = new ArrayList<Entity>();

		for (Entity entity : this.entities) {
			if (entity.getPosition().equals(position)) {
				res.add(entity);
			}
		}
		return res;
	}

	public List<Entity> getEntities(Cons group) {
		if (this.entities.size() > 0) {
			List<Entity> res = new ArrayList<Entity>();
			for (Entity entity : this.entities) {
				if(entity != null){
				
				if (entity.isInGroup(group))
					res.add(entity);
				}
			}
			return res;
		}
		return new ArrayList<Entity>();
	}

	public boolean remove(Entity entity) {
		return this.entities.remove(entity);
	}

	public void removeAll() {
		entities.clear();
	}

	public List<Entity> getEntities() {
		return entities;
	}

}
