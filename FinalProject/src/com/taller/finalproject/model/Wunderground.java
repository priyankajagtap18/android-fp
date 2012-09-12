package com.taller.finalproject.model;


public class Wunderground extends WeatherProvider {

	static final String API_KEY = "3388b5dbeb8fe22b";
	
	@Override
	public Forecast requestWeatherForecast(double lat, double lon, int numOfDays) {
		String URL = "http://api.wunderground.com/api/" + API_KEY + "/forecast10day/q/" + lat + "," + lon + ".json";

		return makeRequest(URL);
	}


	@Override
	public Forecast requestWeatherForecast(String city, int numOfDays) {
		String URL = "http://api.wunderground.com/api/" + API_KEY + "/forecast10day/q/" + city + ".json";

		return makeRequest(URL);
	}


	@Override
	protected Forecast parseResponse(String response) {
		// TODO Auto-generated method stub
		return null;
	}
}
