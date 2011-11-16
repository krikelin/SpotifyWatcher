package com.krikelin.spotify.watcher;

import java.util.Timer;
import java.util.TimerTask;



import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;

public class Audioscape extends Service{
	public class GPSTask extends AsyncTask<Location,Location,Location>
	{
		LocationManager Ms   = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		LocationListener locationListener = new LocationListener() {
			
		    public void onLocationChanged(Location location) {
		      // Called when a new location is found by the network location provider.
		      makeUseOfNewLocation(location);
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		    public Location currentLocation;
		    public void makeUseOfNewLocation(Location location)
		    {
		    	currentLocation=location;
		    }
		};
		@Override
		protected Location doInBackground(Location... params) {
			Location R = Ms.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			
			// TODO Auto-generated method stub
			return R;
		}
		public String result;
		@Override
		protected void onPostExecute(Location result)
		{
			// Create song by location
			
			//String GenreList = 
			
			//result.getAltitude()
		
		}
		
	}

	public Timer d;
	public TimerTask CT = new TimerTask(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	};
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
