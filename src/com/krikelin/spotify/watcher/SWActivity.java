package com.krikelin.spotify.watcher;

import java.net.URI;

import com.google.zxing.integration.android.IntentIntegrator;
import com.krikelin.spotify.watcher.ContentMatcher.ContentEventHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	public boolean onCreateOptionsMenu(Menu menu)
	{
		
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.menu, menu);
		    return true;
		    
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.item01:
	    
		    	Intent C = new Intent(SWActivity.this,Preferences.class);
		    	C.putExtra("btnID",R.id.mnuSettings);
		    	startActivity(C);
	    	
	    	
	    	return true;
	   
    	case R.id.scan:
    		WatchService.Watch(SWActivity.this);
    		
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
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
			Toast t = Toast.makeText(this, "Inget innehåll matchades", 3000);
			t.show();
		}
		
	}

}
