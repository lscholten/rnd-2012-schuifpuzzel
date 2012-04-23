package com.researchanddevelopment.mainpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database access
 * 
 * @author Paranoid Android
 *
 */
public class Database extends SQLiteOpenHelper {

	/**
	 * Tabel naam
	 */
	public static final String DATABASE_TABLE_NAME = "highscores";
	
	/**
	 * Database naam 
	 */
	private static final String DATABASE_NAME = "rushhourdb";
	
	/**
	 * Tabel create query
	 */
	private static final String DATABASE_TABLE_CREATE_QUERY = "CREATE TABLE "
			+ DATABASE_TABLE_NAME + "( gameid INTEGER, least_moves INTEGER);";
	
	/**
	 * Versie van de db
	 */
	private static final int DATABASE_VERSION = 1;

	/**
	 * Nieuwe db maken
	 * @param context
	 */
	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(DATABASE_TABLE_CREATE_QUERY);
		
		for(int i = 1; i <= 40; i++){
			ContentValues content = new ContentValues();
			content.put("gameid", i);
			
			arg0.insert(DATABASE_TABLE_NAME, null, content);
		}
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

}
