package com.krikelin.spotify.watcher;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class EntryAdapter implements ListAdapter{
	
	private Context context;
	private ArrayList<ListEntry> items;
	public EntryAdapter(Context context, ArrayList<ListEntry> items){
		this.items = items;
		this.context = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListEntry item = (ListEntry)getItem(position);
		if(convertView == null){
			LayoutInflater li = (LayoutInflater)context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			
			switch(item.getType()){
			case ListEntry.TYPE_ITEM:
				convertView = li.inflate(R.layout.list_entry, null);
				break;
			}
		}
		switch(item.getType()){
		case ListEntry.TYPE_ITEM:
			{
				((TextView)convertView.findViewById(R.id.tvTitle)).setText(item.getTitle());
			
				((TextView)convertView.findViewById(R.id.tvText)).setText(item.getDescription());
				ImageView iv = (ImageView)convertView.findViewById(R.id.ivIcon);
				if(item.getIcon() != null){
					iv.setImageDrawable(item.getIcon());
				}
			}
			break;
		}
		return convertView;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return items.get(position).getType();
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return items.get(position).isEnabled();
	}
	

}
