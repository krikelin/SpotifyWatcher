package com.krikelin.spotify.watcher;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.os.IBinder;
import android.preference.PreferenceManager;

public class WatchService extends Service {
	public static boolean taskRunning=false;
	public static void Watch(Context context){
		ArrayList<Song> songs = new ArrayList<Song>();
		if(main.Songs == null)
			return;
		int selection = (int)(Math.random()*main.Songs.size());
		int start = (int)(Math.random()*main.Songs.size());
		for(int i=start; i < start + 1; i++){
			Song s = main.Songs.get(i);
			Stream stream = s.isChosen(Song.ALGORITM_MATH);
			if(stream != null){
				
				songs.add(s);
				stream.Store(context);
				// Add the song to the database
				StreamHistory.new_streams_found=true; // Set new streams found
			}
		}
		SharedPreferences sp = (SharedPreferences)PreferenceManager.getDefaultSharedPreferences(context);
		if(sp.getBoolean("notify_stream", false)){
			if(songs.size() == 1){
				NotificationManager nm = (NotificationManager)context.getSystemService(Service.NOTIFICATION_SERVICE);
				PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context, main.class), 0);
				Notification notification = new Notification(R.drawable.play, "Someone has played the song "+songs.get(0).Name  , 0);
				notification.setLatestEventInfo(context, String.format("Someone is playing your song '%s' by 'none'", songs.get(0).Name) , "This is an predication", pi);
				nm.notify(11215, notification);
			}
			if(songs.size() > 1){
				NotificationManager nm = (NotificationManager)context.getSystemService(Service.NOTIFICATION_SERVICE);
				PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context, main.class), 0);
				Notification notification = new Notification(R.drawable.play, "Some of your songs have been streamed recently"  , 0);
				notification.setLatestEventInfo(context, String.format("Someone is playing your song '%s' by '%s'", songs.get((int)Math.random()*songs.size()).Name,"") , "This is an predication", pi);
				nm.notify(11215, notification);
				
			}
		}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public TimerTask task = new  TimerTask(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Watch(WatchService.this);
			
		}
		
	};
	Timer t;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		if(!taskRunning){
			t = new Timer();
			t.scheduleAtFixedRate(task, 0,60*1000);
			taskRunning = true;
		}
		super.onCreate();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		if(!taskRunning){
			t = new Timer();
			t.scheduleAtFixedRate(task, 0,60*1000);
			taskRunning = true;
		}	
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
	
		super.onStart(intent, startId);
	}
}
