package com.krikelin.spotify.watcher;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlayLogActivity extends ListActivity {
	List<Stream> streams;
	public class StreamListAdapter extends ArrayAdapter<Stream>{
	
		@Override
		public Context getContext() {
			// TODO Auto-generated method stub
			return PlayLogActivity.this;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return streams.size();
		}

		@Override
		public Stream getItem(int position) {
			// TODO Auto-generated method stub
			return streams.get(position);
		}

		@Override
		public int getPosition(Stream item) {
			// TODO Auto-generated method stub
			return streams.indexOf(item);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Stream stream = streams.get(position);
			// TODO Auto-generated method stub
			if(convertView == null){
				LayoutInflater li = (LayoutInflater)getSystemService(Service.LAYOUT_INFLATER_SERVICE);
				convertView = li.inflate(R.layout.stream, null);
				
			}
			String trackName = stream.getSong().Name;
			if(trackName.length() > 15){
				trackName = trackName.substring(0,12)+"...";
			}
			((TextView)convertView.findViewById(R.id.tvDuration)).setText(String.format("%d : %d",Math.round(stream.getDuration() / 60), Math.round(stream.getDuration() / 60 + stream.getDuration() % 60)));
			((TextView)convertView.findViewById(R.id.tvTrackName)).setText(trackName);
			((TextView)convertView.findViewById(R.id.tvTime)).setText(stream.getTime().toLocaleString());
			return convertView;
		}

		public StreamListAdapter() {
			super(PlayLogActivity.this, 0);
			// TODO Auto-generated constructor stub
		} 
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);
		streams = (List<Stream>)SongActivity.currentSong.getStreams(11215);
		setListAdapter(new StreamListAdapter());
	}
	 
}
  