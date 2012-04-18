package com.researchanddevelopment.mainpackage;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class SchuifpuzzelActivityListener implements OnClickListener {

	Activity schuifmenu;
	
	
	
	public SchuifpuzzelActivityListener(
			SchuifpuzzelActivity schuifpuzzelActivity) {
		this.schuifmenu = schuifpuzzelActivity;
	}



	@Override
	public void onClick(View arg0) {
		int id = arg0.getId();
		
		if(id == R.id.startGame){
			Intent i = new Intent("com.researchanddevelopment.mainpackage.MAINGAME");
			schuifmenu.startActivity(i);
		}

	}

}
