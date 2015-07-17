package com.phil.rogue.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.phil.rogue.constant.GameConstants.Cons;
import com.phil.rogue.screen.GameScreen;
import com.phil.rogue.utils.Point;
import com.phil.rogue.utils.PositionUtils;

public abstract class Entity implements Comparable<Entity> {

	public enum DisplayOrder {
		ZERO, UN, DEUX;

		public int getOrderId() {
			return ordinal();
		}
	}

	public Entity(Cons name, GameScreen game) {

		this.name = name;
		this.position = new Point(0,0,0);
		this.rotation = 0;
		this.color = Color.WHITE;
		this.sizeX = 1;
		this.sizeY = 1;
		this.groups = new ArrayList<Cons>();
		this.displayOrder = DisplayOrder.ZERO;
		this.game = game;

		this.addGroups(Cons.ENTITY);

	}

	// NAME
	public Cons getName() {
		return name;
	}

	public void setName(Cons name) {
		this.name = name;
	}

	private Cons name;

	// POSITION
	private Point position;

	public Point getPosition() {
		return position;
	}

	public Point getRelativePosition() {
		return PositionUtils.convertToRelativePosition3DIso(this.position.x,
				this.position.y);
	}

	public void setRelativePosition(int x, int y) {
		this.position = PositionUtils.convertToAbsolutePosition3DIso(x, y);
	}

	public void move(int mx, int my, int mz) {
		this.position.add(mx, my, mz);

	}

	public int compareTo(Entity other) {
		Point positionOther = other.getPosition();
		Point position = this.getPosition();

		int sommeOther = positionOther.y * 1000 + positionOther.x;
		int somme = position.y * 1000 + position.x;

		if (position.equals(positionOther)) {
			return 0;
		} else if (sommeOther < somme) {
			return -1;
		} else {
			return 1;
		}
	}

	// ROTATION
	private float rotation;

	public float getRotation() {
		return this.rotation;
	}

	public void rotate(float angle) {
		if (angle + rotation > 360) {
			rotation = rotation + angle - 360;
		} else {
			this.rotation += angle;
		}

	}

	// COLOR
	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	// SIZEX
	private float sizeX;

	public float getSizeX() {
		return sizeX;
	}

	public void setSizeX(float sizeX) {
		this.sizeX = sizeX;
	}

	// SIZEY
	private float sizeY;

	public float getSizeY() {
		return sizeY;
	}

	public void setSizeY(float sizeY) {
		this.sizeY = sizeY;
	}

	// GROUPS
	private List<Cons> groups;

	public boolean addGroups(Cons player) {
		return groups.addAll(Arrays.asList(player));
	}

	public void clearGroups() {
		groups.clear();
	}

	public boolean isInGroup(Cons group) {
		return groups.contains(group);
	}

	// DISPLAY ORDER
	private DisplayOrder displayOrder;

	public DisplayOrder getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(DisplayOrder displayOrder) {
		this.displayOrder = displayOrder;
	}

	// GAME
	private GameScreen game;

	public GameScreen getGame() {
		return game;
	}

}
