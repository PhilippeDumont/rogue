package com.phil.rogue.utils;

import com.badlogic.gdx.Gdx;
import com.phil.rogue.constant.GameConstants;

public class PositionUtils {

	// ++++++++++++++++++++
	// FONCTION DE POSITION
	// ++++++++++++++++++++

	// absolute = (100,200)
	// relative = (2,4)

	/**
	 * You give a relative position (2,4) and this fonction convert in absolute
	 * position (200,400).
	 * 
	 * @param position
	 *            (relative)
	 * @return absolute position
	 */
	public static Point convertToAbsolutePosition(Point position) {
		Point retour = new Point(0, 0, 0);
		retour.x = position.x * GameConstants.TILE_SIZE;
		retour.y = position.y * GameConstants.TILE_SIZE;
		return retour;
	}

	/**
	 * You give a absolute position (200,400) and this fonction convert in
	 * relative position (2,4).
	 * 
	 * @param position
	 *            (absolute)
	 * @return relative position
	 */
	public static Point convertToRelativePosition(Point position) {
		Point retour = new Point(0, 0, 0);
		retour.x += position.x / GameConstants.TILE_SIZE;
		retour.y += position.y / GameConstants.TILE_SIZE;
		return retour;
	}

	/**
	 * return the position of mouse on the screen.
	 * 
	 * @param x
	 *            is the original x position of the mouse
	 * @param y
	 *            is the original y position of the mouse
	 * @return the correct position on the screen
	 */
	public static Point pointdeLaSouris(int x, int y) {
		int newX = x;
		int newY = (-y + Gdx.graphics.getHeight());
		return new Point(newX, newY, 0);
	}

	// ++++++++++++++++++++++++++++++++
	// FONCTION DE POSITION ISOMETRIQUE
	// ++++++++++++++++++++++++++++++++

	/**
	 * On donne la position relative on recois la position absolue
	 * 
	 * @param position
	 *            relative
	 * @param entity
	 * @return position absolue
	 */
	public static Point convertToAbsolutePosition3DIso(Point position) {
		Point retour = new Point(0, 0, 0);

		retour.x = position.x * (GameConstants.TILE_SIZE / 2) + position.y
				* -(GameConstants.TILE_SIZE / 2);

		retour.y = position.x * ((GameConstants.TILE_SIZE / 2) / 2)
				+ position.y * ((GameConstants.TILE_SIZE / 2) / 2);
		return retour;
	}

	public static Point convertToAbsolutePosition3DIso(int x, int y) {
		Point retour = new Point(0, 0, 0);

		retour.x = x * (GameConstants.TILE_SIZE / 2) + y
				* -(GameConstants.TILE_SIZE / 2);

		retour.y = x * ((GameConstants.TILE_SIZE / 2) / 2) + y
				* ((GameConstants.TILE_SIZE / 2) / 2);

		return retour;
	}

	/**
	 * On rentre un point en valeur absolue, on recois la valeur relative du
	 * point
	 * 
	 * @param x
	 * @param y
	 * @param game
	 * @return position relative
	 */
	public static Point convertToRelativePosition3DIso(int x, int y) {

		Point retour = new Point(0,0,0);

		retour.x = (x + (2 * y)) / GameConstants.TILE_SIZE;
		retour.y = -(x - (GameConstants.TILE_SIZE / 2) * retour.x)
				/ (GameConstants.TILE_SIZE / 2);

		return retour;

	}
}
