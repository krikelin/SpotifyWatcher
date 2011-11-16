package com.krikelin.spotify.watcher;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.zxing.integration.android.IntentIntegrator;
import com.krikelin.spotify.watcher.R;

import android.app.Activity;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class main extends SWActivity {
	public static void assertMenuItems(Activity act){
		LinearLayout c = ((LinearLayout)act.findViewById(R.id.menu));
		
		for(int i=0; i < c.getChildCount(); i++){
			View v = c.getChildAt(i);
			if(v.getId() == act.getIntent().getIntExtra("btnID",-1) ){
				v.setBackgroundResource(R.drawable.spotify_menuitem_selected);
			}
		}
		((ImageButton)act.findViewById(R.id.mnuHome)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(v.getContext(),main.class);
				i.putExtra("btnID", R.id.mnuHome);
				i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				v.getContext().startActivity(i);
				
				
			}
		});
		((ImageButton)act.findViewById(R.id.mnuScan)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	
				// TODO Start scan activity
				try{
					IntentIntegrator.initiateScan((Activity)v.getContext(), "Please download barcode scanner ","","Yes", "No");
					
				}catch(Exception e){
					
				}
			}
		});
		((ImageButton)act.findViewById(R.id.mnuPlayLog)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	
				// TODO Start scan activity
				try{
					Intent i = new Intent(v.getContext(),StreamHistory.class);
					i.putExtra("btnID",R.id.mnuPlayLog);
					i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					v.getContext().startActivity(i);
				}catch(Exception e){
					
				}
			}
		});
		((ImageButton)act.findViewById(R.id.mnuSettings)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	
				// TODO Start scan activity
				try{
					Intent i = new Intent(v.getContext(),Preferences.class);
					i.putExtra("btnID",R.id.mnuSettings);
					i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					v.getContext().startActivity(i);
				}catch(Exception e){
					
				}
			}
		});
		
	}
	public class Maiden extends AsyncTask<String,String,  ArrayList<Song>>
	{
		public Context CurrentContext;
		@Override
		protected ArrayList<Song> doInBackground(String... params) {
		ContentLoader D = new ContentLoader(main.this);
		try {
			D.FetchContent();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*	
				ArrayList<Song> Songs = new ArrayList<Song>();
				Song D = new Song();
				D.Name="Test";
				D.popularity=(double) 0.3f;
				D.GenerateStats();
				Songs.add(D);
				return Songs;
			*/
			
			// TODO Auto-generated method stub
		return D.Songs;
		}
		@Override
		protected void onPostExecute(ArrayList<Song> Songs)
		{
			setContentView(R.layout.list_activity);
			main.Songs=Songs;
			LayoutInflater D = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
			getListView().setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Song d = (Song)main.Songs.get(arg2);
					SongActivity.currentSong=d;
					Intent SongIntent = new Intent(main.this,SongActivity.class);
					startActivity(SongIntent);
				}
			
			});
			if(Songs!=null)
				setListAdapter(new SongAdapter(main.this,R.layout.songs, Songs));
			((ImageButton)findViewById(R.id.mnuHome)).setBackgroundResource(R.drawable.spotify_menuitem_selected);
			main.assertMenuItems(main.this);
	        
		}
		
	}
	public static ArrayList<Song> Songs;
	
	
	public ProgressDialog ViewToast;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        
       
   /*     Song s = new Song();
        s.setLink("spotify:track:123415sfr2");
        s.setArtistLink("spotify:artist:a");
        s.setAlbumLink("spotify:album:a");
        s.Name="Test Song";
        s.popularity = 0.5;
      
		if(s.isChosen(Song.ALGORITM_MATH)){
			
			
			Stream stream = new Stream(s);
			stream.setCountry("Unknown");
			stream.setUser("user");
			
			stream.setUri(s.href);
			stream.setArtistLink(s.getArtistLink());
			stream.setAlbumlink(s.getAlbumLink());
			stream.setScanner("Visualization");
			stream.Store(this);
			// Add the song to the database
			
		}*/
		
        setContentView(R.layout.loading);
        ((ImageButton)findViewById(R.id.mnuHome)).setBackgroundResource(R.drawable.spotify_menuitem_selected);
        Intent i = new Intent(main.this,WatchService.class);
        startService(i);
        main.assertMenuItems(this);
        //  ViewToast = ProgressDialog.show(main.this,"","Hämtar data");
        Maiden D = new Maiden();
        D.CurrentContext=this;
        D.execute((String[])null);
    }
    
}