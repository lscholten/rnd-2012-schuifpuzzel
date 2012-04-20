package com.researchanddevelopment.mainpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
	private Paint p;
	
	private static final int TILE_SIZE = 65;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		p = new Paint();
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		p.setColor(Color.BLUE);
		p.setStrokeWidth(3);
		
		//Grid tekenen
		canvas.drawRect(0,0, TILE_SIZE*Board.BOARD_SIZE+5,TILE_SIZE*Board.BOARD_SIZE+5, p);		
		p.setColor(Color.BLACK);
		for(int i = 0; i < 7; ++i) {
			for(int j = 0; j < Board.BOARD_SIZE; ++j) {
				canvas.drawRect(5+TILE_SIZE*i, 5+TILE_SIZE*j, TILE_SIZE*(i+1) , TILE_SIZE*(j+1), p);
			}
		}
		
		super.onDraw(canvas);
	}
	
	

}
