package com.taller.finalproject.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class WorldWeatherOnline extends WeatherProvider {

	static final String API_KEY = "719340e065134036121109";
	
	@Override
	public Forecast requestWeatherForecast(double lat, double lon,
			int numOfDays) {
		
		String URL = "http://free.worldweatheronline.com/feed/weather.ashx?q=" + lat + "," + lon + "&format=json&num_of_days=" + numOfDays + "&key=" + API_KEY;

		return makeRequest(URL);
	}

	@Override
	public Forecast requestWeatherForecast(String city, int numOfDays) {
		String URL = "http://free.worldweatheronline.com/feed/weather.ashx?q=" + city + "&format=json&num_of_days=" + numOfDays + "&key=" + API_KEY;

		return makeRequest(URL);
	}

	@Override
	protected Forecast parseResponse(String response) throws JSONException {
		Forecast parsedForecast = new Forecast();
		JSONObject jResponse = new JSONObject(response).getJSONObject("data");
		
		JSONObject jCurrentCondition = jResponse.getJSONArray("current_condition").getJSONObject(0);
		WeatherInfo currentWeather = new WeatherInfo();
		currentWeather.setCondition(jCurrentCondition.getJSONArray("weatherDesc").getJSONObject(0).getString("value"));
		currentWeather.setCelciusTemp(jCurrentCondition.getDouble("temp_C"));
		currentWeather.setDate(new Date());
		currentWeather.setFarenheitTemp(jCurrentCondition.getDouble("temp_F"));
		currentWeather.setHumidity(jCurrentCondition.getDouble("humidity"));
		currentWeather.setIconUrl(jCurrentCondition.getJSONArray("weatherIconUrl").getJSONObject(0).getString("value"));
		currentWeather.setPressure(jCurrentCondition.getDouble("pressure"));
		currentWeather.setVisibility(jCurrentCondition.getDouble("visibility"));
		currentWeather.setWindDirection(jCurrentCondition.getString("winddir16Point"));
		currentWeather.setWindSpeed(jCurrentCondition.getDouble("windspeedKmph"));
		
		JSONArray jForecastArray = jResponse.getJSONArray("weather");
		ArrayList<ForecastInfo> forecastList = new ArrayList<ForecastInfo>();
		for (int i = 0; i < jForecastArray.length(); i++) {
		    JSONObject jForecastInfo = jForecastArray.getJSONObject(i);
		    ForecastInfo forecastItem = new ForecastInfo();
		    forecastItem.setCondition(jForecastInfo.getJSONArray("weatherDesc").getJSONObject(0).getString("value"));
		    forecastItem.setIconUrl(jForecastInfo.getJSONArray("weatherIconUrl").getJSONObject(0).getString("value"));
		    forecastItem.setMaxCTemp(jForecastInfo.getDouble("tempMaxC"));
		    forecastItem.setMaxFTemp(jForecastInfo.getDouble("tempMaxF"));
		    forecastItem.setMinCTemp(jForecastInfo.getDouble("tempMinC"));
		    forecastItem.setMinFTemp(jForecastInfo.getDouble("tempMinF"));
		    forecastItem.setWindDirection(jForecastInfo.getString("winddirection"));
		    forecastItem.setWindSpeed(jForecastInfo.getDouble("windspeedKmph"));
		    
		    //Date parsing
		    try {
			    String strDate = jForecastInfo.getString("date");
			    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
				forecastItem.setDate(formater.parse(strDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		    forecastList.add(forecastItem);
		}
		
		parsedForecast.setCurrentWeather(currentWeather);
		parsedForecast.setForecastWeather(forecastList);
		
		return parsedForecast;
	}

}
