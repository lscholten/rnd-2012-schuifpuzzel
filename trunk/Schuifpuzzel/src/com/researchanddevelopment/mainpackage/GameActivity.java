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
 * @author Paranoid Android
 *
 */
public class GameActivity extends Activity {
	BoardView view;
	private String resourcename;
	private int gameid;

	Board bord;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		resourcename = "game01";
		gameid = 1;
		if (this.getIntent().hasExtra("gameid")){
			resourcename = String.format("game%02d", this.getIntent()
					.getExtras().getInt("gameid"));
			this.getIntent().getExtras().getInt("gameid");
		}
	

		int res = getResources().getIdentifier(resourcename, "xml",
				this.getPackageName());
		bord = new Board(getResources().getXml(res), this, gameid);
		view = new BoardView(this);
		view.setBackgroundColor(Color.BLACK);

		bord.setBoardView(view);

		setContentView(view);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.game_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.Reset:
				int res = getResources().getIdentifier(resourcename, "xml",
						this.getPackageName());
				bord = new Board(getResources().getXml(res), this, gameid);
				view = new BoardView(this);
				view.setBackgroundColor(Color.BLACK);

				bord.setBoardView(view);

				setContentView(view);
				return true;
			default:
				return false;
		}
	}
	

}
