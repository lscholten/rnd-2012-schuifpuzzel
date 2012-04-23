/**
 * 
 */
package com.researchanddevelopment.mainpackage;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

/**
 * @author Thom
 *
 */
public class LevelSelectActivity extends PreferenceActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		PreferenceScreen root = this.getPreferenceManager().createPreferenceScreen(getApplicationContext());
		
		for(int i = 1; i <= 40; i++) {
			final int id = i;
			Preference d = new Preference(getApplicationContext());
			d.setTitle("Level " + i);
			d.setSummary("lol");
			d.setOnPreferenceClickListener(new OnPreferenceClickListener() {
				public boolean onPreferenceClick(Preference arg0) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), GameActivity.class);
					intent.putExtra("gameid", id);
					LevelSelectActivity.this.startActivity(intent);
					return true;
				}
			});
			root.addPreference(d);
		}
		
		this.setPreferenceScreen(root);
	
		
	}
}
