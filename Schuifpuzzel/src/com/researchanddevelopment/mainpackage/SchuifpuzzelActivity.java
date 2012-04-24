package com.researchanddevelopment.mainpackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * Het hoofdmenu
 * 
 * @author Paranoid Android Thom Wiggers - 4119444 Luuk Scholten - 4126424 Koen
 *         Basten - 4119657
 * 
 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	/**
	 * Opties selecteren in het popup-menu (hardware menu knop)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.new_game:
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), GameActivity.class);
				intent.putExtra("gameid",(int)(( Math.random() * 39)) + 1);
				this.startActivity(intent);
				return true;
			case R.id.exit:
				this.finish();
				return true;
			default:
				return false;
		}
	}

}