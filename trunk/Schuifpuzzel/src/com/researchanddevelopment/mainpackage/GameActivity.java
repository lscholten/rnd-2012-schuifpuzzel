package com.researchanddevelopment.mainpackage;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class GameActivity extends Activity {
	GameView view;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		view = new GameView(this);
		view.setBackgroundColor(Color.BLACK);
		
		setContentView(view);
		
		
		
	}
	
	
	
	
	
	
	
}
