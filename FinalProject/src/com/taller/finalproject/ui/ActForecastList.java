package com.taller.finalproject.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.SavedState;

import com.taller.finalproject.ActionController;
import com.taller.finalproject.ActionController.Action;
import com.taller.finalproject.IActionListener;
import com.taller.finalproject.R;
import com.taller.finalproject.model.Forecast;
import com.taller.finalproject.model.WeatherManager;
import com.taller.finalproject.ui.adapters.ForecastListAdapter;

public class ActForecastList extends Activity implements IActionListener{
	
	private static final int LOADING_DIALOG = 0;

	private ImageView imgCurrentWeather;
	
	private TextView txtTodayCondition;
	private TextView txtHumidity;
	private TextView txtPressure;
	private TextView txtVisibility;
	private TextView txtWindDirection;
	private TextView txtWindSpeed;
	private TextView txtTemp;
	
	private ProgressDialog mProgress;
	
	private ListView lstForecast;
	private LinearLayout screenLayout;
		
	private WeatherRequestTask requestTask;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                
        setContentView(R.layout.lay_forecast_list);
        
        imgCurrentWeather = (ImageView) findViewById(R.id.imgCurrentWeather);
        txtTodayCondition = (TextView) findViewById(R.id.txtTodayCondition);
        
        txtTemp = (TextView) findViewById(R.id.txtTemp);
        txtHumidity = (TextView) findViewById(R.id.txtHumidity);
        txtPressure = (TextView) findViewById(R.id.txtPressure);
        txtVisibility = (TextView) findViewById(R.id.txtVisibility);
        txtWindDirection = (TextView) findViewById(R.id.txtWindDirection);
        txtWindSpeed = (TextView) findViewById(R.id.txtWindSpeed);
        screenLayout = (LinearLayout) findViewById(R.id.screenLayout);
        lstForecast = (ListView) findViewById(R.id.lstForecast);
                	
        lstForecast.setOnItemClickListener(new OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		Intent intent = new Intent(ActForecastList.this, ActForecastDetails.class); 
        		intent.putExtra("position", position);
        		ActForecastList.this.startActivity(intent);
        	}
		});
        
        ActionController.getInstance().registerListener(this);
        
        requestWeatherData();

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		ActionController.getInstance().unregisterListener(this);
	}

	public void notifyAction(ActionController.Action action) {
		switch (action) {
		case WEATHER_REQUEST_INITIATED:
			showDialog(LOADING_DIALOG);
			break;

		case WEATHER_REQUEST_FINISHED:
			updateUI();
			
			removeDialog(LOADING_DIALOG);
			break;
		}
		
	}
	
	private void requestWeatherData(){
        requestTask = (WeatherRequestTask) getLastNonConfigurationInstance();
		
		if (requestTask == null){
			requestTask = new WeatherRequestTask();
		} 
				
		switch (requestTask.getStatus()) {
		case FINISHED:
			updateUI();
			break;

		case PENDING:
			if (WeatherManager.getInstance().getForecastData() == null)
				requestTask.execute();
			else 
				updateUI();
			break;
		}
	}
	
	@Override
	public WeatherRequestTask onRetainNonConfigurationInstance() {
		return requestTask;
	}
	
	protected void updateUI(){
		updateUICurrentWeatherInfo();
    	updateUIForecast();
    	screenLayout.setVisibility(View.VISIBLE);
	}
	
	
	protected void updateUICurrentWeatherInfo(){
		Forecast currentForecast = WeatherManager.getInstance().getForecastData();
	      
        imgCurrentWeather.setImageBitmap(currentForecast.getCurrentWeather().getIconBitmap());

        txtTodayCondition.setText(currentForecast.getCurrentWeather().getCondition());
        txtTemp.setText(Double.toString(currentForecast.getCurrentWeather().getCelciusTemp()) + "° C");
        txtHumidity.setText(Double.toString(currentForecast.getCurrentWeather().getHumidity()) + " %");
        txtPressure.setText(Double.toString(currentForecast.getCurrentWeather().getPressure()) + " Pa");
        txtVisibility.setText(Double.toString(currentForecast.getCurrentWeather().getVisibility()) + " Km");
        txtWindDirection.setText(currentForecast.getCurrentWeather().getWindDirection());
        txtWindSpeed.setText(Double.toString(currentForecast.getCurrentWeather().getWindSpeed()) + " Km/h");
		
	}
	
	
	protected void updateUIForecast(){
		lstForecast.setAdapter(new ForecastListAdapter(this, WeatherManager.getInstance().getForecastData().getForecastWeather()));
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_forecast_list, menu);
         
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mnSyncForecast:
			screenLayout.setVisibility(View.GONE);
			requestTask = new WeatherRequestTask();
			requestTask.execute();
			break;

		}
		return super.onOptionsItemSelected(item);
	}
		
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case LOADING_DIALOG:
			mProgress = new ProgressDialog(this);
	        mProgress.setCancelable(false);
	        mProgress.setMessage("Getting weather data...");
	        dialog = mProgress;
			break;

		}
		return dialog;
	}
	
	private class WeatherRequestTask extends AsyncTask<Void, Void, Forecast>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			ActionController.getInstance().notifyAction(ActionController.Action.WEATHER_REQUEST_INITIATED);
		}
		
		@Override
		protected Forecast doInBackground(Void... arg0) {

	        WeatherManager.getInstance().updateForecast();
	        
			return WeatherManager.getInstance().getForecastData();
		}
		
		@Override
		protected void onPostExecute(Forecast result) {
			super.onPostExecute(result);
			ActionController.getInstance().notifyAction(ActionController.Action.WEATHER_REQUEST_FINISHED);

		}
		
	}
	
}
