package com.taller.finalproject.model;

import java.io.ByteArrayOutputStream;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;


public abstract class WeatherProvider {

	public abstract Forecast requestWeatherForecast(double lat, double lon, int numOfDays);
	
	public abstract Forecast requestWeatherForecast(String city, int numOfDays);
	
	protected abstract Forecast parseResponse(String response) throws JSONException;
	
	protected Forecast makeRequest(String URL){
		Forecast forecast = new Forecast();
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = null;
		try {
			response = httpclient.execute(new HttpGet(URL));
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				String responseString = out.toString();
				forecast = parseResponse(responseString);
			} else {
				//Utility.toast("Request of weather forecast failed ! HTTP status code: " + statusLine.getStatusCode());
				response.getEntity().getContent().close();
			}
		} catch (Exception e) {
			//Utility.toast(e.toString());
			e.printStackTrace();
		}
		return forecast;		
	}
}
