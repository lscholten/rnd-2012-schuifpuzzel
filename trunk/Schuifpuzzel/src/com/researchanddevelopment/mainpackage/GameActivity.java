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
		bord = new Board(getResources().getXml(R.xml.game));
		view = new BoardView(this);
		view.setBackgroundColor(Color.BLACK);
		
		bord.setBoardView(view);
		
		
		
		setContentView(view);
		
		
		
	}
	
	
	
	
	
	
	
}
