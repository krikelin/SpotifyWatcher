package com.krakelin.SpotifyWatcher;

import java.util.ArrayList;

import com.krakelin.SpotifyWatcher.Property.PropertyClickedHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TableLayout.LayoutParams;

public class SongActivity extends ListActivity {
	public static Song currentSong;
	
	public ArrayList<Property> properties = new ArrayList<Property>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);
		properties.add(new Property("@+separator://Basic Info",""));
		properties.add(new Property("Title",currentSong.Name+" "));
		Property DT = new Property("Recent Plays",currentSong.Name+" ");
		
		properties.add(DT);
		DT.OnClick = DT.new PropertyClickedHandler()
		{
			@Override
			protected void onClicked(Object sender)
			{
				Intent i = new Intent(SongActivity.this,PlayLogActivity.class);
				startActivity(i);
			}
		};
		properties.add(new Property("@+separator://Popularity",""));
		properties.add(new Property("General",String.valueOf(Math.round(currentSong.popularity*100)+"%")));
		properties.add(new Property("Plays per day",String.valueOf(Math.round(currentSong.PlaysPerDay)+"")));
		properties.add(new Property("Plays per week",String.valueOf(Math.round(currentSong.PlaysPerWeek)+"")));
		properties.add(new Property("@+separator://Total Plays",""));
		getListView().setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Property C = properties.get(arg2);
				if(C.OnClick != null)
				{
					C.OnClick.onClicked(C);
				}
				
			}
			
		});
		PropertyAdapter CT = new PropertyAdapter(this,0);
		CT.properties = properties;
		setListAdapter(CT);
		
	}
	
}
