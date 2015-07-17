package com.phil.rogue.info;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Info {

	public enum InfoEffet {
		NULL, TREMBLEMENT;
	}

	public Info(String data, Vector2 position, float life, Color color,
			float size, InfoEffet effet, float vitesse) {
		this.setData(data);
		this.setPosition(position);
		this.color = color;
		this.size = size;
		this.effet = effet;
		this.life = life;
		this.vitesse = vitesse;
	}

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	private Vector2 position;

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void move(float x, float y) {
		this.position.add(x, y);
	}

	private float life;

	public void decrementeLife(float delta) {
		this.life -= delta;
	}

	public boolean isDead() {

		if (this.life <= 0) {
			return true;
		}

		return false;
	}

	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	private float size;

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	private InfoEffet effet;

	public InfoEffet getEffet() {
		return effet;
	}

	public void setEffet(InfoEffet effet) {
		this.effet = effet;
	}

	private float vitesse;

	public float getVitesse() {
		return vitesse;
	}

	public void setVitesse(float vitesse) {
		this.vitesse = vitesse;
	}

}
