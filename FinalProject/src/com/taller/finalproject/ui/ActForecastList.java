package com.taller.finalproject.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.taller.finalproject.R;
import com.taller.finalproject.model.Forecast;
import com.taller.finalproject.model.WeatherManager;
import com.taller.finalproject.ui.adapters.ForecastListAdapter;

public class ActForecastList extends Activity {
	
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
		
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mProgress = new ProgressDialog(this);
        mProgress.setCancelable(false);
        mProgress.setMessage("Getting weather data...");
        
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
        

        if (WeatherManager.getInstance().getForecastData() == null)
        	new WeatherRequestTask().execute();
        else{
        	updateUI();
        }
        	
        lstForecast.setOnItemClickListener(new OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		Intent intent = new Intent(ActForecastList.this, ActForecastDetails.class); 
        		intent.putExtra("position", position);
        		ActForecastList.this.startActivity(intent);
        	}
		});
        
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		
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

	private class WeatherRequestTask extends AsyncTask<Void, Void, Forecast>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgress.show();
		}
		
		@Override
		protected Forecast doInBackground(Void... arg0) {

	        WeatherManager.getInstance().updateForecast();
	        
			return WeatherManager.getInstance().getForecastData();
		}
		
		@Override
		protected void onPostExecute(Forecast result) {
			super.onPostExecute(result);
			
			updateUI();
			
			mProgress.dismiss();
		}
		
	}
	
}
