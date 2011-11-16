package com.krikelin.spotify.watcher;


import com.krikelin.spotify.watcher.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;
 

public class Preferences extends PreferenceActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.prefs);
	    addPreferencesFromResource(R.xml.prefs);
	    main.assertMenuItems(this);
	   /* LayoutInflater CX = getLayoutInflater();
	    CX.inflate(R.layout.main,null);*/
	    // TODO Auto-generated method stub
	}

}
