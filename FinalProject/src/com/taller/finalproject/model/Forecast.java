package com.taller.finalproject.model;

import java.util.ArrayList;

public class Forecast {

	private WeatherInfo mCurrentWeather;
	private ArrayList<ForecastInfo> mForecastWeather;
	
	public Forecast(){
		mCurrentWeather = new WeatherInfo();
		mForecastWeather = new ArrayList<ForecastInfo>();	
	}

	public void setCurrentWeather(WeatherInfo currentWeather) {
		this.mCurrentWeather = currentWeather;
	}

	public WeatherInfo getCurrentWeather() {
		return mCurrentWeather;
	}

	public void setForecastWeather(ArrayList<ForecastInfo> forecastWeather) {
		this.mForecastWeather = forecastWeather;
	}

	public ArrayList<ForecastInfo> getForecastWeather() {
		return mForecastWeather;
	}
	
}
