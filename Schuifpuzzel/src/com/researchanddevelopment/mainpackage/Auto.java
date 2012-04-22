package com.researchanddevelopment.mainpackage;

import android.graphics.Point;

public class Auto {
	private Point pos;
	private final int length;
	private final Orientation orientation;
	private boolean goalcar = false;

	public static enum Orientation {HORIZONTAAL, VERTICAAL};
	
	/**
	 * Een nieuwe auto maken
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
}
