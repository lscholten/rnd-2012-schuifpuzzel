package com.researchanddevelopment.mainpackage;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class AutoListener implements OnTouchListener{

	Auto auto;
	
	public AutoListener(Auto a){
		this.auto = a;
	}
	
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
