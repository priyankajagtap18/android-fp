package com.taller.finalproject.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.taller.finalproject.R;
import com.taller.finalproject.model.Forecast;
import com.taller.finalproject.model.WeatherManager;
import com.taller.finalproject.ui.adapters.WeatherListAdapter;

public class ActForecastList extends Activity {
	
	ImageView imgCurrentWeather;
	
	TextView txtTodayCondition;
	TextView txtHumidity;
	TextView txtPressure;
	TextView txtVisibility;
	TextView txtWindDirection;
	TextView txtWindSpeed;
	TextView txtTemp;
	
	ListView lstForecast;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        setContentView(R.layout.lay_forecast_list);
        
        imgCurrentWeather = (ImageView) findViewById(R.id.imgCurrentWeather);
        

        txtTodayCondition = (TextView) findViewById(R.id.txtTodayCondition);
        
        txtTemp = (TextView) findViewById(R.id.txtTemp);
        txtHumidity = (TextView) findViewById(R.id.txtHumidity);
        txtPressure = (TextView) findViewById(R.id.txtPressure);
        txtVisibility = (TextView) findViewById(R.id.txtVisibility);
        txtWindDirection = (TextView) findViewById(R.id.txtWindDirection);
        txtWindSpeed = (TextView) findViewById(R.id.txtWindSpeed);
        
        lstForecast = (ListView) findViewById(R.id.lstForecast);
        
        Forecast currentForecast = WeatherManager.getInstance().getWeatherForecast();
      
        imgCurrentWeather.setImageBitmap(currentForecast.getCurrentWeather().getIconBitmap());

        txtTodayCondition.setText(currentForecast.getCurrentWeather().getCondition());
        txtTemp.setText(Double.toString(currentForecast.getCurrentWeather().getCelciusTemp()) + " °C");
        txtHumidity.setText(Double.toString(currentForecast.getCurrentWeather().getHumidity()) + " %");
        txtPressure.setText(Double.toString(currentForecast.getCurrentWeather().getPressure()) + " Pa");
        txtVisibility.setText(Double.toString(currentForecast.getCurrentWeather().getVisibility()) + " Km");
        txtWindDirection.setText(currentForecast.getCurrentWeather().getWindDirection());
        txtWindSpeed.setText(Double.toString(currentForecast.getCurrentWeather().getWindSpeed()) + " Km/h");
                
        lstForecast.setAdapter(new WeatherListAdapter(this,currentForecast.getForecastWeather()));
	}

	
}
