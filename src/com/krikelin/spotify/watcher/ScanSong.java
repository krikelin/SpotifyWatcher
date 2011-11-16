package com.krikelin.spotify.watcher;

import com.google.zxing.integration.android.IntentIntegrator;

import android.content.Intent;
import android.os.Bundle;

public class ScanSong extends SWActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		IntentIntegrator.initiateScan(this);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		
	}

}
