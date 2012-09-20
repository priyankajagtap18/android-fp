package com.taller.finalproject.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.taller.finalproject.ActionController;
import com.taller.finalproject.ActionController.Action;
import com.taller.finalproject.IActionListener;
import com.taller.finalproject.R;
import com.taller.finalproject.model.BatteryLogManager;
import com.taller.finalproject.receivers.BatteryLogReceiver;
import com.taller.finalproject.ui.adapters.BatteryLogAdapter;

public class ActBatteryLog extends Activity implements IActionListener{
	
	public static final int NEW_BATTERY_LOG_NOTIFICATION = 0;
	private static final int PICK_CONTACT = 0;
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
			//Toast.makeText(this, "New battery log", Toast.LENGTH_SHORT).show();	
			lstBatteryLog.setAdapter(new BatteryLogAdapter(this, BatteryLogManager.getInstance().getBatteryLog()));
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_battery_log, menu);
         
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mnSendMail:
			
			Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

			startActivityForResult(intent, PICK_CONTACT);
			break;

		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
	  super.onActivityResult(reqCode, resultCode, data);

	  switch (reqCode) {
	    case (PICK_CONTACT) :
	      if (resultCode == Activity.RESULT_OK) {
	        Uri contactData = data.getData();
	        Cursor contactCursorc =  managedQuery(contactData, null, null, null, null);
	        if (contactCursorc.moveToFirst()) {
	        	String contactID = contactCursorc.getString(contactCursorc.getColumnIndex(ContactsContract.Contacts._ID));
	        	
	        	sendEmail(contactID);        		
	        }
	      }
	      break;
	  }
	}
	
	private String[] getEmails(String contactID){
		ArrayList<String> contactEmails = new ArrayList<String>();
		
		ContentResolver cr = getContentResolver();
    	Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, 
 									null,
									ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?" , 
									new String[]{contactID}, null);
    	
    	while (emailCur.moveToNext())
    		contactEmails.add(emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
    	
    	emailCur.close();
    	return contactEmails.toArray(new String[contactEmails.size()]);
	}
	
	private void sendEmail(String contactID){
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_EMAIL, getEmails(contactID) );
		intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.mailSubject));
		intent.putExtra(Intent.EXTRA_TEXT, BatteryLogManager.getInstance().getStringSummary());
		startActivity(Intent.createChooser(intent, ""));
	}
}
