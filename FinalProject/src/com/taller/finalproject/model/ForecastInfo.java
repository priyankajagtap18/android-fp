package com.taller.finalproject.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

public class ForecastInfo {
	private Date mDate;
	private String mCondition;
	private double mMaxCTemp;
	private double mMaxFTemp;
	private double mMinCTemp;
	private double mMinFTemp;
	private String mWindDirection;
	private double mWindSpeed;
	private String mIconUrl;
	private Bitmap mIconBitmap;

	public ForecastInfo(){
		
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

	public void setMinCTemp(double mMinCTemp) {
		this.mMinCTemp = mMinCTemp;
	}

	public double getMinCTemp() {
		return mMinCTemp;
	}

	public void setMaxCTemp(double mMaxCTemp) {
		this.mMaxCTemp = mMaxCTemp;
	}

	public double getMaxCTemp() {
		return mMaxCTemp;
	}

	public void setMaxFTemp(double mMaxFTemp) {
		this.mMaxFTemp = mMaxFTemp;
	}

	public double getMaxFTemp() {
		return mMaxFTemp;
	}

	public void setMinFTemp(double mMinFTemp) {
		this.mMinFTemp = mMinFTemp;
	}

	public double getMinFTemp() {
		return mMinFTemp;
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
	
