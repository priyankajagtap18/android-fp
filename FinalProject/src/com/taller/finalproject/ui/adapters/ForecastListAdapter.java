package com.taller.finalproject.ui.adapters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.taller.finalproject.R;
import com.taller.finalproject.model.ForecastInfo;

public class ForecastListAdapter extends BaseAdapter {

	ArrayList<ForecastInfo> forecastList;
	private Activity activity;
	private LayoutInflater inflater;
	
	
	public ForecastListAdapter(Activity act, ArrayList<ForecastInfo> forecastList) {
		super();
		this.activity = act;
		this.forecastList = forecastList;
		this.inflater = LayoutInflater.from(activity);
	}
	
	public int getCount() {
		return forecastList.size();
	}

	public ForecastInfo getItem(int i) {
		return forecastList.get(i);
	}

	public long getItemId(int i) {
		return i;
	}

	
	public View getView(int position, View rowView, ViewGroup parent) {
				
		if (rowView == null){
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.adapter_forecast_list, null);
			ViewHolder holder = new ViewHolder();
			holder.imgCondition = (ImageView) rowView.findViewById(R.id.imgCondition);
			holder.txtDayName = (TextView) rowView.findViewById(R.id.txtDayName);
			holder.txtWeatherCondition = (TextView) rowView.findViewById(R.id.txtWeatherCondition);
			holder.txtMaxTemp = (TextView) rowView.findViewById(R.id.txtMaxTemp);
			holder.txtMinTemp = (TextView) rowView.findViewById(R.id.txtMinTemp);
			
			rowView.setTag(holder);
		}
				
		ViewHolder holder = (ViewHolder) rowView.getTag();
		
		ForecastInfo info = getItem(position); 
		holder.imgCondition.setImageBitmap(info.getIconBitmap());
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		holder.txtDayName.setText(sdf.format(info.getDate()));
		holder.txtWeatherCondition.setText(info.getCondition());
		holder.txtMaxTemp.setText(Double.toString(info.getMaxCTemp()) + "° C");
		holder.txtMinTemp.setText(Double.toString(info.getMinCTemp()) + "° C");
		
		return rowView;
	}

	static class ViewHolder {
		ImageView imgCondition;
		TextView txtDayName;
		TextView txtWeatherCondition;
		TextView txtMaxTemp;
		TextView txtMinTemp;
	  }
	
}
