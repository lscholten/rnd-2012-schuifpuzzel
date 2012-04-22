package com.researchanddevelopment.mainpackage;

import com.researchanddevelopment.mainpackage.Auto.Orientation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
	private Paint p;
	Auto auto;
	Auto auto2;
	
	Board bord;
	
	private static final int TILE_SIZE = 65;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		p = new Paint();
		auto = new Auto(new Point(0,0), 3, Auto.Orientation.HORIZONTAAL);
		auto2 = new Auto(new Point(4,4), 2, Auto.Orientation.VERTICAAL);
		bord  = new Board(context.getResources().getXml(R.xml.game));	
		
		
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
		
		for(Auto auto : bord.getAutos()) {
			canvas = drawCar(auto, canvas);
		}
		
		super.onDraw(canvas);
	}
	
	private Canvas drawCar(Auto a, Canvas c){
		p.setColor(Color.RED);
		int xLeft = a.getPos().x;
		int yTop = a.getPos().y;
		int length = a.getLength();
		
		if(a.getOrientation() == Orientation.HORIZONTAAL )
			c.drawRect(5 + TILE_SIZE * xLeft, 5 + TILE_SIZE * yTop , TILE_SIZE * (xLeft + length), TILE_SIZE * (yTop + 1), p);
		else
			c.drawRect(5 + TILE_SIZE * xLeft, 5 + TILE_SIZE * yTop, TILE_SIZE * (xLeft + 1), TILE_SIZE * (yTop + length), p);
		
		return c;
	}
	
	

}
