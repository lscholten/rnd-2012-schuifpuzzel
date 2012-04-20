package com.researchanddevelopment.mainpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
	private Paint p;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		p = new Paint();
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		p.setColor(Color.BLUE);
		p.setStrokeWidth(3);
		canvas.drawRect(30, 30, 80, 80, p);
		super.onDraw(canvas);
	}
	
	

}
