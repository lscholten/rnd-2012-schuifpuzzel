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
		switch (e.getAction()) {

			case MotionEvent.ACTION_DOWN:
				Auto activecar = null;

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
					ac.addMove();
					ac.setPos(new PointF(Math.round(ac.getPos().x), Math.round(ac.getPos().y)));
					ac = null;
				}
				
				break;

			case MotionEvent.ACTION_MOVE:
				if (ac != null) {
					
					boolean isCollision = false;
					PointF oldPosition = ac.getPos();
					PointF newPosition;
					float movement;
					
					if (ac.getOrientation() == Orientation.HORIZONTAAL) {
						movement = e.getX() - start.x; 
						movement = (movement > BoardView.TILE_SIZE) ? BoardView.TILE_SIZE : movement;
						newPosition = new PointF(ac.getPos().x + (movement) / BoardView.TILE_SIZE, ac
								.getPos().y);
						
					} else {
						movement = e.getY() - start.y; 
						movement = (movement > BoardView.TILE_SIZE) ? BoardView.TILE_SIZE : movement;
						newPosition = (new PointF(ac.getPos().x, ac.getPos().y
								+ ((movement) / BoardView.TILE_SIZE)));
					}
					
					ac.setPos(newPosition);
					
					
					for(Auto auto: this.auto){
						if(ac.collide(auto, (int)Math.signum(movement))){
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
