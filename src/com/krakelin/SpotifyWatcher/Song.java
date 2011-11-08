package com.krakelin.SpotifyWatcher;

import java.util.ArrayList;
import java.util.List;

public class Song {
	public static final int ALGORITM_MATH = 1;
	public static final int ALGORITM_MOOD = 2;
	public static final int INTERVAL = 31;
	public static final int WEEKDAYS = 7;
	public static final float RATE = 0.006f; 
	
	public static int COUNT_SONGS =  11358958;
	public static Double TOTAL_PLAYS_PER_MONTH = (double) 92000000*10 ;
	public static int COUNT_USERS = 3 * 1000 * 1000;
	public Double Length = (double) ( 3.0f * 60.0f) ;
	public static Double GetNormalCount(){
		return Math.floor( (TOTAL_PLAYS_PER_MONTH*2) / COUNT_SONGS );
	}
	public Double OldPopularity = (double) 0.0f;
	public Double popularity=(double) 0.0f;
	public Double PlaysPerDay=(double) 0.0f;
	public Double TotalPlays= (double) 0.0f;
	public Double PlaysPerWeek= (double) 0.0f;
	public	 Double NeatRevenue= (double) 0.0f;
	
	/**
	 * Checked by an watch service to decide if someone is streaming your song right now
	 * 
	 * @return
	 */
	public boolean isChosen(int algoritm){
		
		// We calculate our analysis about several input parameters
		
		switch(algoritm){
		case Song.ALGORITM_MOOD:
			// Time of day is very important.
			Double predicationSeed = Math.random()*COUNT_USERS;
		
			return true;
			
		case Song.ALGORITM_MATH:
		
			Double seed = (popularity / 2) *  (COUNT_USERS );
			Double saturation = (popularity / 2 ) * COUNT_SONGS;
			
			Double x_mod_seed = ( 1- popularity / 1);// * (COUNT_USERS);
			Double y_mod_seed = (1- popularity / 1);// * (COUNT_SONGS);
			Double random_seed = Math.random()*seed;
			 
			Double logic_x = seed % x_mod_seed;
			Double logic_y = saturation % y_mod_seed;
			Double delta_x = (logic_x / random_seed);
			Double delta_y = (logic_y / random_seed);
			Double mod_x =seed % x_mod_seed;
			Double mod_y =  saturation % y_mod_seed;
			if(mod_x < Math.random()*0.5 ){
				return true;
			}
			else
			{
				mod_x = 0d;
			}
			/*
			Double x_speed = Math.random()*COUNT_USERS;
			Double y_speed = Math.random()*COUNT_SONGS;
			
			Double x_delta = seed % x_mod_seed;
			Double y_delta = saturation % y_mod_seed;
			
			Double x_mesh_delta = x_delta / y_delta;
			
			if(x_mesh_delta % x_mod_seed / y_mod_seed > .1){
				return true;
			}*/
			
			return false;
		}
		return false;
		
	}
	private double diff(Double x, Double y) {
		// TODO Auto-generated method stub
		return x > y ? x-y : y-x;
	}
	/****
	 * Generates an list of latest amount of streams
	 * by an special algoritm for reverse calculation of
	 * recent plays, by an approximation algoritm.
	 * 
	 * @param seed an special seed
	 */
	public List<Stream> getStreams(int seed){
		ArrayList<Stream> streams = new ArrayList<Stream>();
		
		// Generate an old popularity seed
		// If the populariy increase, it means an recent spreading of the song
		Double delta = OldPopularity / popularity;
		Double ohm = (Math.sin(popularity * Math.PI)/Math.cos(OldPopularity * Math.PI) * COUNT_USERS);
		
		// If the popularity increases fast, an rapid spreading is occuring
		// Create an approximate list of the lastes 50 streams
		for(int i=0; i <  Math.sin(popularity/3.14)*((TotalPlays / PlaysPerWeek) * PlaysPerDay) * 0.25 * ( COUNT_USERS / PlaysPerDay ) * 0.0005; i++){
			//
			
			// Get the Length of the play
			// By calculaing the approximate seed of the song
			Double Length = 5 + (((Math.tan(popularity/Math.PI)*TotalPlays * Math.sin(i / 3.14)) +1)  ) * this.Length + Math.cos(i / 3.14)*this.Length;
			if(Length < 0){
				Length = -Length * 0.5;
			}
			Length /= 4;
			if(Length > this.Length){
				Length = this.Length;
			}
			Stream s = new Stream(this); 
	
			s.setDuration(Length);
			
			s.setSong(this);
			streams.add(s);
		}
		// Then 
		return streams;
	}
	public String Name = "";
	public Integer ID = 0;
	public void GenerateStats()
	{
		Double differences = popularity / OldPopularity;
		TotalPlays =  this.popularity * (GetNormalCount() );
		
		// Decode amount of plays according to the popularity in the stats pulse
		PlaysPerDay = (Double) Math.floor(TotalPlays/INTERVAL);
		PlaysPerWeek = PlaysPerDay*WEEKDAYS;
		TotalPlays= PlaysPerWeek * 4;
		NeatRevenue = TotalPlays*RATE;
		
	}
}
