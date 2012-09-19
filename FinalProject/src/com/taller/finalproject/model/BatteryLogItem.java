package com.taller.finalproject.model;

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

}
