package com.krakelin.SpotifyWatcher;
/***
 * An stream is an stream of an song
 * @author Alexander
 *
 */
public class Stream {
	
	public Stream(Song song2) {
		// TODO Auto-generated constructor stub
		this.song=song2;
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
