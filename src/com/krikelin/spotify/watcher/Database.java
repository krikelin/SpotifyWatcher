package com.krikelin.spotify.watcher;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	public static final int DB_VERSION = 2;
	public static final String DB_NAME = "";
	public Database(Context context) {
		super(context, "database", null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE 'stream' ('_id' integer PRIMARY KEY  AUTOINCREMENT  NOT NULL ,'time' DATETIME, 'user' text, 'popularity' double, 'track_uri' text, 'uri' text, 'scanner' text,'artist_uri' text,'album_uri' text, 'artist' text,'album' text, 'title' text, 'country' text, 'track_duration' integer, 'duration' integer)");
	//	db.execSQL("CREATE TABLE 'track' ('_ID' integer PRIMARY KEY AUTOINCREMENT NOT NULL, 'title' text, 'uri' VARCHAR(30), 'artist_id' integer, 'album_id' integer, 'duration' integer)");
		//db.execSQL("CREATE TABLE 'artist' ('_ID' integer PRIMARY KEY AUTOINCREMENT NOT NULL, 'title' text, 'uri' VARCHAR(30))");
		//db.execSQL("CREATE TABLE 'album' ('_ID' integer PRIMARY KEY AUTOINCREMENT NOT NULL, 'title' text, 'primary_artist' integer, 'uri' VARCHAR(30))");
	//	db.execSQL("CREATE TABLE 'scanner' ('_ID' integer PRIMARY KEY AUTOINCREMENT NOT NULL, 'title' text, 'primary_artist' integer, 'uri' VARCHAR(30))");
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	

	

}
