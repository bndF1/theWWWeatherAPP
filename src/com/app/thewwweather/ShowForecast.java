package com.app.thewwweather;

import java.util.ArrayList;
import java.util.List;

import adapters.ListWeatherForecastAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import bussiness.CityWeatherForecast;
import bussiness.HandleDialyXMLForecast;

public class ShowForecast extends Activity {

	private HandleDialyXMLForecast obj;
	private ListView listViewWeatherForecast;
	private ListWeatherForecastAdapter listWeatherForecastAdapter;
	private String cityName=null;
	private String countryCode=null;
	private int cityId=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_forecast);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitNetwork().build();
		StrictMode.setThreadPolicy(policy);
		listViewWeatherForecast = (ListView) findViewById(R.id.listViewWeatherForecast);
		setCityName(getIntent().getExtras().getString("cityName"));
		setCountryCode(getIntent().getExtras().getString("countryCode"));
		setCityId(getIntent().getExtras().getInt("cityId"));
		setTitle(getCityName()+", "+getCountryCode()+ " forecast");
		
		showInfoLocation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_forecast, menu);
		return true;
	}

	public void showInfoLocation() {

		String url = "http://api.openweathermap.org/data/2.5/forecast/daily?id="
				+ getCityId() + "&mode=xml&units=metric&cnt=7";

		obj = new HandleDialyXMLForecast(url);
		obj.fetchXML();
		while (obj.parsingComplete) {
		}

		List<CityWeatherForecast> info = new ArrayList<CityWeatherForecast>();
		info = obj.getCityWeatherForecastList();
		listWeatherForecastAdapter = new ListWeatherForecastAdapter(this,
				(ArrayList<CityWeatherForecast>) info);
		listViewWeatherForecast.setAdapter(listWeatherForecastAdapter);
		listViewWeatherForecast.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				CityWeatherForecast c = (CityWeatherForecast) listViewWeatherForecast
						.getItemAtPosition(position);

				Intent i = new Intent(ShowForecast.this,
						ShowForecastDetails.class);
				i.putExtra("cityName", c.getCityWeather().getCityName());
				i.putExtra("countryCode", c.getCityWeather().getCountry());
				i.putExtra("cityId", getCityId());
				startActivity(i);

			}
		});
		/*
		 * List<CityWeather> info = new ArrayList<CityWeather>(); info =
		 * obj.getWeatherInfo(); listWeatherTodayAdapter = new
		 * ListWeatherTodayAdapter(this, (ArrayList<CityWeather>) info);
		 * listViewWeatherToday.setAdapter(listWeatherTodayAdapter);
		 * listViewWeatherToday .setOnItemLongClickListener(new
		 * OnItemLongClickListener() {
		 * 
		 * @Override public boolean onItemLongClick(AdapterView<?> parent, View
		 * view, int position, long id) { CustomDialog c = new
		 * CustomDialog(position); c.show(getFragmentManager(), "mytag"); //
		 * Return true to consume the click event. In this // case the //
		 * onListItemClick listener is not called anymore. return true; } }); }
		 */
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
}
