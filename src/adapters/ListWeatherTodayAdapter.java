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
import bussiness.CityWeather;

import com.app.thewwweather.R;

public class ListWeatherTodayAdapter extends BaseAdapter {

	Activity context;
	ArrayList<CityWeather> datos;
    private final ImageDownloader imageDownloader = new ImageDownloader();


	public ListWeatherTodayAdapter(Activity context,
			ArrayList<CityWeather> info) {
		this.context = context;
		this.datos = info;
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
				com.app.thewwweather.R.layout.list_weather_today_2, null);
		CityWeather info = datos.get(position);
		ImageView i = (ImageView) item.findViewById(R.id.WeatherIcon);
		
		imageDownloader.download(info.getWeather_icon(), i);

		TextView textViewCCT = (TextView) item.findViewById(R.id.textViewCCT);

		textViewCCT.setText(info.getCityName() + ", " + info.getCountry()
				+ ", " + info.getTemperature() + "¼");

		TextView textViewTminTmax_WC = (TextView) item
				.findViewById(R.id.textViewTminTmax_WC);
		textViewTminTmax_WC.setText("Min: " + info.getTemp_min() + "¼, Max: "
				+ info.getTemp_max() + "¼ with " + info.getWind_descrp()
				+ " and " + info.getWaeather_value() + ".");

		// TextView textViewPHP = (TextView)item.findViewById(R.id.textViewPHP);
		// textViewPHP.setText(" and "+info.getWaeather_value());

		return (item);
	}

}
