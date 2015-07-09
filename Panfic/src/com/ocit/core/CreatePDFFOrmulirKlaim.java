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
import com.ocit.data.DataFormulirKlaim;
import com.ocit.data.DataPerhitungan;

public class CreatePDFFOrmulirKlaim {
	
	String fpath;
	
	public CreatePDFFOrmulirKlaim() {
		
	}

	public Boolean write(String fname, DataFormulirKlaim  data) {
		try {
			fpath = "/sdcard/" + fname + ".pdf";
			File file = new File(fpath);
			// If file does not exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			Document document = new Document();
			Chunk cTitle, cSubTitle1, cSubTitle2, cSubTitle3, cSubTitle4, Nama_Lengkap, Alamat_Lengkap,
			No_HP, Email, No_Polis,
			Merek_Jenis, No_Polisi, No_Rangka, No_Mesin, Tahun, Warna,
			Nama_Pengemudi, Umur, Pekerjaan, Hubungan_dengan_Tertanggung,
			Jenis_Golongan_SIM, Berlaku_sampai_dengan_Tanggal, Tanggal, Tempat,
			Jam, keterangan;
			Paragraph title, subTitle1, subTitle2, subTitle3, subTitle4;
			Chunk line = Chunk.NEWLINE;

			// step 2
			PdfWriter.getInstance(document,
					new FileOutputStream(file.getAbsoluteFile()));

			Font fontTitle = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
			Font fontSubTitle = new Font(Font.FontFamily.HELVETICA, 16);

			document.open();

			title = new Paragraph();
			subTitle1 = new Paragraph();
			subTitle2 = new Paragraph();
			subTitle3 = new Paragraph();
			subTitle4 = new Paragraph();
			
			title.setAlignment(Element.ALIGN_CENTER);

			cTitle = new Chunk("FORMULIR PENDAFTARAN KLAIM", fontTitle);
			title.add(cTitle);
			cSubTitle1 = new Chunk("TERTANGGUNG", fontSubTitle);
			subTitle1.add(cSubTitle1);
			cSubTitle2 = new Chunk("KENDARAAN YANG DIPERTANGGUNGKAN", fontSubTitle);
			subTitle2.add(cSubTitle2);
			cSubTitle3 = new Chunk("KETERGANGAN KETIKA KECELAKAAN/KEHILANGAN", fontSubTitle);
			subTitle3.add(cSubTitle3);
			cSubTitle4 = new Chunk("TERANGKAN SELENGKAPNYA BAGAIMANA KEJADIAN TERJADI", fontSubTitle);
			subTitle4.add(cSubTitle4);

			document.add(title);
			document.add(line);
			
			document.add(line);
			document.add(subTitle1);
			document.add(line);

			Nama_Lengkap = new Chunk("Nama  :  " + data.getNama_Lengkap());
			Alamat_Lengkap = new Chunk("Alamat  :  " + data.getAlamat_Lengkap());
			No_HP = new Chunk("No HP  :  " + data.getNo_HP());
			Email = new Chunk("Alamat Email  :  " + data.getEmail());
			No_Polis = new Chunk("No Polis  :  " + data.getNo_Polis());

			document.add(Nama_Lengkap);
			document.add(line);
			document.add(Alamat_Lengkap);
			document.add(line);
			document.add(No_HP);
			document.add(line);
			document.add(Email);
			document.add(line);
			document.add(No_Polis);
			document.add(line);

			document.add(line);
			document.add(subTitle2);
			document.add(line);

			Merek_Jenis = new Chunk("Merek Tipe  :  " + data.getMerek_Jenis());
			No_Polisi = new Chunk("No Polisi  :  " + data.getNo_Polisi());
			Tahun = new Chunk("Tahun  :  " + data.getTahun());
			No_Mesin = new Chunk("Nomor Mesin  :  " + data.getNo_Mesin());
			No_Rangka = new Chunk("No Rangka  :  " + data.getNo_Rangka());
			Warna = new Chunk("Warna  :  " + data.getWarna());
			
			document.add(Merek_Jenis);
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

			Nama_Pengemudi = new Chunk("Nama Pengemudi  :  " + data.getNama_Pengemudi());
			Umur = new Chunk("Umur  :  " + data.getUmur());
			Pekerjaan= new Chunk("Pekerjaan  :  " + data.getPekerjaan());
			Hubungan_dengan_Tertanggung = new Chunk("Hubungan Dengan Tertanggung  :  " + data.getHubungan_dengan_Tertanggung());
			Jenis_Golongan_SIM = new Chunk("Jenis Golongan SIM  :  " + data.getJenis_Golongan_SIM());
			Berlaku_sampai_dengan_Tanggal = new Chunk("Berlaku Sampai Dengan Tanggal  :  " + data.getBerlaku_sampai_dengan_Tanggal());

			document.add(line);
			document.add(subTitle2);
			document.add(line);

			document.add(Nama_Pengemudi);
			document.add(line);
			document.add(Umur);
			document.add(line);
			document.add(Pekerjaan);
			document.add(line);
			document.add(Hubungan_dengan_Tertanggung);
			document.add(line);
			document.add(Jenis_Golongan_SIM);
			document.add(line);
			document.add(Berlaku_sampai_dengan_Tanggal);
			document.add(line);
			
			Tanggal = new Chunk("Tanggal  :  " + data.getTanggal());
			Tempat = new Chunk("Tempat  :  " +data.getTempat());
			Jam = new Chunk("Jam  :  " + data.getJam());

			document.add(line);
			document.add(subTitle3);
			document.add(line);
			
			document.add(Tanggal);
			document.add(line);
			document.add(Tempat);
			document.add(line);
			document.add(Jam);
			document.add(line);

			keterangan = new Chunk(data.getKeterangan());
			document.add(line);
			document.add(subTitle4);
			document.add(line);
			
			document.add(keterangan);
			document.add(line);

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