package com.researchanddevelopment.mainpackage;

import android.graphics.Point;

public class Auto {
	private Point pos;
	private int length;
	private Orientation orientation;

	public static enum Orientation {HORIZONTAAL, VERTICAAL};
	
	public Auto(Point pos, int length, Orientation orientation){
		this.pos = pos;
		this.length = length;
		this.orientation = orientation;
	}
}
