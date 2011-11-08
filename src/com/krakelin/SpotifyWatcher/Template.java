package com.krakelin.SpotifyWatcher;
	



import java.lang.reflect.Field;
import java.util.Date;

import android.app.Activity;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Template {
	public static void InflateView(Activity act){
		// TODO: Fix this function
	}
	public static void BindView(String Name,Object Source,ViewGroup target){
		
			for(int i=0; i < target.getChildCount();i++){
				View Child = target.getChildAt(i);
				if(Child instanceof TextView){
					for(Field _Field : Source.getClass().getDeclaredFields()){
						try {
							TextView Text = (TextView)Child;
							if(!Text.getText().toString().contains("${"+Name+"."+_Field.getName()+"}"))
							{
								continue;
							}
							if(_Field.get(Source) instanceof String){
							
								
					
								String d = (String)Source.getClass().getField(_Field.getName()).get(Source);
								Text.setText(Text.getText().toString().replace("${"+Name+"."+_Field.getName()+"}",d));
									 
								 
							}
						
							if(_Field.get(Source) instanceof Integer)
							{
								Log.e("Integer","To Integer");
									Text = (TextView)Child;
									String d = (String.valueOf((Integer)_Field.get(Source)));
									Text.setText(Text.getText().toString().replace("${"+Name+"."+_Field.getName()+"}",d));
								Log.e("Integer","To Integer complete");
								
							}
							if(_Field.get(Source) instanceof Date)
							{
								Log.e("Integer","To Integer");
								 Text = (TextView)Child;
									String d = ((Date)_Field.get(Source)).toString();
									Text.setText(Text.getText().toString().replace("${"+Name+"."+_Field.getName()+"}",d));
								Log.e("Integer","To Integer complete");
								
							}
							if(_Field.get(Source) instanceof Float)
							{
								Log.e("Integer","To Integer");
									Text = (TextView)Child;
									String d = ((Float)_Field.get(Source)).toString();
									Text.setText(Text.getText().toString().replace("${"+Name+"."+_Field.getName()+"}",d));
								Log.e("Integer","To Integer complete");
								
							}
						
							
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchFieldException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(Child instanceof ViewGroup && Child!=target)
					BindView(Name,Source,(ViewGroup)Child);
			}
		
	}
}
