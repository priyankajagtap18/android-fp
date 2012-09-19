package com.taller.finalproject.ui;

import java.text.SimpleDateFormat;

import com.taller.finalproject.R;
import com.taller.finalproject.model.ForecastInfo;
import com.taller.finalproject.model.WeatherManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActForecastDetails extends Activity {

	private ImageView imgCondition;
	private TextView txtDayName;
	private TextView txtWeatherCondition;
	private TextView txtMinTemp;
	private TextView txtMaxTemp;
	private TextView txtWindDirection;
	private TextView txtWindSpeed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		int position = intent.getIntExtra("position", 0);
		
		ForecastInfo info = WeatherManager.getInstance().getForecastData().getForecastWeather().get(position); 
		
		setContentView(R.layout.lay_weather_details);
		
		imgCondition = (ImageView) findViewById(R.id.imgCondition);
		txtDayName = (TextView) findViewById(R.id.txtDayName);
		txtWeatherCondition = (TextView) findViewById(R.id.txtWeatherCondition);
		txtMaxTemp = (TextView) findViewById(R.id.txtMaxTemp);
		txtMinTemp = (TextView) findViewById(R.id.txtMinTemp);
		txtWindDirection = (TextView) findViewById(R.id.txtWindDirection);
		txtWindSpeed = (TextView) findViewById(R.id.txtWindSpeed);
				
		imgCondition.setImageBitmap(info.getIconBitmap());
				
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		txtDayName.setText(sdf.format(info.getDate()));
		txtWeatherCondition.setText(info.getCondition());
		txtMaxTemp.setText(Double.toString(info.getMaxCTemp()) + "° C (" + Double.toString(info.getMaxFTemp()) +"° F)");
		txtMinTemp.setText(Double.toString(info.getMinCTemp()) + "° C (" + Double.toString(info.getMinFTemp()) +"° F)");
		txtWindDirection.setText(info.getWindDirection());
		txtWindSpeed.setText(Double.toString(info.getWindSpeed()) + " Km/h");
		
		
	}
}
