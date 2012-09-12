package com.taller.finalproject.ui;

import com.taller.finalproject.R;
import com.taller.finalproject.ui.adapters.WeatherListAdapter;
import com.taller.finalproject.model.*;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActForecastList extends Activity {
	
	TextView txtTodayTitle;
	
	ListView lstForecast;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        setContentView(R.layout.lay_forecast_list);
        
        txtTodayTitle = (TextView) findViewById(R.id.txtTodayTitle);

        lstForecast = (ListView) findViewById(R.id.lstForecast);
        
        Forecast currentForecast = WeatherManager.getInstance().getWeatherForecast();
        txtTodayTitle.setText("Monday, 22th march");
        
        //setIconImage(currentForecast.getCurrentWeather().getIconUrl());
        ImageView imgCurrentWeather = (ImageView) findViewById(R.id.imgCurrentWeather);
        imgCurrentWeather.setImageBitmap(currentForecast.getCurrentWeather().getIconBitmap());
        
        lstForecast.setAdapter(new WeatherListAdapter(this,currentForecast.getForecastWeather()));
	}

	
}
