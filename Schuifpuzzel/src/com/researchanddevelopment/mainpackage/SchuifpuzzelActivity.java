package com.researchanddevelopment.mainpackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SchuifpuzzelActivity extends Activity {

	private Button b;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/**
	 * past titel aan
	 * 
	 * @param v
	 */
	public void AboutLaunch(View v) {
		((Button) v).setText("Porno is op");
		TextView t = (TextView) findViewById(R.id.titel1);
		t.setText("Porno");
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
		switch (item.getItemId()) {
		case R.id.about:
			Intent i = new Intent(
					"com.researchanddevelopment.mainpackage.ABOUT");
			startActivity(i);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}

}