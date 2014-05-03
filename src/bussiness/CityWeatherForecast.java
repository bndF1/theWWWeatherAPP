package bussiness;


public class CityWeatherForecast {
	
	private CityWeather cityWeather;
	private String time, timeFrom, timeTo, symbolName, symbolVar, windDirectionName, windName, cloudsValue;
	
	private float precipitation;

	private float windSpeed;
	private float temperatureDay, temp_max, temp_min, temp_eve, temp_morn, temp_nigth;

	private float pressure;
	private int humidity;
	private int cloudsAll;
	public CityWeather getCityWeather() {
		return cityWeather;
	}
	public void setCityWeather(CityWeather cityWeather) {
		this.cityWeather = cityWeather;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSymbolName() {
		return symbolName;
	}
	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}
	public String getSymbolVar() {
		return symbolVar;
	}
	public void setSymbolVar(String symbolVar) {
		this.symbolVar = symbolVar;
	}
	public String getWindDirectionName() {
		return windDirectionName;
	}
	public void setWindDirectionName(String windDirectionName) {
		this.windDirectionName = windDirectionName;
	}
	public String getWindName() {
		return windName;
	}
	public void setWindName(String windName) {
		this.windName = windName;
	}
	public String getCloudsValue() {
		return cloudsValue;
	}
	public void setCloudsValue(String cloudsValue) {
		this.cloudsValue = cloudsValue;
	}
	public float getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(float precipitationList) {
		this.precipitation = precipitationList;
	}
	public float getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}
	public float getTemperatureDay() {
		return temperatureDay;
	}
	public void setTemperatureDay(float temperatureDay) {
		this.temperatureDay = temperatureDay;
	}
	public float getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(float temp_max) {
		this.temp_max = temp_max;
	}
	public float getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(float temp_min) {
		this.temp_min = temp_min;
	}
	public float getTemp_eve() {
		return temp_eve;
	}
	public void setTemp_eve(float temp_eve) {
		this.temp_eve = temp_eve;
	}
	public float getTemp_morn() {
		return temp_morn;
	}
	public void setTemp_morn(float temp_morn) {
		this.temp_morn = temp_morn;
	}
	public float getTemp_nigth() {
		return temp_nigth;
	}
	public void setTemp_nigth(float temp_nigth) {
		this.temp_nigth = temp_nigth;
	}
	public float getPressure() {
		return pressure;
	}
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public int getCloudsAll() {
		return cloudsAll;
	}
	public void setCloudsAll(int cloudsAllList) {
		this.cloudsAll = cloudsAllList;
	}
	@Override
	public String toString() {
		return "CityWeatherForecast [cityWeather=" + cityWeather + ", time="
				+ time + ", symbolName=" + symbolName + ", symbolVar="
				+ symbolVar + ", windDirectionName=" + windDirectionName
				+ ", windName=" + windName + ", cloudsValue=" + cloudsValue
				+ ", precipitation=" + precipitation + ", windSpeed="
				+ windSpeed + ", temperatureDay=" + temperatureDay
				+ ", temp_max=" + temp_max + ", temp_min=" + temp_min
				+ ", temp_eve=" + temp_eve + ", temp_morn=" + temp_morn
				+ ", temp_nigth=" + temp_nigth + ", pressure=" + pressure
				+ ", humidity=" + humidity + ", cloudsAll=" + cloudsAll + "]";
	}
	public String getTimeTo() {
		return timeTo;
	}
	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	public String getTimeFrom() {
		return timeFrom;
	}
	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}
}