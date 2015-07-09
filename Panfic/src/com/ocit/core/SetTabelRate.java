package com.ocit.core;

public class SetTabelRate {

	double Comprehensive = 0;
	double TLO = 0;
	double a = 2.75;

	int levelmin1 = 75000000;
	int levelmin2 = 125000001;
	int levelmin3 = 200000001;
	int levelmin4 = 400000001;
	int levelmin5 = 800000001;
	
	int tahun1 = 2007;
	int tahun2 = 2008;
	int tahun3 = 2009;
	int tahun4 = 2010;

	public SetTabelRate(int tahun, int harga) {

		if ((tahun == tahun1) && (harga >= levelmin1)) {
			Comprehensive = 2.75;
			TLO = 0.43;
		}else
		if ((tahun == tahun2) && (harga >= levelmin1)){
			Comprehensive = 2.89;
			TLO = 0.43;
		}else
		if ((tahun == tahun3) && (harga >= levelmin1)) {
			Comprehensive = 3.03;
			TLO = 0.43;
		} else
		if ((tahun >= tahun4) && (harga >= levelmin1)){
			Comprehensive = 3.18;
			TLO = 0.43;
		}


		if ((tahun == tahun1) && (harga >= levelmin2)) {
			Comprehensive = 2.65;
			TLO = 0.37;
		}else
		if ((tahun == tahun2) && (harga >= levelmin2)){
			Comprehensive = 2.52;
			TLO = 0.37;
		}else
		if ((tahun == tahun3) && (harga >= levelmin2)) {
			Comprehensive = 2.40;
			TLO = 0.37;
		} else
		if ((tahun >= tahun4) && (harga >= levelmin2)){
			Comprehensive = 2.25;
			TLO = 0.37;
		}
		

		if ((tahun == tahun1) && (harga >= levelmin3)) {
			Comprehensive = 1.80;
			TLO = 0.35;
		}else
		if ((tahun == tahun2) && (harga >= levelmin3)){
			Comprehensive = 1.70;
			TLO = 0.35;
		}else
		if ((tahun == tahun3) && (harga >= levelmin3)) {
			Comprehensive = 1.60;
			TLO = 0.35;
		} else
		if ((tahun >= tahun4) && (harga >= levelmin3)){
			Comprehensive = 1.50;
			TLO = 0.35;
		}
		

		if ((tahun == tahun1) && (harga >= levelmin4)) {
			Comprehensive = 1.60;
			TLO = 0.30;
		}else
		if ((tahun == tahun2) && (harga >= levelmin4)){
			Comprehensive = 1.50;
			TLO = 0.30;
		}else
		if ((tahun == tahun3) && (harga >= levelmin4)) {
			Comprehensive = 1.40;
			TLO = 0.30;
		} else
		if ((tahun >= tahun4) && (harga >= levelmin4)){
			Comprehensive = 1.30;
			TLO = 0.30;
		}
		
		
		if ((tahun == tahun1) && (harga > levelmin5)) {
			Comprehensive = 1.45;
			TLO = 0.24;
		}else if ((tahun == tahun2) && (harga > levelmin5)) {
			Comprehensive = 1.25;
			TLO = 0.24;
		}else if ((tahun == tahun3) && (harga > levelmin5)) {
			Comprehensive = 1.25;
			TLO = 0.24;
		}else if ((tahun >= tahun4) && (harga > levelmin5)) {
			Comprehensive = 1.15;
			TLO = 0.24;
		}
	}

	public double getComprehensive() {
		return Comprehensive;
	}
	
	public double getTLO() {
		return TLO;
	}

}
