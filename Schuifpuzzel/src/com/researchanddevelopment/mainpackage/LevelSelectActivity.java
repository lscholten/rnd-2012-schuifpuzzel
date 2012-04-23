/**
 * 
 */
package com.researchanddevelopment.mainpackage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

/**
 * Level select scherm
 * 
 * @author Paranoid Android Thom Wiggers - 4119444 Luuk Scholten - 4126424 Koen
 *         Basten - 4119657
 * 
 */
public class LevelSelectActivity extends PreferenceActivity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PreferenceScreen root = this.getPreferenceManager().createPreferenceScreen(
				getApplicationContext());
		Database db_ad = new Database(getApplicationContext());
		Cursor dbcursor = db_ad.getReadableDatabase().query(Database.DATABASE_TABLE_NAME,
				new String[] { "least_moves" }, null, null, null, null, null);
		dbcursor.moveToFirst();

		for (int i = 1; i <= 40; i++) {
			final int id = i;
			final boolean hasMoves = !dbcursor.isNull(0);

			Preference pref = new Preference(getApplicationContext());
			pref.setTitle("Level " + i);
			pref.setSummary(hasMoves ? "Least amount of moves used: " + dbcursor.getInt(0) : "");
			pref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
				public boolean onPreferenceClick(Preference arg0) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), GameActivity.class);
					intent.putExtra("gameid", id);
					LevelSelectActivity.this.startActivity(intent);
					return true;
				}
			});
			root.addPreference(pref);

			dbcursor.moveToNext();
		}
		dbcursor.close();
		db_ad.close();

		this.setPreferenceScreen(root);

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
