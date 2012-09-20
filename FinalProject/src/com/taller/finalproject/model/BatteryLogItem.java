package com.taller.finalproject.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BatteryLogItem {

	private Date mLogDate;
	private int mLevel;
	private int mMaxLevel;

	public BatteryLogItem(){
		setLogDate(new Date());
		setLevel(0);		
		setMaxLevel(0);
	}

	public void setLogDate(Date mLogDate) {
		this.mLogDate = mLogDate;
	}

	public Date getLogDate() {
		return mLogDate;
	}

	public void setLevel(int mLevel) {
		this.mLevel = mLevel;
	}

	public int getLevel() {
		return mLevel;
	}

	public void setMaxLevel(int mMaxLevel) {
		this.mMaxLevel = mMaxLevel;
	}

	public int getMaxLevel() {
		return mMaxLevel;
	}
	
	public String getSummary(){
		String s = ""; 
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
		s = sdf.format(this.getLogDate()) + "\t\t\t\t";
		s += this.getLevel() + "/" + this.getMaxLevel() + "\t\t\t\t";
		int pctLevel = ((this.getLevel() * 100 )/this.getMaxLevel());
		s += pctLevel + "%";
		
		return s;
		
		
	}
	

}
