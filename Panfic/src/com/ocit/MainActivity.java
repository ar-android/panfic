package com.ocit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ocit.adapter.AdapterMenu;

public class MainActivity extends Activity {

	private ListView lvMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		String[] menu = new String[] { "Asuransi Kendaraan Bermotor",
				"Hitung Premi", "Bengkel Rekanan", "Klaim",
				"Pan Pacific Insurance", "Kontak Kami" };

		lvMenu = (ListView) findViewById(R.id.lvMenu);
		AdapterMenu adapterMenu = new AdapterMenu(getApplicationContext(), menu);
		lvMenu.setAdapter(adapterMenu);

		lvMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					Intent asuransi = new Intent(getApplicationContext(),
							Asuransi.class);
					startActivity(asuransi);
					break;

				case 1:
					Intent hitungPremi = new Intent(getApplicationContext(),
							HitungPremiActivity.class);
					startActivity(hitungPremi);
					break;

				case 2:
					Intent bengkelRekanan = new Intent(getApplicationContext(),
							BengkelRekanan.class);
					startActivity(bengkelRekanan);
					break;

				case 3:
					dialogEdit();
					break;

				case 4:
					Intent panInsurance = new Intent(getApplicationContext(),
							PanInsurance.class);
					startActivity(panInsurance);
					break;

				case 5:
					Intent kontakKami = new Intent(getApplicationContext(),
							KontakKami.class);
					startActivity(kontakKami);
					break;

				default:
					break;
				}

			}
		});
	}

	protected void dialogEdit() {
		String opsiDialog[] = { "Prosedur Klaim", "Laporan Klaim" };
		AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
		b.setNeutralButton("Tutup", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		b.setTitle("KLAIM");
		b.setItems(opsiDialog, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {				
				switch (which) {
				case 0:
					Intent i = new Intent(getApplicationContext(), Klaim.class);
					startActivity(i);
					break;
				case 1:
					Intent x = new Intent(getApplicationContext(),
							FormulirKlaim.class);
					startActivity(x);
					break;
				}
			}
		});
		b.show();
	}

}
