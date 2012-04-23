package com.researchanddevelopment.mainpackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Splash screen
 * 
 * @author Paranoid Android
 *
 */
public class Splash extends Activity {
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.splash);

		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openStartingPoint = new Intent(
							getApplicationContext(), SchuifpuzzelActivity.class);
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
