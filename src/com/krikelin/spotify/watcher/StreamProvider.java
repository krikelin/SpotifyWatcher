package com.krikelin.spotify.watcher;


import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class StreamProvider extends ContentProvider {
	public StreamProvider(){
		this.context=getContext();
	}
	public static final int STREAM_ID = 3;
	public static final int STREAMS = 2;
	public static final UriMatcher URI_MATCHER;
	public static final int SEARCH = 4;
	public static final String AUTHORITY = Stream.PROVIDER;
	
	static{
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY, SEARCH);
        URI_MATCHER.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY + "/*", SEARCH);
        URI_MATCHER.addURI(AUTHORITY, "stream", STREAMS);
        URI_MATCHER.addURI(AUTHORITY, "stream/#", STREAM_ID);
	}
	private Context context;
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		switch(URI_MATCHER.match(uri)){
		case STREAM_ID:
			
			int id = (int)ContentUris.parseId(uri);
			Database d = new Database(context);
			SQLiteDatabase db = d.getWritableDatabase();
			db.delete("stream",  "_id = " + String.valueOf(id), null);
			db.close();
			d.close();
			return id;
		}
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch(URI_MATCHER.match(uri)){
		case STREAM_ID:
		
			return "vnd.android.cursor.item/vnd.krikelin.SpotifyWatcher.providers.stream";
		
		case STREAMS:
			return "vnd.android.cursor.dir/vnd.krikelin.SpotifyWatcher.providers.stream";
			default:
				throw new IllegalArgumentException();
		}
	}
	/***
	 * Adds an object and returns its id
	 * @param type
	 * @param name
	 * @param root
	 * @return
	 */
	public long addObject(SQLiteDatabase db,String type, String name,String root){
		
		Cursor y = db.rawQuery("SELECT _ID FROM "+type+" WHERE ["+root+"] = '"+name+"'",null);
		if(y.moveToFirst()){
			return y.getInt(0);
			
			
		}
		else{
			ContentValues cv = new ContentValues();
			cv.put(root, name);
			return	db.insert(type,"",cv);
		}
	}
	public long addObject(SQLiteDatabase db,String type, String name){
		return addObject(db, type, name, "title");
		
	}
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		Log.e("A",uri.toString()); 
		int match = URI_MATCHER.match(uri);
		if(match != STREAMS ){
			throw new IllegalArgumentException();
		}
		
		// Open database
		Database d = new Database(getContext());
		SQLiteDatabase db = d.getWritableDatabase();
	
		String sp_uri = values.getAsString(Stream.SONG_URI);
		String sp_artist = values.getAsString(Stream.SONG_ARTIST_URI);
		String sp_album = values.getAsString(Stream.SONG_ALBUM_URI);
		String title = values.getAsString(Stream.TITLE);
		String album = values.getAsString(Stream.ALBUM);
		String artist = values.getAsString(Stream.ARTIST);
		Double popularity = values.getAsDouble(Stream.POPULARITY);
		String scanner = values.getAsString(Stream.SCANNER);
		String user = values.getAsString(Stream.USER);
		Integer stream_duration = values.getAsInteger(Stream.DURATION);
		String stream_country = values.getAsString(Stream.COUNTRY);
		Long timestamp = values.getAsLong(Stream.TIME);
		int id = (int)Math.random()*20000;
		ContentValues insertValues = new ContentValues();
		//insertValues.put("_ID",id);
		insertValues.put("duration", stream_duration);
		insertValues.put("artist_uri", sp_artist);
		insertValues.put("album_uri", sp_album);
		insertValues.put("track_uri", sp_uri);
		insertValues.put("artist", artist);
		insertValues.put("time",timestamp);
		insertValues.put("album", album);
		insertValues.put("user", user);
		insertValues.put("scanner", scanner);
		insertValues.put("popularity", popularity);
		insertValues.put("title", title);
		insertValues.put("uri","spotify:stream:"+String.valueOf(id));
		//	insertValues.put("artist_id",(int)addObject(db,"artist", sp_artist));
		//	insertValues.put("album_id",(int)addObject(db,"album", sp_album));
		//	insertValues.put("track_id",(int)addObject(db,"track", sp_uri));
		
		insertValues.put("country", stream_country);
		long row_id = db.insertOrThrow("stream","",insertValues); 
		
		db.close();
		d.close();
		// Return the uri to the new content
		return ContentUris.withAppendedId(uri, row_id);
	
	
	
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		
		SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
		
		Cursor cursor = null;
		switch(URI_MATCHER.match(uri)){
		
		case STREAM_ID:
			sqb.setTables("stream");
			sqb.appendWhere("_id="+uri.getPathSegments().get(1));
			break;
		default:
			throw new IllegalArgumentException();
			
		}
		Database d = new Database(context);
		SQLiteDatabase db = d.getWritableDatabase();
		return sqb.query(db, selectionArgs, selection, selectionArgs, null, null, sortOrder);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		switch(URI_MATCHER.match(uri)){
		case STREAM_ID:
			break;
			default:
				throw new IllegalArgumentException();
		}
		return 0;
	}

}
