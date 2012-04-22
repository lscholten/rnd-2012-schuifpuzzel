package com.researchanddevelopment.mainpackage;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class BoardView extends View implements OnTouchListener {
	private Paint p;
	private ImageView car;

	ArrayList<View> autoViews;

	public static final int TILE_SIZE = 78;

	public BoardView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		p = new Paint();

		autoViews = new ArrayList<View>();
		setOnTouchListener(this);

		car = new ImageView(this.getContext());
		car.setImageResource(R.drawable.car);
		car.setDrawingCacheEnabled(true);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		p.setColor(Color.BLUE);
		p.setStrokeWidth(3);

		// Grid tekenen
		canvas.drawRect(0, 0, TILE_SIZE * Board.BOARD_SIZE + 5, TILE_SIZE * Board.BOARD_SIZE + 5, p);
		p.setColor(Color.BLACK);
		for (int i = 0; i < Board.BOARD_SIZE; ++i) {
			for (int j = 0; j < Board.BOARD_SIZE; ++j) {
				canvas.drawRect(5 + TILE_SIZE * i, 5 + TILE_SIZE * j, TILE_SIZE * (i + 1),
						TILE_SIZE * (j + 1), p);
			}
		}

		/*
		 * for(View av : autoViews) { ((AutoView)av).onDraw(canvas); }
		 */

		car.draw(canvas);

		super.onDraw(canvas);
	}

	public void addAutos(ArrayList<Auto> autos) {
		for (Auto auto : autos) {
			AutoView av = new AutoView(this.getContext(), auto, TILE_SIZE);
			this.autoViews.add(av);
		}
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d("Touch", "Touched the board maddafakka");
		return false;
	}

}
