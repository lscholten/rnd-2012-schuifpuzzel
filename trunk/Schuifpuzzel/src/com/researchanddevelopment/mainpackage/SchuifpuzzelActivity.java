package com.researchanddevelopment.mainpackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class SchuifpuzzelActivity extends Activity {

	/*
	 * Called on creation (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schuifpuzzel);
	}

	/**
	 * De Start knop op het homescherm is ingedrukt
	 * 
	 * @param v
	 */
	public void onClickStartButton(View v) {
		Intent i = new Intent();
		i.setClass(getApplicationContext(), LevelSelectActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.new_game:
				return true;
			case R.id.difficulty:
				return true;
			default:
				return false;
		}
	}
}