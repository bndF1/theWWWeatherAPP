package bussiness;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class HandleXML {

	private String country = "county";
	private String temperature = "temperature";
	private String temp_min = "min", temp_max = "max";
	private String humidity = "humidity";
	private String pressure = "pressure";
	private String cityId="id";
	private String cityName = "name";
	private String longitude = "lat", latitude = "lon";
	private String sunrise = "rise", sunset = "set";
	private String wind_name = "name", wind_direction = "name";
	private String clouds_descrp = "name";
	private String precipitation = "mode";
	private String waeather_value = "value";
	private String weather_icon = "icon";
	private String error = "Please check you have entered a correct location :-P";
	private String urlString = null;
	private XmlPullParserFactory xmlFactoryObject;
	private CityWeather cityWeather = null;
	private List<CityWeather> cityWeatherList = new ArrayList<CityWeather>();
	public volatile boolean parsingComplete = true;

	public HandleXML(String url) {
		this.urlString = url;
	}

	public String getCountry() {
		return country;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public String getPressure() {
		return pressure;
	}

	public void parseXMLAndStoreIt(XmlPullParser myParser) {
		int event;
		String text = null;
		try {
			event = myParser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				String qName = myParser.getName();
				switch (event) {
				case XmlPullParser.START_TAG:
					if (qName.equals("city")) {
						cityName = myParser.getAttributeValue(null, "name");
						cityId = myParser.getAttributeValue(null, "id");
						cityWeather = new CityWeather();
						cityWeather.setCityName(cityName);
						cityWeather.setCityId(Integer.parseInt(cityId));
					}
					break;
				case XmlPullParser.TEXT:
					text = myParser.getText();
					break;

				case XmlPullParser.END_TAG:
					if (qName.equals("country")) {
						country = text;
						cityWeather.setCountry(country);
					} else if (qName.equals("humidity")) {
						humidity = myParser.getAttributeValue(null, "value");
						cityWeather.setHimidity(Integer.parseInt(humidity));
					} else if (qName.equals("pressure")) {
						pressure = myParser.getAttributeValue(null, "value");
						cityWeather.setPressure(Float.parseFloat(pressure));
					} else if (qName.equals("temperature")) {
						temperature = myParser.getAttributeValue(null, "value");
						temp_min = myParser.getAttributeValue(null, "min");
						temp_max = myParser.getAttributeValue(null, "max");
						cityWeather.setTemperature(Float
								.parseFloat(temperature));
						cityWeather.setTemp_min(Float.parseFloat(temp_min));
						cityWeather.setTemp_max(Float.parseFloat(temp_max));

					} else if (qName.equalsIgnoreCase("speed")) {
						wind_name = myParser.getAttributeValue(null, "name");
						cityWeather.setWind_descrp(wind_name);
					} else if (qName.equalsIgnoreCase("direction")) {
						wind_direction = myParser.getAttributeValue(null,
								"name");
						cityWeather.setWind_direction(wind_direction);

					} else if (qName.equalsIgnoreCase("clouds")) {
						clouds_descrp = myParser
								.getAttributeValue(null, "name");
						cityWeather.setClouds_descrp(clouds_descrp);
					} else if (qName.equalsIgnoreCase("precipitation")) {
						precipitation = myParser
								.getAttributeValue(null, "mode");
						if (precipitation.equals("no"))
							cityWeather.setPrecipitation(false);
						else
							cityWeather.setPrecipitation(true);
					} else if (qName.equalsIgnoreCase("weather")) {
						waeather_value = myParser.getAttributeValue(null,
								"value");
						cityWeather.setWaeather_value(waeather_value);
						weather_icon = myParser.getAttributeValue(null, "icon");
						if (weather_icon.equalsIgnoreCase("01d")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/32.png");

						} else if (weather_icon.equalsIgnoreCase("01n")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/31.png");

						} else if (weather_icon.equalsIgnoreCase("02d")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/28.png");

						} else if (weather_icon.equalsIgnoreCase("02n")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/27.png");

						} else if (weather_icon.equalsIgnoreCase("03d")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/26.png");

						} else if (weather_icon.equalsIgnoreCase("03n")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/26.png");

						} else if (weather_icon.equalsIgnoreCase("04d")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/26.png");

						} else if (weather_icon.equalsIgnoreCase("04n")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/26.png");

						} else if (weather_icon.equalsIgnoreCase("09d")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/40.png");

						} else if (weather_icon.equalsIgnoreCase("09n")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/40.png");

						} else if (weather_icon.equalsIgnoreCase("10d")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/39.png");

						} else if (weather_icon.equalsIgnoreCase("10n")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/45.png");

						} else if (weather_icon.equalsIgnoreCase("11d")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/38.png");

						} else if (weather_icon.equalsIgnoreCase("11n")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/38.png");

						} else if (weather_icon.equalsIgnoreCase("13d")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/41.png");

						} else if (weather_icon.equalsIgnoreCase("13n")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/41.png");

						} else if (weather_icon.equalsIgnoreCase("50d")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/na.png");

						} else if (weather_icon.equalsIgnoreCase("50n")) {
							cityWeather
									.setWeather_icon("https://dl.dropboxusercontent.com/u/22185675/ICONS/128/na.png");

						}
						// cityWeather.setWeather_icon("http://openweathermap.org/img/w/"+weather_icon);
					} else if (qName.equalsIgnoreCase("item")) {
						// System.out.println(cityWeather.toString());
						// adding a cityWeather to the list
						cityWeatherList.add(cityWeather);
						// System.out.println(getWeatherInfo());
						// System.out.println("==");
					}
					break;
				default:
					break;
				}
				event = myParser.next();
			}
			parsingComplete = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchXML() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					URL url = new URL(urlString);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setReadTimeout(10000 /* milliseconds */);
					conn.setConnectTimeout(15000 /* milliseconds */);
					conn.setRequestMethod("GET");
					conn.setDoInput(true);
					conn.connect();
					InputStream stream = conn.getInputStream();

					xmlFactoryObject = XmlPullParserFactory.newInstance();
					XmlPullParser myparser = xmlFactoryObject.newPullParser();

					myparser.setFeature(
							XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
					myparser.setInput(stream, null);
					parseXMLAndStoreIt(myparser);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		thread.start();

	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

	public String getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(String temp_min) {
		this.temp_min = temp_min;
	}

	public String getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(String temp_max) {
		this.temp_max = temp_max;
	}

	public String getWind_name() {
		return wind_name;
	}

	public void setWind_name(String wind_name) {
		this.wind_name = wind_name;
	}

	public String getWind_direction() {
		return wind_direction;
	}

	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
	}

	public String getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}

	public String getWaeather_value() {
		return waeather_value;
	}

	public void setWaeather_value(String waeather_value) {
		this.waeather_value = waeather_value;
	}

	public String getClouds_descrp() {
		return clouds_descrp;
	}

	public void setClouds_descrp(String clouds_descrp) {
		this.clouds_descrp = clouds_descrp;
	}

	@Override
	public String toString() {
		String inf = "HandleXML [country=" + country + ", temperature="
				+ temperature + ", temp_min=" + temp_min + ", temp_max="
				+ temp_max + ", humidity=" + humidity + ", pressure="
				+ pressure + ", cityName=" + cityName + ", wind_name="
				+ wind_name + ", wind_direction=" + wind_direction
				+ ", clouds_descrp=" + clouds_descrp + ", precipitation="
				+ precipitation + ", waeather_value=" + waeather_value + "]";
		return inf;
	}

	public List<CityWeather> getWeatherInfo() {
		return cityWeatherList;
	}

	public String getWeather_icon() {
		return weather_icon;
	}

	public void setWeather_icon(String weather_icon) {
		this.weather_icon = weather_icon;
	}
}