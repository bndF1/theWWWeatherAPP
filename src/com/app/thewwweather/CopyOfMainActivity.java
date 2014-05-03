package com.app.thewwweather;

import java.util.ArrayList;
import java.util.List;

import utils.ImageDownloader;
import adapters.CopyOfListWeatherTodayAdapter;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import bussiness.CityWeather;
import bussiness.HandleXML;

public class CopyOfMainActivity extends FragmentActivity {

	private String location = "";
	private EditText locationEdit;
	private HandleXML obj;
	private CityWeather cityWeather;
	private ListView listViewWeatherToday;
	private CopyOfListWeatherTodayAdapter listWeatherTodayAdapter;
    private final ImageDownloader imageDownloader = new ImageDownloader();
	private static final int NOTIF_ALERTA_ID = 0;
	private TextView city, temperature, temp_max, temp_min, weather_value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_webview);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitNetwork().build();
		StrictMode.setThreadPolicy(policy);
		listViewWeatherToday = (ListView) findViewById(R.id.listViewWeather2);
		city = (TextView) findViewById(R.id.CityName);
		temperature = (TextView) findViewById(R.id.temperatura);
		temp_max = (TextView) findViewById(R.id.textView2);
		temp_min = (TextView) findViewById(R.id.textView3);
		weather_value = (TextView) findViewById(R.id.textView4);
		loadSavedPreferences();

	}
	@Override
	public void onResume(){
		super.onResume();
		showNotification(getCityWeather());
	}
	private void loadSavedPreferences() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		String city = sp.getString("cityName", "city");
		String country = sp.getString("countryCode", "country");
		loadDefualtLocation(country, city);
	}

	private void savePreferences(String key, String value) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();
	}

	private void showNotification(CityWeather cityWeather) {
		ImageView largeIcon = new ImageView(this);
		imageDownloader.download(cityWeather.getWeather_icon(), largeIcon);
		Bitmap bitmap = ((BitmapDrawable) largeIcon.getDrawable()).getBitmap();
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				CopyOfMainActivity.this)
				.setSmallIcon(R.drawable.cloud).setOngoing(true)
				.setLargeIcon(bitmap)
				.setContentTitle(
						cityWeather.getCityName() + ", "
								+ cityWeather.getCountry())
				.setContentText(cityWeather.getTemperature()+"¼, "+cityWeather.getWaeather_value()).setTicker(" "+String.valueOf(cityWeather.getTemperature()+"¼ - ")+cityWeather.getCityName());

		Intent notIntent = new Intent(CopyOfMainActivity.this, CopyOfMainActivity.class);
		PendingIntent contIntent = PendingIntent.getActivity(CopyOfMainActivity.this,
				0, notIntent, 0);
		mBuilder.setContentIntent(contIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(NOTIF_ALERTA_ID, mBuilder.build());
		

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		if (null != searchView) {
			searchView.setSearchableInfo(searchManager
					.getSearchableInfo(getComponentName()));
			searchView.setIconifiedByDefault(false);
		}

		SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextChange(String newText) {
				// this is your adapter that will be filtered
				return true;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				// Here u can get the value "query" which is entered in the
				// search box.
				setLocation(query);

				showInfoLocation();

				return true;

			}
		};
		searchView.setOnQueryTextListener(queryTextListener);

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_search:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@SuppressLint("ValidFragment")
	public class CustomDialog extends DialogFragment {
		private int position;

		public CustomDialog(int position) {
			this.position = position;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			String[] items = { "Set as default",
					"Add to favourites [not available yet]", "7 days forecast", "View Details Forecast" };
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Select an option").setItems(items,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int option) {
							if (option == 0) {
								CityWeather c = (CityWeather) listViewWeatherToday
										.getItemAtPosition(position);
								savePreferences("countryCode", c.getCountry());
								savePreferences("cityName", c.getCityName());
							} else if (option == 2) {
								CityWeather c = (CityWeather) listViewWeatherToday
										.getItemAtPosition(position);

								Intent i = new Intent(CopyOfMainActivity.this,
										ShowForecast.class);
								i.putExtra("cityName", c.getCityName());
								i.putExtra("countryCode", c.getCountry());
								i.putExtra("cityId", c.getCityId());
								startActivity(i);
							}
							else if(option == 3){
								CityWeather c = (CityWeather) listViewWeatherToday
										.getItemAtPosition(position);

								Intent i = new Intent(CopyOfMainActivity.this,
										ShowForecastDetails.class);
								i.putExtra("cityName", c.getCityName());
								i.putExtra("countryCode", c.getCountry());
								i.putExtra("cityId", c.getCityId());
								startActivity(i);
							}
						}
					});
			return builder.create();
		}
	}

	public EditText getLocationEdit() {
		return locationEdit;
	}

	public void setLocationEdit(EditText locationEdit) {
		this.locationEdit = locationEdit;
	}

	public void showInfoLocation() {
		Toast.makeText(this, "Searching '" + getLocation() + "'",
				Toast.LENGTH_SHORT).show();
		if (getLocation().length() > 0) {
			String url = "http://api.openweathermap.org/data/2.5/find?q="
					+ getLocation() + "&type=like&units=metric&mode=xml";

			obj = new HandleXML(url);
			obj.fetchXML();
			while (obj.parsingComplete) {
			}

			List<CityWeather> info = new ArrayList<CityWeather>();
			info = obj.getWeatherInfo();
			listWeatherTodayAdapter = new CopyOfListWeatherTodayAdapter(this,
					(ArrayList<CityWeather>) info);
			listViewWeatherToday.setAdapter(listWeatherTodayAdapter);
			listViewWeatherToday
					.setOnItemLongClickListener(new OnItemLongClickListener() {
						@Override
						public boolean onItemLongClick(AdapterView<?> parent,
								View view, int position, long id) {
							CustomDialog c = new CustomDialog(position);
							c.show(getFragmentManager(), "mytag");
							// Return true to consume the click event. In this
							// case the
							// onListItemClick listener is not called anymore.
							return true;
						}
					});
			listViewWeatherToday
					.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							CityWeather c = (CityWeather) listViewWeatherToday
									.getItemAtPosition(position);

							Intent i = new Intent(CopyOfMainActivity.this,
									ShowForecast.class);
							i.putExtra("cityName", c.getCityName());
							i.putExtra("countryCode", c.getCountry());
							i.putExtra("cityId", c.getCityId());
							startActivity(i);

						}
					});
		}
	}

	public void loadDefualtLocation(String country, String city) {
		String url = "http://api.openweathermap.org/data/2.5/find?q=" + city
				+ "," + country + "&units=metric&mode=xml";
		obj = new HandleXML(url);
		obj.fetchXML();
		while (obj.parsingComplete) {
		}

		List<CityWeather> info = new ArrayList<CityWeather>();
		info = obj.getWeatherInfo();
		/*listWeatherTodayAdapter = new CopyOfListWeatherTodayAdapter(this,
				(ArrayList<CityWeather>) info);*/
		cityWeather = new CityWeather();
		cityWeather = info.get(0);
		this.city.setText(cityWeather.getCityName()+", "+cityWeather.getCountry());
		this.temperature.setText(cityWeather.getTemperature()+"¼");
		this.temp_max.setText("Maximum: "+String.valueOf(cityWeather.getTemp_max()));
		this.temp_min.setText("Minimum: "+String.valueOf(cityWeather.getTemp_min()));
		this.weather_value.setText(cityWeather.getClouds_descrp());
		
		
		/*listViewWeatherToday.setAdapter(listWeatherTodayAdapter);
		listViewWeatherToday.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				CityWeather c = (CityWeather) listViewWeatherToday
						.getItemAtPosition(position);

				Intent i = new Intent(CopyOfMainActivity.this, ShowForecast.class);
				i.putExtra("cityName", c.getCityName());
				i.putExtra("countryCode", c.getCountry());
				i.putExtra("cityId", c.getCityId());
				startActivity(i);

			}
		});*/
		showNotification(getCityWeather());
	}

	public CityWeather getCityWeather() {
		return cityWeather;
	}

	public void setCityWeather(CityWeather cityWeather) {
		this.cityWeather = cityWeather;
	}
}
