package com.taller.finalproject.model;

public class WeatherManager {
	
	private static WeatherManager mInstance; //
		
	private WeatherManager(){
					
	}
	
	public WeatherManager getInstance(){
		if (mInstance == null)
			mInstance = new WeatherManager();
		
		return mInstance;
	}
}
