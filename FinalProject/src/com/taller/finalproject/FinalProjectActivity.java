package com.taller.finalproject;

import com.taller.finalproject.R;
import com.taller.finalproject.model.Forecast;
import com.taller.finalproject.model.WeatherManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FinalProjectActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("AndroidFinalProject", "Main activity created");
        
        setContentView(R.layout.main);
         
        WeatherManager.getInstance().updateForecast();
        Forecast clima = WeatherManager.getInstance().getWeatherForecast();
        
   }


	public void showWeather(View v){
	}
	
	public void showBattery(View v){
	}
	
	public void sendMail(View v){
	}

}