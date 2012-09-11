package com.taller.finalproject.model;

import java.util.Date;

public class Weather {
	
	private Date mDate;
	private String mCondition;
	private float mCelciusTemp;
	private float mFarenheitTemp;
	private String mWindDirection;
	private float mWindSpeed;
	
	public Weather(Date date, String condition, float celsiusTemp, float farenheitTemp, String windDirection, float windSpeed)
	{		
		mDate = date;
		mCondition = condition;
		mCelciusTemp = celsiusTemp;
		mFarenheitTemp = farenheitTemp;
		mWindDirection = windDirection;
		mWindSpeed = windSpeed;
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
	public void setCelciusTemp(float mCelciusTemp) {
		this.mCelciusTemp = mCelciusTemp;
	}
	public float getCelciusTemp() {
		return mCelciusTemp;
	}
	public void setFarenheitTemp(float mFarenheitTemp) {
		this.mFarenheitTemp = mFarenheitTemp;
	}
	public float getFarenheitTemp() {
		return mFarenheitTemp;
	}
	public void setWindDirection(String mWindDirection) {
		this.mWindDirection = mWindDirection;
	}
	public String getWindDirection() {
		return mWindDirection;
	}
	public void setWindSpeed(float mWindSpeed) {
		this.mWindSpeed = mWindSpeed;
	}
	public float getWindSpeed() {
		return mWindSpeed;
	}
	
}
