package com.taller.finalproject.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.taller.finalproject.ActionController;
import com.taller.finalproject.ActionController.Action;
import com.taller.finalproject.IActionListener;
import com.taller.finalproject.R;
import com.taller.finalproject.model.BatteryLogManager;
import com.taller.finalproject.receivers.BatteryLogReceiver;
import com.taller.finalproject.ui.adapters.BatteryLogAdapter;

public class ActBatteryLog extends Activity implements IActionListener{
	
	public static final int NEW_BATTERY_LOG_NOTIFICATION = 0;
	private ListView lstBatteryLog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.lay_battery_log);
		
		lstBatteryLog = (ListView) findViewById(R.id.lstBatteryLog);
		lstBatteryLog.setAdapter(new BatteryLogAdapter(this, BatteryLogManager.getInstance().getBatteryLog()));
		
		ActionController.getInstance().registerListener(this);
		
		BatteryLogReceiver.notification.number = 0;
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		ActionController.getInstance().unregisterListener(this);
	}
	
	public void notifyAction(Action action) {
		switch (action) {
		case NEW_BATTERY_LOG:
		
			Toast.makeText(this, "New battery log", Toast.LENGTH_SHORT).show();
			
			lstBatteryLog.setAdapter(new BatteryLogAdapter(this, BatteryLogManager.getInstance().getBatteryLog()));
			break;

		
		}
		
	}
	
	
}
