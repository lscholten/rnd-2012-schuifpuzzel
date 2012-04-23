package com.researchanddevelopment.mainpackage;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class BoardView extends View {
	private Paint p;

	ArrayList<View> autoViews;
	private boolean win = false;
	private int moves;
	private Paint boardp;
	private Paint gridp;
	private Paint finishp;
	private Paint textp;

	public static final int TILE_SIZE = 78;

	public BoardView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initPaint();

		this.moves = 0;

		autoViews = new ArrayList<View>();
	}

	@Override
	protected void onDraw(Canvas canvas) {

		// Grid tekenen
		canvas.drawRect(0, 0, TILE_SIZE * Board.BOARD_SIZE + 5, TILE_SIZE
				* Board.BOARD_SIZE + 5, gridp);
		for (int i = 0; i < Board.BOARD_SIZE; ++i) {
			for (int j = 0; j < Board.BOARD_SIZE; ++j) {
				canvas.drawRect(5 + TILE_SIZE * i, 5 + TILE_SIZE * j, TILE_SIZE
						* (i + 1), TILE_SIZE * (j + 1), boardp);
			}
		}
		canvas.drawRect(5 + TILE_SIZE * (Board.BOARD_SIZE - 1),
				5 + 2 * TILE_SIZE, TILE_SIZE * Board.BOARD_SIZE + 5,
				3 * TILE_SIZE, finishp);

		for (View av : autoViews) {
			((AutoView) av).onDraw(canvas);
		}

		if (win) {
			Log.d("nigga", "g");
			canvas.drawText("You win in " + moves + " moves :C", 40,
					7 * TILE_SIZE, textp);
		} else {

			canvas.drawText("Moves: " + moves, 40, 7 * TILE_SIZE, textp);
		}

		super.onDraw(canvas);
	}

	public void addAutos(ArrayList<Auto> autos) {
		for (Auto auto : autos) {
			AutoView av = new AutoView(this.getContext(), auto, TILE_SIZE);
			this.autoViews.add(av);
		}
		this.addTouchables(autoViews);

	}

	public void updateMoves(int i) {
		this.moves = i;
	}

	public void drawWin() {
		win = true;
	}

	private void initPaint() {
		boardp = new Paint();

		boardp.setColor(Color.BLACK);
		boardp.setStrokeWidth(3);

		gridp = new Paint();

		gridp.setColor(Color.BLUE);
		gridp.setStrokeWidth(3);

		finishp = new Paint();
		finishp.setStrokeWidth(3);
		finishp.setColor(Color.DKGRAY);

		textp = new Paint();
		textp.setTextSize(20);
		textp.setColor(Color.GREEN); 

	}

}
