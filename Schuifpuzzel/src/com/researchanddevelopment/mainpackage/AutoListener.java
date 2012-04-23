package com.researchanddevelopment.mainpackage;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.researchanddevelopment.mainpackage.Auto.Orientation;

/**
 * Events voor autos, handelt beweging af
 * 
 * @author Paranoid Android
 *
 */
/**
 * @author Paranoid Android
 * 
 */
public class AutoListener implements OnTouchListener {

	/**
	 * Position in grid coordinates of drag start
	 */
	private Point gridStartPos;
	/**
	 * Startpunt beweging
	 */
	private PointF start;
	/**
	 * Actieve car
	 */
	private Auto activeCar;
	/**
	 * aantal zetten
	 */
	private int moves;
	/**
	 * lijst met autos
	 */
	ArrayList<Auto> auto;
	/**
	 * levelnr
	 */
	private final int level;
	/**
	 * Activity
	 */
	private Activity act;
	/**
	 * View van speelboard
	 */
	private BoardView boardView;
	/**
	 * Gewonnen?
	 */
	private boolean won = false;

	/**
	 * Nieuwe AutoListener maken
	 * 
	 * @param autoList
	 *            Lijst met autos
	 * @param act
	 *            activity welke erbij hoort
	 * @param level
	 *            levelnummer
	 * @param boardView
	 *            view van het board
	 */
	public AutoListener(ArrayList<Auto> autoList, Activity act, int level, BoardView boardView) {
		this.auto = autoList;
		this.act = act;
		this.level = level;
		this.boardView = boardView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View,
	 * android.view.MotionEvent)
	 */
	public boolean onTouch(View v, MotionEvent e) {
		if (won) {
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

				activeCar = activecar;
				gridStartPos = new Point(Math.round(activeCar.getPos().x), Math.round(activeCar
						.getPos().y));

				start = new PointF(e.getX(), e.getY());
				break;

			case MotionEvent.ACTION_UP:
				if (activeCar != null) {
					if (!(gridStartPos.x == Math.round(activeCar.getPos().x) && gridStartPos.y == Math
							.round(activeCar.getPos().y))) {
						// er is bewogen
						this.moves++;
						boardView.updateMoves(moves);
					}
					activeCar.setPos(new PointF(Math.round(activeCar.getPos().x), Math
							.round(activeCar.getPos().y)));
					activeCar = null;
				}

				break;

			case MotionEvent.ACTION_MOVE:
				if (activeCar != null) {

					boolean isCollision = false;
					PointF oldPosition = activeCar.getPos();
					PointF newPosition;
					float movement;

					if (activeCar.getOrientation() == Orientation.HORIZONTAAL) {
						movement = e.getX() - start.x;
						movement = (movement > BoardView.TILE_SIZE) ? BoardView.TILE_SIZE
								: movement;
						newPosition = new PointF(activeCar.getPos().x + (movement)
								/ BoardView.TILE_SIZE, activeCar.getPos().y);

					} else {
						movement = e.getY() - start.y;
						movement = (movement > BoardView.TILE_SIZE) ? BoardView.TILE_SIZE
								: movement;
						newPosition = (new PointF(activeCar.getPos().x, activeCar.getPos().y
								+ ((movement) / BoardView.TILE_SIZE)));
					}

					activeCar.setPos(newPosition);

					for (Auto auto : this.auto) {
						if (activeCar.collide(auto, (int) Math.signum(movement))) {
							isCollision = true;
							activeCar.setPos(new PointF(Math.round(activeCar.getPos().x), Math
									.round(activeCar.getPos().y)));
							break;
						}
					}
					if (isCollision || activeCar.isOutOfBounds()) {
						activeCar.setPos(oldPosition);
					} else if (activeCar.isWinPosition()) {
						activeCar.setPos(new PointF(Board.BOARD_SIZE - 1, 2F));
						this.moves++;
						boardView.updateMoves(moves);
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

	/**
	 * Gewonnen? functies
	 * 
	 * @param v
	 *            view om te manipuleren
	 */
	private void win(View v) {
		v.invalidate();
		Database db = new Database(act.getApplicationContext());

		SQLiteDatabase dbase = db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("gameid", this.level);
		values.put("least_moves", this.moves);
		int row = dbase.update(Database.DATABASE_TABLE_NAME, values,
				"gameid = ? AND (least_moves > ? OR least_moves IS NULL)", new String[] {
						this.level + "", this.moves + "" });

		boardView.drawWin((row > 0));
		dbase.close();

		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openStartingPoint = new Intent(act.getApplicationContext(),
							SchuifpuzzelActivity.class);
					act.startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}

}
