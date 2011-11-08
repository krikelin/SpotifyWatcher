package com.krakelin.SpotifyWatcher;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.krakelin.SpotifyWatcher.R;

import android.app.Activity;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class main extends Activity {
	
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
			main.Songs=Songs;
			LayoutInflater D = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
			ListView Lst = (ListView)main.this.findViewById(R.id.ListView01);
			Lst.setOnItemClickListener(new OnItemClickListener(){

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
				Lst.setAdapter(new SongAdapter(main.this,R.layout.songs, Songs));
			ViewToast.cancel();
		}
		
	}
	public static ArrayList<Song> Songs;
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.menu, menu);
		    return true;
		    
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.item01:
	    
		    	Intent C = new Intent(main.this,Preferences.class);
		    	startActivity(C);
	    	
	    	
	    	return true;
    	case R.id.scan:
    		WatchService.Watch(main.this);
    		
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	
	}
	public ProgressDialog ViewToast;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewToast = ProgressDialog.show(main.this,"","Hämtar data");
        Maiden D = new Maiden();
        D.CurrentContext=this;
        D.execute((String[])null);
    }
    
}