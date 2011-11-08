package com.krakelin.SpotifyWatcher;

import java.net.URI;

import com.google.zxing.integration.android.IntentIntegrator;
import com.krakelin.SpotifyWatcher.ContentMatcher.ContentEventHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class SWActivity extends ListActivity implements ContentEventHandler {
	ProgressDialog pd;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		if (requestCode == IntentIntegrator.REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            String contents = intent.getStringExtra("SCAN_RESULT");
	            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            // Handle successful scan
	            pd = ProgressDialog.show(this, "", "Matching barcode with Spotify");
	            ContentMatcher cm = new ContentMatcher();
	            cm.setOnFinish(this);
	            cm.execute(contents);
	           
	        } else if (resultCode == RESULT_CANCELED) {
	            // Handle cancel
	        }
	    }
	}
	@Override
	public void onFinish(String uri) {
		// TODO Auto-generated method stub
		pd.cancel();
		if(uri != null){
			Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(uri));
			startActivity(i);
		}else{
			Toast t = Toast.makeText(this, "Inget innehåll matchadeas", 3000);
			t.show();
		}
		
	}

}
