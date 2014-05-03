package adapters;

import java.util.ArrayList;

import utils.ImageDownloader;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import bussiness.CityWeatherForecast;

import com.app.thewwweather.R;

public class ListWeatherForecastAdapter extends BaseAdapter {

	Activity context;
	ArrayList<CityWeatherForecast> datos;
    private final ImageDownloader imageDownloader = new ImageDownloader();


	public ListWeatherForecastAdapter(Activity context,
			ArrayList<CityWeatherForecast> datos) {
		this.context = context;
		this.datos = datos;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View item = inflater.inflate(
				com.app.thewwweather.R.layout.list_weather_forecast, null);
		CityWeatherForecast info = datos.get(position);
		ImageView i = (ImageView) item.findViewById(R.id.WeatherIconForecast);
		
		imageDownloader.download(info.getSymbolVar(), i);

		TextView ForecastDayDate = (TextView) item.findViewById(R.id.ForecastDayDate);

		ForecastDayDate.setText(info.getTime()//+", "+info.getCityWeather().getCityName() + ", " + info.getCityWeather().getCountry()
				+ ", " + info.getTemperatureDay() + "¼");

		TextView ExpectedForecast = (TextView) item
				.findViewById(R.id.ExpectedForecast);
		ExpectedForecast.setText("Min: " + info.getTemp_min() + "¼, Max: "
				+ info.getTemp_max() + "¼ with " + info.getWindName()
				+ " and " + info.getSymbolName() + ".");

		// TextView textViewPHP = (TextView)item.findViewById(R.id.textViewPHP);
		// textViewPHP.setText(" and "+info.getWaeather_value());

		return (item);
	}

}

