package com.ocit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ocit.core.CreatePDFFOrmulirKlaim;
import com.ocit.data.DataFormulirKlaim;

public class FormulirKlaim extends Activity {

	private EditText Nama_Lengkap, Alamat_Lengkap, No_HP, Email, No_Polis,
			Merek_Jenis, No_Polisi, No_Rangka, No_Mesin, Tahun, Warna,
			Nama_Pengemudi, Umur, Pekerjaan, Hubungan_dengan_Tertanggung,
			Jenis_Golongan_SIM, Berlaku_sampai_dengan_Tanggal, Tanggal, Tempat,
			Jam, keterangan;
	private Button simpan, kirim, upload;
	public DataFormulirKlaim formulirKlaim;
	private int RESULT_LOAD_IMAGE;
	private String pathIMG;
	private CreatePDFFOrmulirKlaim foKlaim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulir_klaim);

		Nama_Lengkap = (EditText) findViewById(R.id.etNamaLengkap);
		Alamat_Lengkap = (EditText) findViewById(R.id.etALamatLengkap);
		No_HP = (EditText) findViewById(R.id.etNoHp);
		Email = (EditText) findViewById(R.id.etAlamatEmail);
		No_Polis = (EditText) findViewById(R.id.etNoPolis);
		Merek_Jenis = (EditText) findViewById(R.id.etMerekJenis);
		No_Polisi = (EditText) findViewById(R.id.etNoPolisi);
		No_Rangka = (EditText) findViewById(R.id.etNoRangka);
		No_Mesin = (EditText) findViewById(R.id.etNoMesin);
		Tahun = (EditText) findViewById(R.id.etTahun);
		Warna = (EditText) findViewById(R.id.etWarna);
		Nama_Pengemudi = (EditText) findViewById(R.id.etNamaPengemudi);
		Umur = (EditText) findViewById(R.id.etUmur);
		Pekerjaan = (EditText) findViewById(R.id.etPekerjaan);
		Hubungan_dengan_Tertanggung = (EditText) findViewById(R.id.etHubunganDenganTertanggung);
		Jenis_Golongan_SIM = (EditText) findViewById(R.id.etJenisSim);
		Berlaku_sampai_dengan_Tanggal = (EditText) findViewById(R.id.etBerlakuSim);
		Tanggal = (EditText) findViewById(R.id.etTanggal);
		Tempat = (EditText) findViewById(R.id.etTempat);
		Jam = (EditText) findViewById(R.id.etJam);
		keterangan = (EditText) findViewById(R.id.etKeterangan);

		simpan = (Button) findViewById(R.id.btnSimpan);
		kirim = (Button) findViewById(R.id.btnKirim);
		upload = (Button) findViewById(R.id.btnUploadFormulir);

		kirim.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				formulirKlaim = new DataFormulirKlaim();
				setDataFormulir();
				foKlaim = new CreatePDFFOrmulirKlaim();
				foKlaim.write("Formulir Klaim", formulirKlaim);
				dialogSend();
			}
		});
		simpan.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				formulirKlaim = new DataFormulirKlaim();
				setDataFormulir();
				foKlaim = new CreatePDFFOrmulirKlaim();
				foKlaim.write("Formulir Klaim", formulirKlaim);
				Toast.makeText(getApplicationContext(),
						"File Berhasil Disimpan di " + foKlaim.getFpath(),
						Toast.LENGTH_SHORT).show();
			}
		});

		upload.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent INTENT = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(INTENT, RESULT_LOAD_IMAGE);
			}
		});
	}

	private void setDataFormulir() {

		formulirKlaim.setNama_Lengkap(Nama_Lengkap.getText().toString());
		formulirKlaim.setAlamat_Lengkap(Alamat_Lengkap.getText().toString());
		formulirKlaim.setNo_HP(No_HP.getText().toString());
		formulirKlaim.setEmail(Email.getText().toString());
		formulirKlaim.setNo_Polis(No_Polis.getText().toString());
		formulirKlaim.setMerek_Jenis(Merek_Jenis.getText().toString());
		formulirKlaim.setNo_Polisi(No_Polisi.getText().toString());
		formulirKlaim.setNo_Rangka(No_Rangka.getText().toString());
		formulirKlaim.setNo_Mesin(No_Mesin.getText().toString());
		formulirKlaim.setTahun(Tahun.getText().toString());
		formulirKlaim.setWarna(Warna.getText().toString());
		formulirKlaim.setNama_Pengemudi(Nama_Pengemudi.getText().toString());
		formulirKlaim.setUmur(Umur.getText().toString());
		formulirKlaim.setPekerjaan(Pekerjaan.getText().toString());
		formulirKlaim
				.setHubungan_dengan_Tertanggung(Hubungan_dengan_Tertanggung
						.getText().toString());
		formulirKlaim.setJenis_Golongan_SIM(Jenis_Golongan_SIM.getText()
				.toString());
		formulirKlaim
				.setBerlaku_sampai_dengan_Tanggal(Berlaku_sampai_dengan_Tanggal
						.getText().toString());
		formulirKlaim.setTanggal(Tanggal.getText().toString());
		formulirKlaim.setTempat(Tempat.getText().toString());
		formulirKlaim.setJam(Jam.getText().toString());
		formulirKlaim.setKeterangan(keterangan.getText().toString());
	}

	protected void dialogSend() {
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(
				FormulirKlaim.this);
		builderSingle.setIcon(R.drawable.ic_launcher);
		builderSingle.setTitle("Pilih Alamat Email:-");
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				FormulirKlaim.this, android.R.layout.select_dialog_singlechoice);

		arrayAdapter.add("klaim.bpn@panfic.com");
		arrayAdapter.add("klaim.mlg@panfic.com");
		arrayAdapter.add("klaim.sby2@panfic.com");
		arrayAdapter.add("klaim.sby1@panfic.com");
		arrayAdapter.add("klaim.mks@panfic.com");
		arrayAdapter.add("klaim.dps@panfic.com");
		arrayAdapter.add("klaim.jogja@panfic.com");
		arrayAdapter.add("klaim.solo@panfic.com");
		arrayAdapter.add("klaim.smg@panfic.com");
		arrayAdapter.add("klaim.crb@panfic.com");
		arrayAdapter.add("klaim.pwt@panfic.com");
		arrayAdapter.add("idris_mhd@panfic.com");

		builderSingle.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builderSingle.setAdapter(arrayAdapter,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String strName = arrayAdapter.getItem(which);
						ArrayList<String> uris = new ArrayList<String>();
						uris.add(foKlaim.getFpath());
						uris.add(pathIMG);
						email(FormulirKlaim.this, strName, strName, "Formulir Pendaftaran Klaim", "Formulir Pendaftaran Klaim", uris);
						//sendEmail(strName);
					}
				});
		builderSingle.show();
	}

	public static void email(Context context, String emailTo, String emailCC,
			String subject, String emailText, List<String> filePaths) {
		// need to "send multiple" to get more than one attachment
		final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
		emailIntent.setType("text/plain");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
				new String[] { emailTo });
		emailIntent.putExtra(android.content.Intent.EXTRA_CC,
				new String[] { emailCC });
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
		// has to be an ArrayList
		ArrayList<Uri> uris = new ArrayList<Uri>();
		// convert from paths to Android friendly Parcelable Uri's
		for (String file : filePaths) {
			File fileIn = new File(file);
			Uri u = Uri.fromFile(fileIn);
			uris.add(u);
		}
		emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
		context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			pathIMG = cursor.getString(columnIndex);
			cursor.close();

			// String picturePath contains the path of selected Image
			
		}
	}
}