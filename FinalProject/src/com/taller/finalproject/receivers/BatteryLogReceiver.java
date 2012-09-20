package com.taller.finalproject.receivers;

import java.util.Date;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.taller.finalproject.ActionController;
import com.taller.finalproject.R;
import com.taller.finalproject.model.BatteryLogItem;
import com.taller.finalproject.model.BatteryLogManager;
import com.taller.finalproject.ui.ActBatteryLog;

public class BatteryLogReceiver extends BroadcastReceiver {

	public static final int NEW_BATTERY_LOG_NOTIFICATION = 0;
	public static Notification notification = null;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		BatteryLogItem item = new BatteryLogItem();
		int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
		item.setLevel(level);
		item.setMaxLevel(scale);
		item.setLogDate(new Date());

		BatteryLogManager.getInstance().addBatteryLog(item);
		
		CharSequence title = "New battery log";
		
		if (notification == null){		
			notification = new Notification(R.drawable.application_icon, title , System.currentTimeMillis());		

			notification.flags |= Notification.FLAG_AUTO_CANCEL;					
		}
			
		notification.number += 1;	 
		
		if (notification.number > 1)
			title = "("+ notification.number +") " + title;
		
		Intent notificationIntent = new Intent(context, ActBatteryLog.class);
		PendingIntent contIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);		
		notification.setLatestEventInfo(context, title, "a new battery event occurred", contIntent);
		
		NotificationManager notificatinoManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificatinoManager.notify(NEW_BATTERY_LOG_NOTIFICATION, notification);
		
		ActionController.getInstance().notifyAction(ActionController.Action.NEW_BATTERY_LOG);
	}

}
