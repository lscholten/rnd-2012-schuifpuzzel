package com.researchanddevelopment.mainpackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SchuifpuzzelActivity extends Activity {

	/* Called on creation
	 * (non-Javadoc)
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
		i.setClass(getApplicationContext(), GameActivity.class);
		startActivity(i);
	}
}