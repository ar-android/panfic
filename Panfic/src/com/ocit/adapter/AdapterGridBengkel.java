package com.ocit.adapter;

import com.ocit.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterGridBengkel extends BaseAdapter {

	LayoutInflater layoutInflater;
	String[] data = new String[] { "BALIKPAPAN", "MALANG", "SURABAYA 1",
			"SURABAYA II", "MAKASAR", "DENPASAR", "YOGYAKARTA", "SOLO",
			"SEMARANG", "CIREBON", "PURWOKERTO" };

	public AdapterGridBengkel(Context context) {
		layoutInflater = LayoutInflater.from(context);
	}

	private class ViewHolder {
		TextView menu;
	}

	@Override
	public int getCount() {
		return data.length;
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
			convertView = layoutInflater.inflate(R.layout.inflate_menu_bengkel,
					null);
			holder.menu = (TextView) convertView
					.findViewById(R.id.btnMenuBengkel);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.menu.setText(data[position]);
		return convertView;
	}
}
