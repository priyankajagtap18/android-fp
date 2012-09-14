package com.taller.finalproject;

import com.taller.finalproject.model.Forecast;
import com.taller.finalproject.model.WeatherManager;
import com.taller.finalproject.ui.ActForecastList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FinalProjectActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("AndroidFinalProject", "Main activity created");
        
        setContentView(R.layout.main);
                
   }


	public void showWeather(View v){
		
		Intent intent = new Intent(this, ActForecastList.class);
		startActivity(intent);
		
	}
	
	public void showBattery(View v){
	}
	
	public void sendMail(View v){
	}

}