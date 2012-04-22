package com.researchanddevelopment.mainpackage;

import java.util.ArrayList;

import com.researchanddevelopment.mainpackage.Auto.Orientation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GameView extends View {
	private Paint p;
	ArrayList<AutoView> autoViews;
	
	Board bord;
	
	private static final int TILE_SIZE = 78; 
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		p = new Paint();
		bord  = new Board(context.getResources().getXml(R.xml.game));	
		autoViews = new ArrayList<AutoView>();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		p.setColor(Color.BLUE);
		p.setStrokeWidth(3);
		
		//Grid tekenen
		canvas.drawRect(0,0, TILE_SIZE*Board.BOARD_SIZE+5,TILE_SIZE*Board.BOARD_SIZE+5, p);		
		p.setColor(Color.BLACK);
		for(int i = 0; i < Board.BOARD_SIZE; ++i) {
			for(int j = 0; j < Board.BOARD_SIZE; ++j) {
				canvas.drawRect(5+TILE_SIZE*i, 5+TILE_SIZE*j, TILE_SIZE*(i+1) , TILE_SIZE*(j+1), p);
			}
		}
		
		for(Auto auto : bord.getAutos()) {
			autoViews.add(new AutoView(this.getContext(), auto, TILE_SIZE));
			autoViews.get(autoViews.size() - 1).onDraw(canvas);
		}
		
		super.onDraw(canvas);
	}
}
