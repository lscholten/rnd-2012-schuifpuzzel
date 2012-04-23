package com.researchanddevelopment.mainpackage;


import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class BoardView extends View {
	private Paint p;

	ArrayList<View> autoViews;
	
	public static final int TILE_SIZE = 78; 
	
	public BoardView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		p = new Paint();
		
		autoViews = new ArrayList<View>();
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
		p.setColor(Color.DKGRAY);
		canvas.drawRect(5+TILE_SIZE*(Board.BOARD_SIZE-1),5+2*TILE_SIZE,TILE_SIZE*Board.BOARD_SIZE+5, 3*TILE_SIZE, p);
		

		for(View av : autoViews) {
			((AutoView)av).onDraw(canvas);
		}
		
		super.onDraw(canvas);
	}

	public void addAutos(ArrayList<Auto> autos) {
		for(Auto auto : autos){
			AutoView av = new AutoView(this.getContext(), auto, TILE_SIZE);
			this.autoViews.add(av);
		}
		this.addTouchables(autoViews);
		
	}

}
