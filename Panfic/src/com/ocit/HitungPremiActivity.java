package com.ocit;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.ocit.core.CreatePDFHitungPremi;
import com.ocit.core.RumusHitungPremi;
import com.ocit.core.SetTabelRate;
import com.ocit.data.DataPerhitungan;

public class HitungPremiActivity extends Activity {

	private EditText Nama, Alamat, No_HP, Alamat_EmaiL, Merek_Tipe, No_Polisi,
			Tahun, No_Mesin, No_Rangka, Warna, Harga_Pertanggungan, etLimitTpl,
			etLimitPad, etLimitPap, etPremiAsuransi;

	private CheckBox Comprehensive, TLO, EQVET, FW, RSCC, TS, TPL, PAD, PAP;
	private boolean ComprehensiveB, TLOB, EQVETB, FWB, RSCCB, TSB, TPLB, PADB,
			PAPB;

	private Button btnHitungHP, btnKirimHP, btnSimpanHP, btnUploadFotoKTP,
			btnUploadFotoSTNK;
	private ImageView imgKTP, imgSTNK;
	public RumusHitungPremi hitung;
	public static DataPerhitungan setData;
	public static String hasilHarga;

	private String newpremiAsuransi, newHarga, newLimitTPL, newLimitPAD,
			newLimitPAP;
	public static int intHarga, intLimitTPL, intLimitPAD, intLimitPAP;

	public static String hasilComprehensive, hasilTLO, hasilEQVET, hasilFW,
			hasilRSCC, hasilTS, hasilTPL, hasilPAD, hasilPAP;
	public int intComprehensive, intTLO, intEQVET, intFW, intRSCC, intTS,
			intTPL, intPAD, intPAP;

	public String sComprehensive, sTLO, sEQVET, sFW, sRSCC, sTS, sTPL, sPAD,
			sPAP;

	double rateComprehensive = 0;
	double rateTLO = 0;
	public static SetTabelRate tableRate;

	private int administrasi = 35000;
	private int RESULT_LOAD_IMAGE_KTP, RESULT_LOAD_IMAGE_STNK;

