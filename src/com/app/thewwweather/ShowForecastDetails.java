package com.app.thewwweather;

import java.util.ArrayList;
import java.util.List;

import adapters.ListWeatherForecastAdapterDetails;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.widget.ListView;
import bussiness.CityWeatherForecast;
import bussiness.HandleDialyXMLForecastDetails;

public class ShowForecastDetails extends Activity {

	private HandleDialyXMLForecastDetails obj;
	private ListView listViewWeatherForecastDetails;
	private ListWeatherForecastAdapterDetails listWeatherForecastAdapterDetails;
	private String cityName = null;
	private String countryCode = null;
	private int cityId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_forecast_details);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitNetwork().build();
		StrictMode.setThreadPolicy(policy);
		listViewWeatherForecastDetails = (ListView) findViewById(R.id.listViewWeatherForecastDetails);

		setCityName(getIntent().getExtras().getString("cityName"));
		setCountryCode(getIntent().getExtras().getString("countryCode"));
		setCityId(getIntent().getExtras().getInt("cityId"));
		setTitle(getCityName()+", "+getCountryCode()+ " details");
		showInfoLocation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_forecast_details, menu);
		return true;
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
	public void showInfoLocation() {

		String url = "http://api.openweathermap.org/data/2.5/forecast?id="
				+ getCityId() + "&mode=xml&units=metric";

		obj = new HandleDialyXMLForecastDetails(url);
		obj.fetchXML();
		while (obj.parsingComplete) {
		}

		List<CityWeatherForecast> info = new ArrayList<CityWeatherForecast>();
		info = obj.getCityWeatherForecastList();
		listWeatherForecastAdapterDetails = new ListWeatherForecastAdapterDetails(this,
				(ArrayList<CityWeatherForecast>) info);
		listViewWeatherForecastDetails.setAdapter(listWeatherForecastAdapterDetails);
	}

}
