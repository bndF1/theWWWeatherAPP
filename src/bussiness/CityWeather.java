package bussiness;


public class CityWeather {
	private int cityId;
	private String cityName, country;
	private String longitude, latitude;
	private String sunrise, sunset;


	private float temperature, temp_min, temp_max;

	private int himidity;
	private float pressure;

	private float wind_speed;
	private String wind_descrp, wind_direction;

	private int clouds_value;
	private String clouds_descrp;

	private boolean precipitation;

	private int weather_number;
	private String waeather_value;
	private String weather_icon;
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(float temp_min) {
		this.temp_min = temp_min;
	}
	public float getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(float temp_max) {
		this.temp_max = temp_max;
	}
	public int getHimidity() {
		return himidity;
	}
	public void setHimidity(int himidity) {
		this.himidity = himidity;
	}
	public float getPressure() {
		return pressure;
	}
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
	public float getWind_speed() {
		return wind_speed;
	}
	public void setWind_speed(float wind_speed) {
		this.wind_speed = wind_speed;
	}
	public String getWind_descrp() {
		return wind_descrp;
	}
	public void setWind_descrp(String wind_descrp) {
		this.wind_descrp = wind_descrp;
	}
	public String getWind_direction() {
		return wind_direction;
	}
	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
	}
	public int getClouds_value() {
		return clouds_value;
	}
	public void setClouds_value(int clouds_value) {
		this.clouds_value = clouds_value;
	}
	public String getClouds_descrp() {
		return clouds_descrp;
	}
	public void setClouds_descrp(String clouds_descrp) {
		this.clouds_descrp = clouds_descrp;
	}
	public boolean isPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(boolean precipitation) {
		this.precipitation = precipitation;
	}
	public int getWeather_number() {
		return weather_number;
	}
	public void setWeather_number(int weather_number) {
		this.weather_number = weather_number;
	}
	public String getWaeather_value() {
		return waeather_value;
	}
	public void setWaeather_value(String waeather_value) {
		this.waeather_value = waeather_value;
	}
	@Override
	public String toString() {
		return "CityWeather [cityName=" + cityName + ", country=" + country
				+ ", temperature=" + temperature + ", temp_min=" + temp_min
				+ ", temp_max=" + temp_max + ", himidity=" + himidity
				+ ", pressure=" + pressure + ", wind_descrp=" + wind_descrp
				+ ", wind_direction=" + wind_direction + ", clouds_descrp="
				+ clouds_descrp + ", precipitation=" + precipitation
				+ ", waeather_value=" + waeather_value + "]";
	}
	public String getWeather_icon() {
		return weather_icon;
	}
	public void setWeather_icon(String weather_icon) {
		this.weather_icon = weather_icon;
	}
	
	

}
