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
				Auto activecar = null;
				System.out.println(v.toString());

				for (Auto auto : this.auto) {
					if (auto.touchOnPosition(e.getX(), e.getY(), BoardView.TILE_SIZE)) {
						activecar = auto;
						break;
					}
				}

				ac = activecar;
				break;

			case MotionEvent.ACTION_UP:
				Log.d("AutoListener", "ac null");
				ac = null;
				break;

			case MotionEvent.ACTION_MOVE:
				if (ac != null) {
					Log.d("AutoListener", "ac not null");

					if (ac.getOrientation() == Orientation.HORIZONTAAL) {
						ac.setPos(new PointF((float) (ac.getPos().x + 0.01), ac.getPos().y));
					} else {
						Log.d("AutoListener", "Verticaal");
						ac.setPos(new PointF(ac.getPos().x, (float) (ac.getPos().y + 0.01)));
					}

				}
				break;
		}
		v.invalidate();
		return true;
	}

}
