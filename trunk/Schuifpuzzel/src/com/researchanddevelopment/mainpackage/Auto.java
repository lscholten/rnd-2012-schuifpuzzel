package com.researchanddevelopment.mainpackage;

import android.graphics.PointF;

/**
 * Model van autos
 * 
 * @author Paranoid Android
 *
 */
public class Auto {
	private PointF pos;
	private final int length;
	private final Orientation orientation;
	private boolean goalcar = false;
	private int moves = 0;

	public static enum Orientation {
		HORIZONTAAL, VERTICAAL
	};

	/**
	 * Een nieuwe auto maken - nooit goalcar
	 * 
	 * @param pos
	 * @param width
	 * @param height
	 * @param orientation
	 */
	public Auto(PointF pos, int length, Orientation orientation) {
		if (orientation == null || length < 2 || pos.x < 0
				|| pos.x > Board.BOARD_SIZE || pos.y < 0
				|| pos.y > Board.BOARD_SIZE)
			throw new IllegalArgumentException("Fix your arguments");

		this.pos = pos;
		this.length = length;
		this.orientation = orientation;
	}

	/**
	 * Nieuwe auto maken
	 * 
	 * @param pos
	 * @param length
	 * @param orientation
	 * @param goalcar
	 */
	public Auto(PointF pos, int length, Orientation orientation, boolean goalcar) {
		this(pos, length, orientation);
		this.goalcar = goalcar;
	}

	/**
	 * @return the pos
	 */
	public PointF getPos() {
		return pos;
	}

	/**
	 * @param pos
	 *            the pos to set
	 */
	public void setPos(PointF pos) {
		this.pos = pos;
	}

	/**
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return is this the goal car
	 */
	public boolean isGoalcar() {
		return goalcar;
	}

	/**
	 * Check for collision
	 * 
	 * @param a
	 * @return
	 */
	public boolean collide(Auto a, int signum) {
		if (a.equals(this)) {
			return false;
		}

		if (orientation == Orientation.HORIZONTAAL) {
			if (a.orientation == Orientation.HORIZONTAAL) {
				return ((pos.x < a.pos.x + a.length && pos.x > a.pos.x) || (pos.x
						+ length > a.pos.x && pos.x + length < a.pos.x
						+ a.length))
						&& pos.y == a.pos.y;

			} else if (a.orientation == Orientation.VERTICAAL) {
				if (signum >= 0) {
					return (a.pos.x <= pos.x + length) && a.pos.x > pos.x
							&& pos.y >= a.pos.y && pos.y < a.pos.y + a.length;

				} else {
					return (a.pos.x + 1 < pos.x + length) && a.pos.x + 1 >= pos.x
							&& pos.y >= a.pos.y && pos.y < a.pos.y + a.length;
				}

			}

		} else if (orientation == Orientation.VERTICAAL) {
			if (a.orientation == Orientation.HORIZONTAAL) {
				if (signum == 1) {
					return (a.pos.y <= pos.y + length) && a.pos.y > pos.y
							&& pos.x + 1 >= a.pos.x + 1 && pos.x + 1 < a.pos.x + 1 + a.length;
				} else {
					return (a.pos.y + 1 < pos.y + length) && a.pos.y + 1 >= pos.y
							&& pos.x + 1 >= a.pos.x + 1 && pos.x + 1< a.pos.x + 1 + a.length;
				}

			} else if (a.orientation == Orientation.VERTICAAL) {
				return ((pos.y < a.pos.y + a.length && pos.y > a.pos.y) || (pos.y
						+ length > a.pos.y && pos.y + length < a.pos.y
						+ a.length))
						&& pos.x == a.pos.x;

			}
		}

		return false;

	}

	/**
	 * @return
	 */
	public boolean outOfBounds() {
		if (goalcar) {
			return pos.x < 0;
		}
		if (orientation == Orientation.HORIZONTAAL) {
			return pos.x < 0 || pos.x + length > Board.BOARD_SIZE;
		} else {
			return pos.y < 0 || pos.y + length > Board.BOARD_SIZE;
		}
	}

	/**
	 * Find out if the car is in the goal position
	 * 
	 * @return is this the goal position?
	 */
	public boolean isWinPosition() {
		return goalcar && pos.x + length / 2 >= Board.BOARD_SIZE;
	}

	/**
	 * @param x
	 * @param y
	 * @param TILE_SIZE
	 * @return
	 */
	public boolean touchOnPosition(double x, double y, int TILE_SIZE) {
		float xLeft = this.pos.x;
		float yTop = this.pos.y;
		int length = this.length;

		boolean touched = false;
		if (this.orientation == Orientation.HORIZONTAAL)
			touched = 5 + TILE_SIZE * xLeft <= x && 5 + TILE_SIZE * yTop <= y
					&& TILE_SIZE * (xLeft + length) >= x
					&& TILE_SIZE * (yTop + 1) >= y;
		if (this.orientation == Orientation.VERTICAAL)
			touched = 5 + TILE_SIZE * xLeft <= x && 5 + TILE_SIZE * yTop <= y
					&& TILE_SIZE * (xLeft + 1) >= x
					&& TILE_SIZE * (yTop + length) >= y;

		return touched;
	}

	public void addMove() {
		moves++;
	}

	public int getMoves() {
		return moves;
	}
}
