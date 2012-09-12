package com.taller.finalproject.model;

import java.util.Calendar;
import java.util.Date;


public class WeatherManager {
	
	private static double DEFAULT_LATITUDE = -31.423239;
	private static double DEFAULT_LONGITUDE = -64.186824;
	private static String DEFAULT_CITY = "Cordoba,Cordoba";
	
	// Singleton instance
	private static WeatherManager mInstance; //
	
	private Date mLastUdpate;
	private Forecast mWeatherForecast;
	private WeatherProvider mWeatherProvider;
	
	private WeatherManager(){
		//January 1, 1970, 00:00:00 GMT 
		mLastUdpate = new Date(0);
		mWeatherProvider = new WorldWeatherOnline();
	}
	
	public static WeatherManager getInstance(){
		if (mInstance == null)
			mInstance = new WeatherManager();
		
		return mInstance;
	}
		
	public void updateForecast(){
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(mLastUdpate); // sets calendar time/date
	    cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
	    		    		
	    Date expireDate = cal.getTime();
	    Date now = new Date();
	    
		if (now.after(expireDate)){
			mWeatherForecast = mWeatherProvider.requestWeatherForecast(DEFAULT_CITY, 5);
			mLastUdpate = now;
		}
	}

	public void setWeatherForecast(Forecast mWeatherForecast) {
		this.mWeatherForecast = mWeatherForecast;
	}

	public Forecast getWeatherForecast() {
		return mWeatherForecast;
	}
}
