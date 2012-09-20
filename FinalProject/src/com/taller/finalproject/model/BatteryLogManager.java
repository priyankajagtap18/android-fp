package com.taller.finalproject.model;

import java.util.ArrayList;

public class BatteryLogManager {

	private ArrayList<BatteryLogItem> mBatteryLog;
	private static BatteryLogManager mInstance;
	
	private BatteryLogManager(){
		mBatteryLog = new ArrayList<BatteryLogItem>();		
	}
	
	public static BatteryLogManager getInstance(){
		if (mInstance == null)
			mInstance = new BatteryLogManager();
		
		return mInstance;
	}
	
	public void addBatteryLog(BatteryLogItem item) {
		this.mBatteryLog.add(item);
	}

	public ArrayList<BatteryLogItem> getBatteryLog() {
		return mBatteryLog;
	}
	
	public String getStringSummary(){
		String s = "";
		
		for (BatteryLogItem item : mBatteryLog) {
			s += item.getSummary() + "\n";
		}
		
		return s;
	}
	
}
