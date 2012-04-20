package com.researchanddevelopment.mainpackage;

import android.util.Xml;

public class Board {
	public static final int BOARD_SIZE = 7;
	
	private Auto[] autos;
	
	public Board(Auto[] autos) {
		this.autos = autos;
		
	}
	
	public Board(Xml parser) {
		
	}
}
