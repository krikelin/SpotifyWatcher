package com.krikelin.spotify.watcher;

import java.util.Date;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/***
 * An stream is an stream of an song
 * @author Alexander
 *
 */
public class Stream {
	public static String TIME = "time";
	public Stream(){
		
	}
	private Date time;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Stream(Cursor c){
		this.id = c.getLong(c.getColumnIndex("_id"));
		this.song = new Song();
		try{
			this.time = new Date(c.getLong(c.getColumnIndex(Stream.TIME)));
		}
		catch(Exception e){
			this.time = new Date(2009,01,18);
		}
		this.duration = (Double)c.getDouble(c.getColumnIndex(Stream.DURATION));
		this.song.Name = c.getString(c.getColumnIndex(Stream.TITLE));
		this.song.popularity = c.getDouble(c.getColumnIndex(Stream.POPULARITY));
		this.song.href = c.getString(c.getColumnIndex(Stream.SONG_URI));
		this.artistlink = c.getString(c.getColumnIndex(Stream.SONG_ARTIST_URI));
		this.albumlink = c.getString(c.getColumnIndex(Stream.SONG_ALBUM_URI));
		this.uri = c.getString(c.getColumnIndex(Stream.SONG_URI));
		this.user = c.getString(c.getColumnIndex(Stream.USER));
	}
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Save the stream into the public database
	 * @param context
	 */
	public void Store(Context context){
		ContentValues cv = new ContentValues();
		cv.put(Stream.ID, Math.random()*11215);
		cv.put(Stream.DURATION, getDuration());
		cv.put(Stream.SONG_URI, getSong().href);
		cv.put(Stream.SONG_ARTIST_URI, getSong().getArtistLink());
		cv.put(Stream.SONG_ALBUM_URI, getSong().getAlbumLink());
		cv.put(Stream.COUNTRY, getCountry());
		cv.put(Stream.SCANNER,this.scanner);
		cv.put(Stream.POPULARITY, this.getSong().popularity);
		cv.put(Stream.ARTIST,"Unknown");
		cv.put(Stream.ALBUM, "Unknown");
		cv.put(Stream.TITLE, this.getSong().Name);
		cv.put(Stream.TIME,this.getTime().getTime());
		
		this.id = ContentUris.parseId(context.getContentResolver().insert(Uri.withAppendedPath(CONTENT_URI, "stream"), cv));
		if(this.id == -1)
		{
			throw new IllegalArgumentException();
		}
	}
	private String artistlink;
	private String albumlink;
	private String scanner;
	public String getScanner() {
		return scanner;
	}
	public void setScanner(String scanner) {
		this.scanner = scanner;
	}
	public String getArtistLink() {
		return artistlink;
	}
	public void setArtistLink(String artistlink) {
		this.artistlink = artistlink;
	}
	public String getAlbumLink() {
		return albumlink;
	}
	public void setAlbumlink(String albumlink) {
		this.albumlink = albumlink;
	}
	public static final String ID = "_id";
	public static final String PROVIDER = "com.krikelin.spotify.watcher.provider";
	public static final String SONG = "song";
	public static final String DURATION = "duration";
	public static final String COUNTRY = "country";
	public static final String USER = "user";
	public static final String SONG_URI = "track_uri";
	public static final String URI = "uri";
	public static final Uri CONTENT_URI = Uri.parse("content://"+PROVIDER);

	public static final String SONG_ARTIST_URI = "artist_uri";
	public static final String SONG_ALBUM_URI = "album_uri";
	public static final String ARTIST = "artist";
	public static final String ALBUM = "album";
	public static final String TITLE = "title";
	public static final String POPULARITY = "popularity";
	public static final String SCANNER = "scanner";
	public Stream(Song song2) {
		// TODO Auto-generated constructor stub
		this.song=song2;
	}
	private String uri;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration2) {
		this.duration = duration2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	private Song song;
	private Double duration;
	private String country;
	private String user;
	
	
}
