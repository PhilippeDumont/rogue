package com.phil.rogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class Point {
	public int x;
	public int y;
	public int z;

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public List<Point> neighbors8() {
		List<Point> points = new ArrayList<Point>();

		for (int ox = -1; ox < 2; ox++) {
			for (int oy = -1; oy < 2; oy++) {
				if (ox == 0 && oy == 0)
					continue;

				points.add(new Point(x + ox, y + oy, z));
			}
		}

		Collections.shuffle(points);
		return points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	public boolean equals(Point other) {
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	public void add(int mx, int my, int mz) {
		this.x += mx;
		this.y += my;
		this.z += mz;
	}

	public Vector2 getVector2() {
		return new Vector2(this.x, this.y);
	}

	public void setVector2(Vector2 v) {
		this.x = (int) v.x;
		this.y = (int) v.y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

}