package com.krikelin.spotify.watcher;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;

public class StreamHistory extends SWActivity {
	public static boolean new_streams_found=false;
	Timer c;
	TimerTask task = new TimerTask(){

		@Override
		public void run() {
			if(StreamHistory.new_streams_found){
				Looper.prepare();
				
				runOnUiThread(new Runnable(){

					@Override
					public void run() {
						Database d = new Database(StreamHistory.this);
						SQLiteDatabase db = d.getWritableDatabase();
						Cursor a = db.rawQuery("SELECT * FROM stream ORDER BY _id DESC", null);
						if(getIntent().hasExtra("track")){
							a = db.rawQuery("SELECT * FROM stream WHERE track_uri = '" + getIntent().getStringExtra("track")+"' ORDER BY _time DESC", null);
						}
						// TODO Auto-generated method stub
						setListAdapter(new StreamAdapter(StreamHistory.this, a));
						db.close();
						d.close();
					}
					
				});
				
				
				StreamHistory.new_streams_found= false;
				
			}
		}
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);
		c = new Timer();
		main.assertMenuItems(this);
		Database d = new Database(this);
		SQLiteDatabase db = d.getWritableDatabase();
		Cursor a = db.rawQuery("SELECT * FROM stream ORDER BY _id DESC", null);
		if(getIntent().hasExtra("track")){
			a = db.rawQuery("SELECT * FROM stream WHERE track_uri = '" + getIntent().getStringExtra("track")+"' ORDER BY _id DESC", null);
		}
		
		
		
		
		setListAdapter(new StreamAdapter(StreamHistory.this,a));
		db.close();
		c.schedule(task, 0, 10000);
	
	}

}
