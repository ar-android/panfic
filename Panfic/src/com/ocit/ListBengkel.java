package com.ocit;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ListView;

import com.ocit.adapter.AdapterListBengkel;
import com.ocit.data.Databengkel;

public class ListBengkel extends Activity {

	private ListView lvBengkel;
	String[] namaBengkel, alamatBengkel;
	String[] data = new String[] { "BALIKPAPAN", "MALANG", "SURABAYA 1",
			"SURABAYA II", "MAKASAR", "DENPASAR", "YOGYAKARTA" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_bengkel);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	

		//data[0] = Databengkel.BALIKPAPAN_NAMA;
		int intent = getIntent().getIntExtra("Data", 0);
		switch (intent) {
		case 0:
			namaBengkel = Databengkel.BALIKPAPAN_NAMA;
			alamatBengkel = Databengkel.BALIKPAPAN_ALAMAT;
			break;

		case 1:
			namaBengkel = Databengkel.MALANG_NAMA;
			alamatBengkel = Databengkel.MALANG_ALAMAT;
			break;

		case 2:
			namaBengkel = Databengkel.SURABAYAI_NAMA;
			alamatBengkel = Databengkel.SURABAYAI_ALAMAT;
			break;

		case 3:
			namaBengkel = Databengkel.SURABAYAII_NAMA;
			alamatBengkel = Databengkel.SURABAYAII_ALAMAT;
			break;

		case 4:
			namaBengkel = Databengkel.MAKASAR_NAMA;
			alamatBengkel = Databengkel.MAKASAR_ALAMAT;
			break;

		case 5:
			namaBengkel = Databengkel.DENPASAR_NAMA;
			alamatBengkel = Databengkel.DENPASAR_ALAMAT;
			break;
			
		case 6:
			namaBengkel = Databengkel.YOGYAKARTA_NAMA;
			alamatBengkel = Databengkel.YOGYAKARTA_ALAMAT;
			break;

		default:
			break;
		}

		lvBengkel = (ListView) findViewById(R.id.lvBengkel);
		AdapterListBengkel adapter = new AdapterListBengkel(
				getApplicationContext(), namaBengkel, alamatBengkel);
		lvBengkel.setAdapter(adapter);

	}
}
