package com.researchanddevelopment.mainpackage;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class GameActivity extends Activity {
	BoardView view;
	
	Board bord ;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int res = getResources().getIdentifier("game01", "xml", this.getPackageName());
		bord = new Board(getResources().getXml(res));
		view = new BoardView(this);
		view.setBackgroundColor(Color.BLACK);
		
		bord.setBoardView(view);
		
		setContentView(view);
	}
	
	
	
	
	
	
	
}
