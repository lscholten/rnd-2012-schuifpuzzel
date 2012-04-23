package com.researchanddevelopment.mainpackage;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.researchanddevelopment.mainpackage.Auto.Orientation;

public class AutoListener implements OnTouchListener {

	private PointF start;
	private Auto ac;
	private int moves;
	ArrayList<Auto> auto;
	private final int level;
	private Activity act;
	private BoardView b;
	private boolean won = false;

	public AutoListener(ArrayList<Auto> a, Activity act, int level, BoardView b) {
		this.auto = a;
		this.act = act;
		this.level = level;
		this.b = b;
	}

	public boolean onTouch(View v, MotionEvent e) {
		if (won){ 
			return false;
		}
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
					this.moves++;
					b.updateMoves(moves);
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
						this.moves++;
						b.updateMoves(moves);
						this.won = true;
						win(v);
				
						
					}

				}
				start = new PointF(e.getX(), e.getY());
				break;
		}
		v.invalidate();
		return true;
	}
	
	private void win(View v){
		b.drawWin();
		v.invalidate();
		Database db = new Database(act.getApplicationContext());
		
		SQLiteDatabase dbase = db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("gameid", this.level);
		values.put("least_moves", this.moves);
		dbase.update(Database.DATABASE_TABLE_NAME, values, "gameid = ? AND (least_moves > ? OR least_moves IS NULL)", new String[] {this.level + "", this.moves + ""});
		
		
		
	}

}
