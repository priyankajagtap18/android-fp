package com.taller.finalproject.ui.adapters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.taller.finalproject.R;
import com.taller.finalproject.model.BatteryLogItem;
import com.taller.finalproject.model.ForecastInfo;
import com.taller.finalproject.ui.adapters.ForecastListAdapter.ViewHolder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BatteryLogAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<BatteryLogItem> list; 
	
	public BatteryLogAdapter(Activity act, ArrayList<BatteryLogItem> list){
		this.activity = act;
		this.list = list;
	}
	
	public int getCount() {
		return list.size();
	}

	public BatteryLogItem getItem(int index) {
		return list.get(index);
	}

	public long getItemId(int index) {
		return index;
	}

	public View getView(int position, View rowView, ViewGroup parent) {
		
		if (rowView == null){
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.adapter_battery_log, null);
			ViewHolder holder = new ViewHolder();
			
			holder.txtRelation = (TextView) rowView.findViewById(R.id.txtRelation);
			holder.txtLevelPct = (TextView) rowView.findViewById(R.id.txtLevelPct);
			holder.txtDate = (TextView) rowView.findViewById(R.id.txtDate);
			holder.imgBatteryStatus = (ImageView) rowView.findViewById(R.id.imgBatteryStatus);
			rowView.setTag(holder);
		}
				
		ViewHolder holder = (ViewHolder) rowView.getTag();
		
		BatteryLogItem logItem = getItem(position); 

		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
		holder.txtDate.setText(sdf.format(logItem.getLogDate()));
		holder.txtRelation.setText(logItem.getLevel() + "/" + logItem.getMaxLevel());
		int pctLevel = ((logItem.getLevel() * 100 )/logItem.getMaxLevel());
		holder.txtLevelPct.setText(pctLevel + "%");
				
		if (pctLevel > 75)
			holder.imgBatteryStatus.setImageDrawable(activity.getResources().getDrawable(R.drawable.battery_status_100));
		else if (pctLevel > 50) {
			holder.imgBatteryStatus.setImageDrawable(activity.getResources().getDrawable(R.drawable.battery_status_75));
		} else if (pctLevel > 25) {
			holder.imgBatteryStatus.setImageDrawable(activity.getResources().getDrawable(R.drawable.battery_status_50));
		} else if (pctLevel > 10) {
			holder.imgBatteryStatus.setImageDrawable(activity.getResources().getDrawable(R.drawable.battery_status_25));
		} else if (pctLevel > 0) {
			holder.imgBatteryStatus.setImageDrawable(activity.getResources().getDrawable(R.drawable.battery_status_0));
		}
		
		return rowView;
		
	}

	
	static class ViewHolder {
		ImageView imgBatteryStatus;
		TextView txtDate;
		TextView txtRelation;
		TextView txtLevelPct;
	  }
	
}
