package com.researchanddevelopment.mainpackage;

import java.util.ArrayList;

import com.researchanddevelopment.mainpackage.Auto.Orientation;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class AutoListener implements OnTouchListener{

	ArrayList<Auto> auto;
	
	public AutoListener(ArrayList<Auto> a){
		this.auto = a;
	}
	
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Auto activecar = null;
		System.out.println(v.toString());
		for(Auto auto : this.auto){
			if(auto.touchOnPosition(event.getX(), event.getY(), BoardView.TILE_SIZE)){
				activecar = auto;
				break;
			}
		}
		if(activecar != null){
			if(activecar.getOrientation() == Orientation.HORIZONTAAL)
				activecar.setPos(new PointF(activecar.getPos().x + 1, activecar.getPos().y));
			else
				activecar.setPos(new PointF(activecar.getPos().x, activecar.getPos().y + 1));
		}
		v.invalidate();
		return false;
	}

}
