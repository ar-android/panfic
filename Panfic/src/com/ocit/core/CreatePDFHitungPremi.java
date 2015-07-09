package com.ocit.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.util.Log;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.ocit.data.DataPerhitungan;

public class CreatePDFHitungPremi {
	
	String fpath;
	private DataPerhitungan data;
	public CreatePDFHitungPremi() {
		
	}

	public Boolean write(String fname, DataPerhitungan data) {
		this.data = data;
		try {
			fpath = "/sdcard/" + fname + ".pdf";
			File file = new File(fpath);
			// If file does not exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			
			Document document = new Document();
			Chunk cTitle, cSubTitle1, cSubTitle2, cSubTitle3, Nama, Alamat, No_HP, Alamat_Email, Merek_Tipe, No_Polisi, Tahun, No_Mesin, No_Rangka, Warna, 
			Harga_Pertanggungan, Comprehensive, TLO, EQVET, FW, RSCC, TS, TPL, PAD, PAP, Premi_Asuransi;
			Paragraph title, subTitle1, subTitle2, subTitle3;
			Chunk line = Chunk.NEWLINE;
			
			// step 2 create file
			PdfWriter.getInstance(document,
					new FileOutputStream(file.getAbsoluteFile()));

			Font fontTitle = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
			Font fontSubTitle = new Font(Font.FontFamily.HELVETICA, 16);

			document.open();

			title = new Paragraph();
			subTitle1 = new Paragraph();
			subTitle2 = new Paragraph();
			subTitle3 = new Paragraph();

			title.setAlignment(Element.ALIGN_CENTER);

			cTitle = new Chunk("PERHITUNGAN PREMI ASURANSI KENDARAAN", fontTitle);
			title.add(cTitle);
			cSubTitle1 = new Chunk("TERTANGGUNG SESUAI(KTP)", fontSubTitle);
			subTitle1.add(cSubTitle1);
			cSubTitle2 = new Chunk("KENDARAAN YANG DIPERTANGGUNGKAN (SESUAI STNK)", fontSubTitle);
			subTitle2.add(cSubTitle2);
			cSubTitle3 = new Chunk("ASURANSI");
			subTitle3.add(cSubTitle3);

			document.add(title);
			document.add(line);
			
			document.add(line);
			document.add(subTitle1);
			document.add(line);

			Nama = new Chunk("Nama  :  " + data.getNama());
			Alamat = new Chunk("Alamat  :  " + data.getAlamat());
			No_HP = new Chunk("No HP  :  " + data.getNo_HP() );
			Alamat_Email = new Chunk("Alamat Email  :  " + data.getAlamat_EmaiL());

			document.add(Nama);
			document.add(line);
			document.add(Alamat);
			document.add(line);
			document.add(No_HP);
			document.add(line);
			document.add(Alamat_Email);
			document.add(line);

			document.add(line);
			document.add(subTitle2);
			document.add(line);

			Merek_Tipe = new Chunk("Merek Tipe  :  " + data.getMerek_Tipe());
			No_Polisi = new Chunk("No Polisi  :  " + data.getNo_Polisi());
			Tahun = new Chunk("Tahun  :  " + data.getTahun());
			No_Mesin = new Chunk("Nomor Mesin  :  " + data.getNo_Mesin());
			No_Rangka = new Chunk("No Rangka  :  " + data.getNo_Rangka());
			Warna = new Chunk("Warna  :  " + data.getWarna());
			
			document.add(Merek_Tipe);
			document.add(line);
			document.add(No_Polisi);
			document.add(line);
			document.add(No_Mesin);
			document.add(line);
			document.add(Tahun);
			document.add(line);
			document.add(No_Rangka);
			document.add(line);
			document.add(Warna);
			document.add(line);

			document.add(line);
			document.add(subTitle2);
			document.add(line);

			Harga_Pertanggungan = new Chunk("Harga Pertanggungan  :  " + data.getHarga_Pertanggunga());
			Comprehensive = new Chunk("Comprehensive  :  " + data.getComprehensive() + " %");
			TLO = new Chunk("TLO (Total Loss Only)  :  " + data.getTLO() + " %");
			EQVET = new Chunk("Earthquake, Volcanic Eruption,Tsunami (EQVET)  :  0,075 %");
			FW = new Chunk("Flood & Windstrom (FW)  :  9,975 %");
			RSCC = new Chunk("Riot, Strike, & Civil Commotion (RSCC)  : 0,2 %");
			TS = new Chunk("Terorisme & Sabotase (TS)  :  0,2 %");
			TPL = new Chunk("Third Party Liability (TPL)  :  1 %");
			PAD = new Chunk("Personal Accident Driver (PAD)  : 0,5 %");
			PAP = new Chunk("Personal Accident Passenger (PAP)  :  0,1 %");
			Premi_Asuransi = new Chunk("Premi Asuransi  :  " + data.getPremiAsuransi());

			document.add(Harga_Pertanggungan);
			document.add(line);
			document.add(Comprehensive);
			document.add(line);
			document.add(TLO);
			document.add(line);
			document.add(EQVET);
			document.add(line);
			document.add(FW);
			document.add(line);
			document.add(RSCC);
			document.add(line);
			document.add(TS);
			document.add(line);
			document.add(TPL);
			document.add(line);
			document.add(PAD);
			document.add(line);
			document.add(PAP);
			document.add(line);
			document.add(Premi_Asuransi);
			document.add(line);

			document.close();
			document.close();

			Log.d("Suceess", "Sucess");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getFpath() {
		return fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

}