	private CreatePDFHitungPremi fo;
	private String picturePathKTP, picturePathSTNK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hitung_premi);

		hitung = new RumusHitungPremi();
		setData = new DataPerhitungan();
		RESULT_LOAD_IMAGE_KTP = 0;
		RESULT_LOAD_IMAGE_STNK = 1;
		btnHitungHP = (Button) findViewById(R.id.btnHitungPremi);
		btnKirimHP = (Button) findViewById(R.id.btnKirimHP);
		btnSimpanHP = (Button) findViewById(R.id.btnSimpanHP);
		btnUploadFotoKTP = (Button) findViewById(R.id.btnUploadFotoKtp);
		btnUploadFotoSTNK = (Button) findViewById(R.id.btnUploadFotoStnk);

		imgKTP = (ImageView) findViewById(R.id.imgKTP);
		imgSTNK = (ImageView) findViewById(R.id.imgSTNK);

		Comprehensive = (CheckBox) findViewById(R.id.checkComprehensive);
		TLO = (CheckBox) findViewById(R.id.checkTLO);
		EQVET = (CheckBox) findViewById(R.id.checkEQVET);
		FW = (CheckBox) findViewById(R.id.checkFW);
		RSCC = (CheckBox) findViewById(R.id.checkSRCC);
		TS = (CheckBox) findViewById(R.id.checkTS);
		TPL = (CheckBox) findViewById(R.id.checkTPL);
		PAD = (CheckBox) findViewById(R.id.checkPAD);
		PAP = (CheckBox) findViewById(R.id.checkPAP);

		Nama = (EditText) findViewById(R.id.etNamaLengkapHP);
		Alamat = (EditText) findViewById(R.id.etALamatLengkapHP);
		No_HP = (EditText) findViewById(R.id.etNoHpHP);
		Alamat_EmaiL = (EditText) findViewById(R.id.etAlamatEmailHP);
		Merek_Tipe = (EditText) findViewById(R.id.etMerekTypeHP);
		No_Polisi = (EditText) findViewById(R.id.etNoPolisiHP);
		Tahun = (EditText) findViewById(R.id.etTahunHP);
		No_Mesin = (EditText) findViewById(R.id.etNoMesinHP);
		No_Rangka = (EditText) findViewById(R.id.etNoRangkaHP);
		Warna = (EditText) findViewById(R.id.etWarnaHP);
		Harga_Pertanggungan = (EditText) findViewById(R.id.etHargaPertanggunganHP);

		etPremiAsuransi = (EditText) findViewById(R.id.etPremiAsuransi);

		etLimitTpl = (EditText) findViewById(R.id.etLimitTPL);
		etLimitPad = (EditText) findViewById(R.id.etLimitPAD);
		etLimitPap = (EditText) findViewById(R.id.etLimitPAP);

		setCurrency(Harga_Pertanggungan);
		setCurrency(etLimitTpl);
		setCurrency(etLimitPad);
		setCurrency(etLimitPap);
		setCurrency(etPremiAsuransi);

		hasilHarga = Harga_Pertanggungan.getText().toString();

		btnHitungHP.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (Harga_Pertanggungan.getText().length() == 0) {
					Toast.makeText(getApplicationContext(),
							"Harga Pertanggungan Tidak Boleh Kosong",
							Toast.LENGTH_SHORT).show();
				} else if (Tahun.getText().length() == 0) {
					Toast.makeText(getApplicationContext(),
							"Tahun Tidak Boleh Kosong", Toast.LENGTH_SHORT)
							.show();
				} else if (etLimitTpl.getText().length() == 0) {
					Toast.makeText(getApplicationContext(),
							"LimitTPL Tidak Boleh Kosong", Toast.LENGTH_SHORT)
							.show();
				} else if (etLimitPad.getText().length() == 0) {
					Toast.makeText(getApplicationContext(),
							"LimitPAD Tidak Boleh Kosong", Toast.LENGTH_SHORT)
							.show();
				} else if (etLimitPap.getText().length() == 0) {
					Toast.makeText(getApplicationContext(),
							"LimitPAP Tidak Boleh Kosong", Toast.LENGTH_SHORT)
							.show();
				} else {

					String harga = Harga_Pertanggungan.getText().toString();
					String premiAsuransi = etPremiAsuransi.getText().toString();
					String limitTPL = etLimitTpl.getText().toString();
					String limitPAD = etLimitPad.getText().toString();
					String limitPAP = etLimitPap.getText().toString();

					newLimitTPL = limitTPL.replaceAll("[^\\d]+", "");
					newLimitPAD = limitPAD.replaceAll("[^\\d]+", "");
					newLimitPAP = limitPAP.replaceAll("[^\\d]+", "");
					newHarga = harga.replaceAll("[^\\d]+", "");
					newpremiAsuransi = premiAsuransi.replaceAll("[^\\d]+", "");

					intHarga = Integer.parseInt(newHarga);
					intLimitTPL = Integer.parseInt(newLimitTPL);
					intLimitPAD = Integer.parseInt(newLimitPAD);
					intLimitPAP = Integer.parseInt(newLimitPAP);

					String tahun1 = Tahun.getText().toString();
					int tahun = Integer.parseInt(tahun1);

					tableRate = new SetTabelRate(tahun, intHarga);
					rateComprehensive = tableRate.getComprehensive();
					rateTLO = tableRate.getTLO();

					// setDataHP();
					settingChecked();
					setHasilKali();

					sComprehensive = hasilComprehensive.replaceAll("[^\\d]+",
							"");
					sTLO = hasilTLO.replaceAll("[^\\d]+", "");
					sEQVET = hasilEQVET.replaceAll("[^\\d]+", "");
					sFW = hasilFW.replaceAll("[^\\d]+", "");
					sRSCC = hasilRSCC.replaceAll("[^\\d]+", "");
					sTS = hasilTS.replaceAll("[^\\d]+", "");
					sTPL = hasilTPL.replaceAll("[^\\d]+", "");
					sPAD = hasilPAD.replaceAll("[^\\d]+", "");
					sPAP = hasilPAP.replaceAll("[^\\d]+", "");

					intComprehensive = Integer.parseInt(sComprehensive);
					intTLO = Integer.parseInt(sTLO);
					intEQVET = Integer.parseInt(sEQVET);
					intFW = Integer.parseInt(sFW);
					intRSCC = Integer.parseInt(sRSCC);
					intTS = Integer.parseInt(sTS);
					intTPL = Integer.parseInt(sTPL);
					intPAD = Integer.parseInt(sPAD);
					intPAP = Integer.parseInt(sPAP);

					int hasilHitungPremi = intComprehensive + intTLO + intEQVET
							+ intFW + intRSCC + intTS + intTPL + intPAD
							+ intPAP + administrasi;

					String PremiA = Integer.toString(hasilHitungPremi);
					etPremiAsuransi.setText(PremiA);

					setDataHP();
				}
			}
		});

		Switch onOffSwitch = (Switch) findViewById(R.id.on_off_switch);
		onOffSwitch.setChecked(true);
		onOffSwitch
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						Log.v("Switch State=", "" + isChecked);

						if (isChecked == false) {
							Comprehensive.setChecked(false);
							TLO.setChecked(true);
						} else {
							Comprehensive.setChecked(true);
							TLO.setChecked(false);
						}
					}

				});

		btnKirimHP.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				fo = new CreatePDFHitungPremi();
				fo.write("Hitung Premi", setData);

				dialogSend();
			}
		});

		btnUploadFotoKTP.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent iKTP = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(iKTP, RESULT_LOAD_IMAGE_KTP);
			}
		});

		btnUploadFotoSTNK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent iSTNK = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(iSTNK, RESULT_LOAD_IMAGE_STNK);
			}
		});

		btnSimpanHP.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				fo = new CreatePDFHitungPremi();
				fo.write("Hitung Premi", setData);

				Toast.makeText(getApplicationContext(),
						"File Berhasil disimpan" + fo.getFpath(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	public String hitungComprehensive(int harga) {

		double hasil;
		int HP = harga;

		hasil = rateComprehensive * HP / 100;

		Locale locale = new Locale("id", "id");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		String HASIL = formatter.format(hasil);

		return HASIL;
	}

	public String hitungTLO(int harga) {

		double hasil;
		int HP = harga;

		hasil = rateTLO * HP / 100;

		Locale locale = new Locale("id", "id");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		String HASIL = formatter.format(hasil);

		return HASIL;
	}

	private void setHasilKali() {
		if (ComprehensiveB == true) {
			hasilComprehensive = hitungComprehensive(intHarga);
		} else {
			hasilComprehensive = "0";
		}
		if (TLOB == true) {
			hasilTLO = hitungTLO(intHarga);
		} else {
			hasilTLO = "0";
		}
		if (EQVETB == true) {
			hasilEQVET = hitung.hitungEQVET(intHarga);
		} else {
			hasilEQVET = "0";
		}
		if (FWB == true) {
			hasilFW = hitung.hitungFW(intHarga);
		} else {
			hasilFW = "0";
		}
		if (RSCCB == true) {
			hasilRSCC = hitung.hitungRSCC(intHarga);
		} else {
			hasilRSCC = "0";
		}
		if (TSB == true) {
			hasilTS = hitung.hitungTS(intHarga);
		} else {
			hasilTS = "0";
		}
		if (TPLB == true) {
			hasilTPL = hitung.hitungTPL(intLimitTPL);
		} else {
			hasilTPL = "0";
		}
		if (PADB == true) {
			hasilPAD = hitung.hitungPAD(intLimitPAD);
		} else {
			hasilPAD = "0";
		}
		if (PAPB == true) {
			hasilPAP = hitung.hitungPAP(intLimitPAP);
		} else {
			hasilPAP = "0";
		}
	}

	private void setDataHP() {

		String setComprehensive = Double.toString(rateComprehensive);
		String setTLO = Double.toString(rateTLO);

		setData.setNama(Nama.getText().toString());
		setData.setAlamat(Alamat.getText().toString());
		setData.setNo_HP(No_HP.getText().toString());
		setData.setAlamat_EmaiL(Alamat_EmaiL.getText().toString());
		setData.setMerek_Tipe(Merek_Tipe.getText().toString());
		setData.setNo_Polisi(No_Polisi.getText().toString());
		setData.setTahun(Tahun.getText().toString());
		setData.setNo_Mesin(No_Mesin.getText().toString());
		setData.setNo_Rangka(No_Rangka.getText().toString());
		setData.setWarna(Warna.getText().toString());

		setData.setLimitTpl(etLimitTpl.getText().toString());
		setData.setLimitPad(etLimitPad.getText().toString());
		setData.setLimitPap(etLimitPap.getText().toString());
		setData.setPremiAsuransi("Rp. " + etPremiAsuransi.getText().toString());
		setData.setHarga_Pertanggunga("Rp. "
				+ Harga_Pertanggungan.getText().toString());
		setData.setComprehensive(setComprehensive);
		setData.setTLO(setTLO);

	}

	private void settingChecked() {
		if (Comprehensive.isChecked() == true) {
			ComprehensiveB = true;
		} else {
			ComprehensiveB = false;
		}

		if (TLO.isChecked() == true) {
			TLOB = true;
		} else {
			TLOB = false;
		}

		if (EQVET.isChecked() == true) {
			EQVETB = true;
		} else {
			EQVETB = false;
		}

		if (FW.isChecked() == true) {
			FWB = true;
		} else {
			FWB = false;
		}

		if (RSCC.isChecked() == true) {
			RSCCB = true;
		} else {
			RSCCB = false;
		}

		if (TS.isChecked() == true) {
			TSB = true;
		} else {
			TSB = false;
		}

		if (TPL.isChecked() == true) {
			TPLB = true;
		} else {
			TPLB = false;
		}

		if (PAD.isChecked() == true) {
			PADB = true;
		} else {
			PADB = false;
		}

		if (PAP.isChecked() == true) {
			PAPB = true;
		} else {
			PAPB = false;
		}

	}

	private void setCurrency(final EditText edt) {
		edt.addTextChangedListener(new TextWatcher() {
			private String current = "";

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (!s.toString().equals(current)) {
					edt.removeTextChangedListener(this);

					Locale local = new Locale("id", "id");
					String replaceable = String.format("[Rp,.\\s]",
							NumberFormat.getCurrencyInstance().getCurrency()
									.getSymbol(local));
					String cleanString = s.toString().replaceAll(replaceable,
							"");

					double parsed;
					try {
						parsed = Double.parseDouble(cleanString);
					} catch (NumberFormatException e) {
						parsed = 0.00;
					}

					NumberFormat formatter = NumberFormat
							.getCurrencyInstance(local);
					formatter.setMaximumFractionDigits(0);
					formatter.setParseIntegerOnly(true);
					String formatted = formatter.format((parsed));

					String replace = String.format("[Rp\\s]",
							NumberFormat.getCurrencyInstance().getCurrency()
									.getSymbol(local));
					String clean = formatted.replaceAll(replace, "");

					current = formatted;
					edt.setText(clean);
					edt.setSelection(clean.length());
					edt.addTextChangedListener(this);
				}
			}
		});
	}

	protected void dialogSend() {
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(
				HitungPremiActivity.this);
		builderSingle.setIcon(R.drawable.ic_launcher);
		builderSingle.setTitle("Pilih Alamat Email:-");
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				HitungPremiActivity.this,
				android.R.layout.select_dialog_singlechoice);

		arrayAdapter.add("admin.bpn@panfic.com");
		arrayAdapter.add("admin.mlg@panfic.com");
		arrayAdapter.add("admin.sby2@panfic.com");
		arrayAdapter.add("admin.sby1@panfic.com");
		arrayAdapter.add("admin.mks@panfic.com");
		arrayAdapter.add("admin.dps@panfic.com");
		arrayAdapter.add("admin.jogja@panfic.com");
		arrayAdapter.add("admin.colo@panfic.com");
		arrayAdapter.add("admin.smg@panfic.com");
		arrayAdapter.add("admin.crb@panfic.com");
		arrayAdapter.add("admin.pwt@panfic.com");
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
						uris.add(picturePathKTP);
						uris.add(picturePathSTNK);
						uris.add(fo.getFpath());
						email(HitungPremiActivity.this, strName, strName,
								"Hitung Premi", "Terlapir file hitung premi",
								uris);
					}
				});
		builderSingle.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:

			if (resultCode == RESULT_OK && null != data) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				picturePathKTP = cursor.getString(columnIndex);
				cursor.close();

				File imgFile = new File(picturePathKTP);
				if (imgFile.exists()) {
					Bitmap myBitmap = BitmapFactory.decodeFile(imgFile
							.getAbsolutePath());
					imgKTP.setImageBitmap(myBitmap);
					imgKTP.setVisibility(View.VISIBLE);
				}

				// Toast.makeText(this, "Berhasil attach" + picturePathKTP,
				// Toast.LENGTH_SHORT).show();
			}

			break;

		case 1:
			if (resultCode == RESULT_OK && null != data) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToLast();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				picturePathSTNK = cursor.getString(columnIndex);
				cursor.close();

				File imgFile = new File(picturePathSTNK);
				if (imgFile.exists()) {
					Bitmap myBitmap = BitmapFactory.decodeFile(imgFile
							.getAbsolutePath());
					imgSTNK.setImageBitmap(myBitmap);
					imgSTNK.setVisibility(View.VISIBLE);
				}

				// Toast.makeText(this, "Berhasil attach" + picturePathSTNK,
				// Toast.LENGTH_SHORT).show();

			}
			break;
		default:
			break;
		}
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

}
