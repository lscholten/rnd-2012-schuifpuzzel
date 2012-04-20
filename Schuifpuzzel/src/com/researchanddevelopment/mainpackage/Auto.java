package com.researchanddevelopment.mainpackage;

import android.graphics.Point;

public class Auto {
	private Point pos;
	private int width, height;
	private Orientation orientation;

	public static enum Orientation {HORIZONTAAL, VERTICAAL};
	
	public Auto(Point pos, int width, int height, Orientation orientation){
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.orientation = orientation;
	}
}
