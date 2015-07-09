package com.ocit;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.ocit.adapter.AdapterGridBengkel;

public class BengkelRekanan extends Activity {
	
	private GridView gridBengkel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bengkel_rekanan);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		gridBengkel = (GridView)findViewById(R.id.gridBengkel);
		AdapterGridBengkel adapter = new AdapterGridBengkel(this);
		gridBengkel.setAdapter(adapter);
		
		gridBengkel.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					Intent i = new Intent(BengkelRekanan.this, ListBengkel.class);
					i.putExtra("Data", position);
					startActivity(i);
					
			}
		});
	}

}
