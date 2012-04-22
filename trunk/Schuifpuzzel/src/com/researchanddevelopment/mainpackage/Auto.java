package com.researchanddevelopment.mainpackage;

import android.graphics.Point;

public class Auto {
	private Point pos;
	private final int length;
	private final Orientation orientation;
	private boolean goalcar = false;

	public static enum Orientation {HORIZONTAAL, VERTICAAL};
	
	/**
	 * Een nieuwe auto maken - nooit goalcar
	 * 
	 * @param pos
	 * @param width
	 * @param height
	 * @param orientation
	 */
	public Auto(Point pos, int length, Orientation orientation){
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
	public Auto(Point pos, int length, Orientation orientation, boolean goalcar) {
		this(pos, length, orientation);
		this.goalcar = goalcar;
	}

	/**
	 * @return the pos
	 */
	public Point getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Point pos) {
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
	 * Find out if a car is on a position
	 * 
	 * @param positie
	 * @return is this car on position p?
	 */
	public boolean onPosition(Point p) {
		if(this.pos.x == p.x && this.pos.y == p.y)
			return true;
		else if(this.orientation.equals(Orientation.HORIZONTAAL) && (p.x - pos.x - length <= 0) && p.y == pos.y) 
			return true;
		else if(this.orientation.equals(Orientation.VERTICAAL) && (p.y - pos.y - length <= 0 ) && p.x == pos.x)
			return true;
					
		return false;
	}
}
