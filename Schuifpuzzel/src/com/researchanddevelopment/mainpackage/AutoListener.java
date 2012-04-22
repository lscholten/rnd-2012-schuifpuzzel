package com.researchanddevelopment.mainpackage;

import java.util.ArrayList;

import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.researchanddevelopment.mainpackage.Auto.Orientation;

public class AutoListener implements OnTouchListener {

	private Auto ac;
	ArrayList<Auto> auto;

	public AutoListener(ArrayList<Auto> a) {
		this.auto = a;
	}

	public boolean onTouch(View v, MotionEvent e) {
		Log.d("AutoListener", "Action: " + e.getAction());
		switch (e.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d("AutoListener", "ACTION_DOWN");
				break;
			case MotionEvent.ACTION_UP:
				Log.d("AutoListener", "ACTION_UP");
				break;
		}

		Auto activecar = null;
		System.out.println(v.toString());
		for (Auto auto : this.auto) {
			if (auto.touchOnPosition(e.getX(), e.getY(), BoardView.TILE_SIZE)) {
				activecar = auto;
				break;
			}
		}
		if (activecar != null) {
			if (activecar.getOrientation() == Orientation.HORIZONTAAL)
				activecar.setPos(new PointF(activecar.getPos().x + 1, activecar.getPos().y));
			else
				activecar.setPos(new PointF(activecar.getPos().x, activecar.getPos().y + 1));
		}
		return true;
	}

}
