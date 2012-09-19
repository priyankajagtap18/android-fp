package com.taller.finalproject;

import com.taller.finalproject.receivers.BatteryLogReceiver;
import com.taller.finalproject.ui.ActBatteryLog;
import com.taller.finalproject.ui.ActForecastList;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FinalProjectActivity extends Activity {

	private BatteryLogReceiver mBatteryReceiver;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("AndroidFinalProject", "Main activity created");
        
        setContentView(R.layout.main);
                
        mBatteryReceiver = new BatteryLogReceiver();
    	registerReceiver(mBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		unregisterReceiver(mBatteryReceiver);
	}

	public void showWeather(View v){
		
		Intent intent = new Intent(this, ActForecastList.class);
		startActivity(intent);
		
	}
	
	public void showBattery(View v){
		
		Intent intent = new Intent(this, ActBatteryLog.class);
		startActivity(intent);
		
	}
	

}