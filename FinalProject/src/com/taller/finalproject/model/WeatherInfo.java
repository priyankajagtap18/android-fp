package com.taller.finalproject.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class WeatherInfo {
	
	private Date mDate;
	private String mCondition;
	private double mCTemp;
	private double mFTemp;
	private double mHumidity;
	private String mWindDirection;
	private double mWindSpeed;
	private double mVisibility;
	private double mPressure;
	private String mIconUrl;
	private Bitmap mIconBitmap;
	
	public WeatherInfo(){
		
	}
		
	/* Getters and Setters */
	public void setDate(Date mDate) {
		this.mDate = mDate;
	}
	
	public Date getDate() {
		return mDate;
	}
	
	public void setCondition(String mCondition) {
		this.mCondition = mCondition;
	}
	
	public String getCondition() {
		return mCondition;
	}
	
	public void setCelciusTemp(double mCelciusTemp) {
		this.mCTemp = mCelciusTemp;
	}
	
	public double getCelciusTemp() {
		return mCTemp;
	}
	
	public void setFarenheitTemp(double mFarenheitTemp) {
		this.mFTemp = mFarenheitTemp;
	}
	
	public double getFarenheitTemp() {
		return mFTemp;
	}
	
	public void setWindDirection(String mWindDirection) {
		this.mWindDirection = mWindDirection;
	}
	
	public String getWindDirection() {
		return mWindDirection;
	}
	
	public void setWindSpeed(double mWindSpeed) {
		this.mWindSpeed = mWindSpeed;
	}
	
	public double getWindSpeed() {
		return mWindSpeed;
	}

	
	public void setHumidity(double mHumidity) {
		this.mHumidity = mHumidity;
	}

	public double getHumidity() {
		return mHumidity;
	}

	public void setVisibility(double mVisibility) {
		this.mVisibility = mVisibility;
	}

	public double getVisibility() {
		return mVisibility;
	}

	public void setPressure(double mPressure) {
		this.mPressure = mPressure;
	}

	public double getPressure() {
		return mPressure;
	}

	public void setIconUrl(String mIconUrl) {
		this.mIconUrl = mIconUrl;
		try {
	        URL url = new URL(mIconUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        this.mIconBitmap = BitmapFactory.decodeStream(input); 
	    } catch (IOException e) {
	        e.printStackTrace();

	    }
	}

	public String getIconUrl() {
		return mIconUrl;
	}
	
	public void setIconBitmap(Bitmap mIconBitmap) {
		this.mIconBitmap = mIconBitmap;
	}

	public Bitmap getIconBitmap() {
		return mIconBitmap;
	}
}
