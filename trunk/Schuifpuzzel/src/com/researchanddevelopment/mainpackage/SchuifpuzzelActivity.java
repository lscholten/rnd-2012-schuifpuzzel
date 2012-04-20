package com.researchanddevelopment.mainpackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SchuifpuzzelActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schuifpuzzel);
	}
	
	public void onClickStartGame(View v) {
		Intent i = new Intent();
		i.setClass(getApplicationContext(), GameActivity.class);
		startActivity(i);
	}
}