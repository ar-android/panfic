package com.ocit.adapter;

import com.ocit.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterMenu extends BaseAdapter {

	private LayoutInflater inflater;
	private String[] data;
	
	public AdapterMenu(Context context, String[] menu) {
	
		inflater = LayoutInflater.from(context);
		this.data = menu;
		
	}

	class ViewHolder{
		TextView tvMenu;
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
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.list_menu, null);
			holder.tvMenu = (TextView)convertView.findViewById(R.id.tvMenu);
			
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvMenu.setText(data[position]);
		return convertView;
	}
	
}
