package com.ocit.adapter;

import com.ocit.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterListBengkel extends BaseAdapter {

	private LayoutInflater inflanter;

	private String[] namaBengkel, alamatBengkel;
	
	public AdapterListBengkel(Context context, String[] data1, String[] data2 ) {
		inflanter = LayoutInflater.from(context);
		this.namaBengkel = data1;
		this.alamatBengkel = data2;
	}

	private class ViewHolder {
		TextView tvNamaBengkel, tvAlamat, tvTelpon, tvContactPeron;
	}

	@Override
	public int getCount() {
		return namaBengkel.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			convertView = inflanter
					.inflate(R.layout.inflate_list_bangkel, null);
			holder.tvNamaBengkel = (TextView)convertView.findViewById(R.id.tvNamaBengkel);
			holder.tvAlamat = (TextView)convertView.findViewById(R.id.tvAlamat);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.tvNamaBengkel.setText(namaBengkel[position]);
		holder.tvAlamat.setText(alamatBengkel[position]);
		return convertView;
	}
}
