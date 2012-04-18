package com.researchanddevelopment.mainpackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

public class SchuifpuzzelActivity extends Activity {
	
	private Button b;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		b = (Button)findViewById(R.id.startGame);
		b.setOnClickListener(new SchuifpuzzelActivityListener(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.about:
			Intent i = new Intent("com.researchanddevelopment.mainpackage.ABOUT");
			startActivity(i);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}
	
	

	
}