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

public class ListWeatherForecastAdapterDetails extends BaseAdapter {

	Activity context;
	ArrayList<CityWeatherForecast> datos;
    private final ImageDownloader imageDownloader = new ImageDownloader();


	public ListWeatherForecastAdapterDetails(Activity context,
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
				com.app.thewwweather.R.layout.list_weather_forecast_details, null);
		CityWeatherForecast info = datos.get(position);
		ImageView i = (ImageView) item.findViewById(R.id.WeatherIconForecastDetails);
		
		imageDownloader.download(info.getSymbolVar(), i);

		TextView ForecastDayDate = (TextView) item.findViewById(R.id.ForecastTimeFromTo);

		
		String [] timeFrom = info.getTimeFrom().split("T");
		String[] timeTo = info.getTimeTo().split("T");
		ForecastDayDate.setText(timeFrom[1]+" - "+timeTo[1]//+", "+info.getCityWeather().getCityName() + ", " + info.getCityWeather().getCountry()
				+ ", " + info.getTemperatureDay() + "¼");

		TextView ExpectedForecast = (TextView) item
				.findViewById(R.id.ExpectedForecastDetails);
		ExpectedForecast.setText("Min: " + info.getTemp_min() + "¼, Max: "
				+ info.getTemp_max() + "¼ with " + info.getWindName()
				+ " and " + info.getSymbolName() + ".");

		// TextView textViewPHP = (TextView)item.findViewById(R.id.textViewPHP);
		// textViewPHP.setText(" and "+info.getWaeather_value());

		return (item);
	}

}

