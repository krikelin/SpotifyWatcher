package com.krakelin.SpotifyWatcher;


import com.krakelin.SpotifyWatcher.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;
 

public class Preferences extends PreferenceActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    addPreferencesFromResource(R.xml.prefs);
	   /* LayoutInflater CX = getLayoutInflater();
	    CX.inflate(R.layout.main,null);*/
	    // TODO Auto-generated method stub
	}

}
