package com.ocit.core;

import java.text.NumberFormat;
import java.util.Locale;

import com.ocit.HitungPremiActivity;

public class RumusHitungPremi {

	double EQVET = 0.075;
	double FW = 0.075;
	double RSCC = 0.2;
	double TS = 0.2;
	double TPL = 1;
	double PAD = 0.5;
	double PAP = 0.1;
	int persen = 100;
	
	public RumusHitungPremi() {
		
	}


	public String hitungEQVET(int harga){

		double hasil;
		int HP = harga;
		
		hasil = EQVET * HP / persen;

		Locale locale = new Locale("id", "id");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		String HASIL = formatter.format(hasil);

		return HASIL;
	}

	public String hitungFW(int harga){

		double hasil;
		int HP = harga;
		
		hasil = FW * HP / persen;

		Locale locale = new Locale("id", "id");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		String HASIL = formatter.format(hasil);

		return HASIL;
	}

	public String hitungRSCC(int harga){

		double hasil;
		int HP = harga;
		
		hasil = RSCC * HP / persen;

		Locale locale = new Locale("id", "id");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		String HASIL = formatter.format(hasil);

		return HASIL;
	}
	

	public String hitungTS(int harga){

		double hasil;
		int HP = harga;
		
		hasil = TS * HP / persen;

		Locale locale = new Locale("id", "id");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		String HASIL = formatter.format(hasil);

		return HASIL;
	}
	

	public String hitungTPL(int harga){

		double hasil;
		int HP = harga;
		
		hasil = TPL * HP / persen;

		Locale locale = new Locale("id", "id");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		String HASIL = formatter.format(hasil);

		return HASIL;
	}
	

	public String hitungPAD(int harga){

		double hasil;
		int HP = harga;
		
		hasil = PAD * HP / persen;

		Locale locale = new Locale("id", "id");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		String HASIL = formatter.format(hasil);

		return HASIL;
	}

	public String hitungPAP(int harga){

		double hasil;
		int HP = harga;
		
		hasil = PAP * HP / persen;

		Locale locale = new Locale("id", "id");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		String HASIL = formatter.format(hasil);

		return HASIL;
	}
}
