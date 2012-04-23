package com.researchanddevelopment.mainpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.researchanddevelopment.mainpackage.Auto.Orientation;

/**
 * View voor alle autos
 * 
 * @author Paranoid Android Thom Wiggers - 4119444 Luuk Scholten - 4126424 Koen
 *         Basten - 4119657
 * 
 */
public class AutoView extends View {
	/**
	 * Auto
	 */
	private Auto auto;
	/**
	 * Paint
	 */
	Paint p;
	/**
	 * Tegelgrootte
	 */
	private int TILE_SIZE;

	/**
	 * @param context
	 * @param a
	 * @param tile_size
	 */
	public AutoView(Context context, Auto a, int tile_size) {
		super(context);
		// TODO Auto-generated constructor stub
		this.auto = a;
		this.p = new Paint();

		TILE_SIZE = tile_size;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		p.setColor(auto.isGoalcar() ? Color.RED : Color.YELLOW);
		float xLeft = auto.getPos().x;
		float yTop = auto.getPos().y;
		int length = auto.getLength();

		if (auto.getOrientation() == Orientation.HORIZONTAAL)
			canvas.drawRect(5 + TILE_SIZE * xLeft, 5 + TILE_SIZE * yTop, TILE_SIZE
					* (xLeft + length), TILE_SIZE * (yTop + 1), p);
		else
			canvas.drawRect(5 + TILE_SIZE * xLeft, 5 + TILE_SIZE * yTop, TILE_SIZE * (xLeft + 1),
					TILE_SIZE * (yTop + length), p);
	}

}
