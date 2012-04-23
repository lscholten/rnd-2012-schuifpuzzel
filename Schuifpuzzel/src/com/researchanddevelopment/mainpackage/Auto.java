package com.researchanddevelopment.mainpackage;

import android.graphics.PointF;
import android.graphics.Paint.Align;

public class Auto {
	private PointF pos;
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
	public Auto(PointF pos, int length, Orientation orientation){
		if(orientation == null || length < 2 || pos.x < 0 || pos.x > Board.BOARD_SIZE || pos.y < 0 || pos.y > Board.BOARD_SIZE) 
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
	 * @param pos the pos to set
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
	 * Find out if a car is on a position
	 * 
	 * @param positie
	 * @return is this car on position p?
	 */
	public boolean collide(PointF p) {
		if(this.pos.x == p.x && this.pos.y == p.y)
			return true;
		else if(this.orientation.equals(Orientation.HORIZONTAAL) && (p.x - pos.x - length <= 0) && p.y == pos.y) 
			return true;
		else if(this.orientation.equals(Orientation.VERTICAAL) && (p.y - pos.y - length <= 0 ) && p.x == pos.x)
			return true;
					
		return false;
	}
	
	public boolean collide(Auto a){
		/* TODO
		 * Check for everything , this is not completely right, so lets fix this
		 * 
		 */
		if(a.equals(this))
			return false;
		if(orientation == Orientation.HORIZONTAAL && a.orientation == Orientation.HORIZONTAAL){
			return ((pos.x < a.pos.x + a.length && pos.x > a.pos.x) ||
					(pos.x + length > a.pos.x && pos.x + length < a.pos.x + a.length))
					&& pos.y == a.pos.y;
		}
		if(orientation == Orientation.HORIZONTAAL && a.orientation == Orientation.VERTICAAL){
			return ((pos.x < a.pos.x + 1 && pos.x > a.pos.x) ||
					(pos.x + length  > a.pos.x && pos.x + length  < a.pos.x + 1))
					&& ((pos.y >= a.pos.y && pos.y + 1 < a.pos.y + a.length) || 
							(pos.y > a.pos.y && pos.y + 1 <= a.pos.y + a.length));
		}
		if(orientation == Orientation.VERTICAAL && a.orientation == Orientation.HORIZONTAAL){
			return ((pos.y < a.pos.y + 1 && pos.y > a.pos.y) ||
					(pos.y + length > a.pos.y && pos.y + length < a.pos.y + 1))
					&& ((pos.x >= a.pos.x && pos.x + 1 < a.pos.x + a.length) || 
							(pos.x > a.pos.x && pos.x + 1 <= a.pos.x + a.length));
		}
		if(orientation == Orientation.VERTICAAL && a.orientation == Orientation.VERTICAAL){
			return ((pos.y < a.pos.y + a.length && pos.y > a.pos.y) ||
					(pos.y + length > a.pos.y && pos.y + length < a.pos.y + a.length))
					&& pos.x == a.pos.x;
		}
		
		return false;
				
	}
	
	public boolean outOfBounds(){
		if(isGoalcar())
			return pos.x < 0;
		if(orientation == Orientation.HORIZONTAAL)
			return pos.x < 0 || pos.x + length > Board.BOARD_SIZE;
		else
			return pos.y < 0 || pos.y + length > Board.BOARD_SIZE;	
	}
	
	public boolean touchOnPosition(double x, double y, int TILE_SIZE){
		float xLeft = this.pos.x;
		float yTop = this.pos.y;
		int length = this.length;

		boolean touched = false;
		if (this.orientation == Orientation.HORIZONTAAL)
			touched = 5 + TILE_SIZE * xLeft <= x && 5 + TILE_SIZE * yTop <= y
					&& TILE_SIZE * (xLeft + length) >= x && TILE_SIZE * (yTop + 1) >= y;
		if(this.orientation == Orientation.VERTICAAL)
			touched = 5 + TILE_SIZE * xLeft <= x && 5 + TILE_SIZE * yTop <= y
				&& TILE_SIZE * (xLeft + 1) >= x && TILE_SIZE * (yTop + length) >= y;
				
		return touched;
	}
}
