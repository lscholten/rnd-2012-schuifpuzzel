package com.researchanddevelopment.mainpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	public static final String DATABASE_TABLE_NAME = "highscores";
	private static final String DATABASE_NAME = "rushhourdb";
	
	private static final String DATABASE_TABLE_CREATE_QUERY = "CREATE TABLE "
			+ DATABASE_TABLE_NAME + "( gameid INTEGER, least_moves INTEGER);";
	private static final int DATABASE_VERSION = 1;

	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(DATABASE_TABLE_CREATE_QUERY);
		
		for(int i = 1; i <= 40; i++){
			ContentValues content = new ContentValues();
			content.put("gameid", i);
			
			arg0.insert(DATABASE_TABLE_NAME, null, content);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

}
