package com.researchanddevelopment.mainpackage;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * View van het bord
 * 
 * @author Paranoid Android Thom Wiggers - 4119444 Luuk Scholten - 4126424 Koen
 *         Basten - 4119657
 * 
 */
public class BoardView extends View {

	/**
	 * Views van de autos
	 */
	ArrayList<View> autoViews;

	/**
	 * gewonnen?
	 */
	private boolean win = false;

	/**
	 * Is een highscore?
	 */
	private boolean highScore = false;

	/**
	 * Zetten
	 */
	private int moves;

	/**
	 * Board paint
	 */
	private Paint boardp;

	/**
	 * Grid paint
	 */
	private Paint gridp;

	/**
	 * finish paint
	 */
	private Paint finishp;

	/**
	 * Text paint
	 */
	private Paint textp;

	public static final int TILE_SIZE = 78;

	/**
	 * Nieuwe view maken
	 * 
	 * @param context
	 */
	public BoardView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initPaint();

		this.moves = 0;

		autoViews = new ArrayList<View>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// Grid tekenen
		canvas.drawRect(0, 0, TILE_SIZE * Board.BOARD_SIZE + 5, TILE_SIZE * Board.BOARD_SIZE + 5,
				gridp);
		for (int i = 0; i < Board.BOARD_SIZE; ++i) {

			for (int j = 0; j < Board.BOARD_SIZE; ++j) {
				canvas.drawRect(5 + TILE_SIZE * i, 5 + TILE_SIZE * j, TILE_SIZE * (i + 1),
						TILE_SIZE * (j + 1), boardp);
			}
		}
		canvas.drawRect(5 + TILE_SIZE * (Board.BOARD_SIZE - 1), 5 + 2 * TILE_SIZE, TILE_SIZE
				* Board.BOARD_SIZE + 5, 3 * TILE_SIZE, finishp);

		for (View av : autoViews) {
			((AutoView) av).onDraw(canvas);
		}

		if (win) {
			canvas.drawText("You win in " + moves + " moves", 40, 7 * TILE_SIZE, textp);
			if (highScore) {
				canvas.drawText("You broke your previous highscore", 40, 8 * TILE_SIZE, textp);
				Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.not_bad);
				canvas.drawBitmap(bmp, 150, 8 * TILE_SIZE + 5, textp);
			}
		} else {

			canvas.drawText("Moves: " + moves, 40, 7 * TILE_SIZE, textp);
		}

		super.onDraw(canvas);
	}

	/**
	 * Autos toevoegen aan view
	 * 
	 * @param autos
	 */
	public void addAutos(ArrayList<Auto> autos) {
		for (Auto auto : autos) {
			AutoView av = new AutoView(this.getContext(), auto, TILE_SIZE);
			this.autoViews.add(av);
		}
		this.addTouchables(autoViews);

	}

	/**
	 * i moves erbij
	 * 
	 * @param i
	 */
	public void updateMoves(int i) {
		this.moves = i;
	}

	/**
	 * Win
	 * 
	 * @param b
	 */
	public void drawWin(boolean b) {
		highScore = b;
		win = true;
	}

	/**
	 * Paints initen
	 */
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
