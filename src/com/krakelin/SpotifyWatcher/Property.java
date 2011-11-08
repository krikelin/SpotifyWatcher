package com.krakelin.SpotifyWatcher;

public class Property
{
	public class PropertyClickedHandler {
	
		protected void onClicked(Object sender) {
			// TODO Auto-generated method stub
			
		}
	}
	public String name;
	public String value;
	public PropertyClickedHandler OnClick;
	public Property(String Name,String Value)
	{
		name=Name;
		value=Value;
	}
}
