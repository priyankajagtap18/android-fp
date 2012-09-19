package com.taller.finalproject.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class WeatherService extends IntentService {

	public WeatherService() {
		super("WeatherService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.i("AndroidFinalProject", "Se ejecutó el intent service");
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}
	
	
}
