package com.taller.finalproject;

import java.util.ArrayList;

public class ActionController {

	private ArrayList<IActionListener> mListeners;
	
	public enum Action{
		WEATHER_REQUEST_INITIATED,
		WEATHER_REQUEST_FINISHED,
		NEW_BATTERY_LOG
	}
	
	
	// Singleton instance
	private static ActionController mInstance; //
	
	private ActionController(){
		mListeners = new ArrayList<IActionListener>();
	}
	
	public static ActionController getInstance(){
		if (mInstance == null)
			mInstance = new ActionController();

		return mInstance;
	}
	
	public void registerListener(IActionListener listener){
		if (! mListeners.contains(listener))
			mListeners.add(listener);
	}
	
	public void unregisterListener(IActionListener listener){
		mListeners.remove(listener);
	}

	public void notifyAction(ActionController.Action action){
		for (IActionListener listener : mListeners) {
			listener.notifyAction(action);			
		}		
	}
	
}
