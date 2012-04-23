package com.researchanddevelopment.mainpackage;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Game Activity, beheert spel
 * 
 * @author Paranoid Android Thom Wiggers - 4119444 Luuk Scholten - 4126424 Koen
 *         Basten - 4119657
 * 
 */
public class GameActivity extends Activity {
	/**
	 * view
	 */
	BoardView view;

	/**
	 * game id
	 */
	private int gameid;

	/**
	 * Spelboard
	 */
	Board bord;

	/**
	 * Resource van de game xml
	 */
	private int gameResource;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String resourcename = "game01";
		gameid = 1;
		if (this.getIntent().hasExtra("gameid")) {
			gameid = this.getIntent().getExtras().getInt("gameid");
			resourcename = String.format("game%02d", gameid);

		}

		gameResource = getResources().getIdentifier(resourcename, "xml", this.getPackageName());
		bord = new Board(getResources().getXml(gameResource), this, gameid);
		view = new BoardView(this);
		view.setBackgroundColor(Color.BLACK);

		bord.setBoardView(view);

		setContentView(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.game_menu, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.Reset:
				bord = new Board(getResources().getXml(gameResource), this, gameid);
				view = new BoardView(this);
				view.setBackgroundColor(Color.BLACK);

				bord.setBoardView(view);

				setContentView(view);
				return true;
			default:
				return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

}
