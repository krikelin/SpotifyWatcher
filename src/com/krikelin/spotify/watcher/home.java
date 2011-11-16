package com.krikelin.spotify.watcher;

import java.util.ArrayList;

import com.google.zxing.integration.android.IntentIntegrator;
import com.krikelin.spotify.watcher.ListEntry.OnClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class home extends SWActivity {
	private ArrayList<ListEntry> items = new ArrayList<ListEntry>();
	protected void createHomeMenu(){
		items = new ArrayList<ListEntry>();
		ListEntry lstHome = new ListEntry();
		lstHome.setTitle("Concerts");
		lstHome.setDescription("");
		lstHome.setOnClickHandler(new OnClickListener() {
			
			@Override
			public void onClick(ListEntry sender) {
				// TODO Auto-generated method stub
				
			}
		});
		items.add(lstHome);
		
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				ListEntry selectedItem = (ListEntry)arg0.getItemAtPosition(arg2);
				
				// Invoke the onClick handler
				
				if(selectedItem.getOnClickHandler()!= null){
					selectedItem.getOnClickHandler().onClick(selectedItem);
				}
			}
		});
		items.add(new ListEntry("Soundsphere", "What music is present around you?", new OnClickListener() {
			
			@Override
			public void onClick(ListEntry sender) {
				// TODO Auto-generated method stub
				
			}
		}));
		items.add(new ListEntry("Watcher", "Get an hint of your music evolution on Spotify", new OnClickListener() {
			
			@Override
			public void onClick(ListEntry sender) {
				// TODO Auto-generated method stub
				
			}
		}));
		items.add(new ListEntry("Scan music", "Revive your old CDs by scan their barcode ", new OnClickListener() {
			
			@Override
			public void onClick(ListEntry sender) {
				// TODO Auto-generated method stub
				try{
					IntentIntegrator.initiateScan((Activity)home.this, "Please download barcode scanner ","","Yes", "No");
					
				}catch(Exception e){
					
				}
			}
		}));
		items.add(new ListEntry("Magic Coordinates", "The song is choosen by the coordinates", new OnClickListener() {
			
			@Override
			public void onClick(ListEntry sender) {
				// TODO Auto-generated method stub
				try{
					IntentIntegrator.initiateScan((Activity)home.this, "Please download barcode scanner ","","Yes", "No");
					
				}catch(Exception e){
					
				}
			}
		}));
		setListAdapter(new EntryAdapter(this, items));
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);
		main.assertMenuItems(this);
		createHomeMenu();
	}

}
