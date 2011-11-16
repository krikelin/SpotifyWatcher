package com.krikelin.spotify.watcher;

import android.app.Service;
import android.content.Context;
import android.database.Cursor;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class StreamAdapter extends CursorAdapter {

	public StreamAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		Stream stream = new Stream(cursor);
		String trackName = stream.getSong().Name;
		if(trackName.length() > 20){
			trackName = trackName.substring(0,20)+"...";
		}
		((TextView)view.findViewById(R.id.tvDuration)).setText(String.format("%d : %d",Math.round(stream.getDuration() / 60), Math.round(stream.getDuration() / 60 + stream.getDuration() % 60)));
		((TextView)view.findViewById(R.id.tvTrackName)).setText(trackName);
		((TextView)view.findViewById(R.id.tvTime)).setText(DateFormat.format("yyyy-MM-dd",stream.getTime()));
	
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater)context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		View stream = li.inflate(R.layout.stream, null);
		
		bindView(stream, context, cursor);
		return stream;
	}

}
