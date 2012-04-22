package com.researchanddevelopment.mainpackage;

import com.researchanddevelopment.mainpackage.Auto.Orientation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class AutoView extends View {
	private Auto auto;
	Paint p;
	private static int TILE_SIZE;

	public AutoView(Context context, Auto a, int tile_size) {
		super(context);
		// TODO Auto-generated constructor stub
		this.auto = a;
		this.p = new Paint();

		AutoView.TILE_SIZE = tile_size;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		p.setColor(auto.isGoalcar() ? Color.RED : Color.YELLOW);
		float xLeft = auto.getPos().x;
		float yTop = auto.getPos().y;
		int length = auto.getLength();

		if (auto.getOrientation() == Orientation.HORIZONTAAL)
			canvas.drawRect(5 + TILE_SIZE * xLeft, 5 + TILE_SIZE * yTop,
					TILE_SIZE * (xLeft + length), TILE_SIZE * (yTop + 1), p);
		else
			canvas.drawRect(5 + TILE_SIZE * xLeft, 5 + TILE_SIZE * yTop,
					TILE_SIZE * (xLeft + 1), TILE_SIZE * (yTop + length), p);
	}

}
