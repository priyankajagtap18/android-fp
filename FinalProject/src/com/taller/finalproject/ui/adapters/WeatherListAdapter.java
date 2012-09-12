package com.taller.finalproject.ui.adapters;

import java.util.ArrayList;

import com.taller.finalproject.R;
import com.taller.finalproject.model.ForecastInfo;
import com.taller.finalproject.ui.ActForecastList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherListAdapter extends BaseAdapter {

	ArrayList<ForecastInfo> forecastList;
	private Activity activity;
	private LayoutInflater inflater;
	
	
	public WeatherListAdapter(Activity act, ArrayList<ForecastInfo> forecastList) {
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

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if (view == null) {
			view = inflater.inflate(R.layout.lay_adapter_weather, null);
		}

		final ImageView imgCondition = (ImageView) view.findViewById(R.id.imgCondition);
		ForecastInfo info = getItem(position); 
		imgCondition.setImageBitmap(info.getIconBitmap());
		return view;
	}

}
