package com.krakelin.SpotifyWatcher;

import java.util.ArrayList;
import java.util.List;

import com.krakelin.SpotifyWatcher.R;

import android.app.Service;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SongAdapter extends ArrayAdapter<Song> {
	public SongAdapter(Context context, int resource, ArrayList<Song> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		Songs = objects;
		_Context=context;
	}

	Context _Context;
	ArrayList<Song> Songs;
	
	
	public int getCount() {
		// TODO Auto-generated method stub
		return Songs.size();
	}

	@Override
	public Song getItem(int arg0) {
		// TODO Auto-generated method stub
		return Songs.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return Songs.get(arg0).ID;
	}

	@Override
	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
			Song CT = Songs.get(arg0);
			if(CT.Name.startsWith("@+Separator//"))
			{
				TextView TV = new TextView(getContext(),null,android.R.attr.listSeparatorTextViewStyle);
				TV.setText(CT.Name.replace("@+Separator//",""));
				if(TV.getLayoutParams()!=null)
					TV.getLayoutParams().width=LayoutParams.FILL_PARENT; 
				return TV;  
			}
		 
			LayoutInflater D = (LayoutInflater)getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			View c = D.inflate(R.layout.songs, null);
			((TextView)c.findViewById(R.id.TextView01)).setText(Songs.get(arg0).Name);
			ProgressBar C = ((ProgressBar)c.findViewById(R.id.progressBar1));
			C.setMax(100);
			C.setProgress((int)Math.round(CT.popularity*100));
			((TextView)c.findViewById(R.id.TextView02)).setText(" ~"+String.valueOf(Songs.get(arg0).PlaysPerDay).replace(".0"," plays per day"));
		// TODO Auto-generated method stub
		return c;	
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return Songs.size()== 0;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled(int arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
