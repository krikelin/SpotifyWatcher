package com.krakelin.SpotifyWatcher;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.krakelin.SpotifyWatcher.Property;

public class PropertyAdapter extends ArrayAdapter<Property>
{
	public ArrayList<Property> properties;
	public PropertyAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(Property object) {
		// TODO Auto-generated method stub
		properties.add(object);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return properties.size();
	}

	@Override
	public Property getItem(int position) {
		// TODO Auto-generated method stub
		return properties.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater LI = (LayoutInflater)getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		Property d = properties.get(position);
		// Make this an separator if the name is an separator
		if(d.name.startsWith("@+separator://"))
		{
			View C = LI.inflate(R.layout.separator,null);
			TextView TV = (TextView)C.findViewById(R.id.textView1);
			TV.setText(d.name.replace("@+separator://",""));
			return C;
		}
		View Properties = LI.inflate(R.layout.property,null);
		TextView Prop = (TextView)Properties.findViewById(R.id.TextView01);
		TextView Val = (TextView)Properties.findViewById(R.id.TextView02);
		ImageView IV = (ImageView)Properties.findViewById(R.id.imageView2);
		if(d.OnClick == null)
			IV.setVisibility(ImageView.GONE);
		else
			IV.setVisibility(ImageView.VISIBLE);
		try{
		Prop.setText(d.name);
		Val.setText(d.value);
		}catch(Exception e){
		
		}
		return Properties;
	}

	@Override
	public void insert(Property object, int index) {
		// TODO Auto-generated method stub
		super.insert(object, index);
	}

	@Override
	public void remove(Property object) {
		// TODO Auto-generated method stub
		super.remove(object);
	}

	@Override
	public void setDropDownViewResource(int resource) {
		// TODO Auto-generated method stub
		super.setDropDownViewResource(resource);
	}
	
}
