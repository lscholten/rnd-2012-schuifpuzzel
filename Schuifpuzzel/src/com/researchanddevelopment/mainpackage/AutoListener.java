package com.researchanddevelopment.mainpackage;

import java.util.ArrayList;

import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.researchanddevelopment.mainpackage.Auto.Orientation;

public class AutoListener implements OnTouchListener {

	private PointF start;
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
				start = new PointF(e.getX(), e.getY());
				break;

			case MotionEvent.ACTION_UP:
				if(ac != null){
					Log.d("AutoListener", "ac null");
					ac.addMove();
					ac.setPos(new PointF(Math.round(ac.getPos().x), Math.round(ac.getPos().y)));
					ac = null;
				}
				
				break;

			case MotionEvent.ACTION_MOVE:
				if (ac != null) {
					Log.d("AutoListener", "ac not null");
					
					boolean isCollision = false;
					PointF oldPosition = ac.getPos();
					PointF newPosition;
					
					if (ac.getOrientation() == Orientation.HORIZONTAAL) {
						newPosition = new PointF(ac.getPos().x + ((e.getX() - start.x) / BoardView.TILE_SIZE), ac
								.getPos().y);
					} else {
						newPosition = (new PointF(ac.getPos().x, ac.getPos().y
								+ ((e.getY() - start.y) / BoardView.TILE_SIZE)));
					}
					
					ac.setPos(newPosition);
					
					
					for(Auto auto: this.auto){
						if(ac.collide(auto)){
							isCollision = true;
							ac.setPos(new PointF(Math.round(ac.getPos().x), Math.round(ac.getPos().y)));
							break;
						}
					}
					if(isCollision || ac.outOfBounds()){
						ac.setPos(oldPosition);
					} else if (ac.isWinPosition()) {
						ac.setPos(new PointF(Board.BOARD_SIZE-1, 2F));
						System.out.println("win + moves: " + ac.getMoves());
					}

				}
				start = new PointF(e.getX(), e.getY());
				break;
		}
		v.invalidate();
		return true;
	}

}
