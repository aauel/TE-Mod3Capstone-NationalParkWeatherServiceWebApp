package com.techelevator.model;

import java.util.HashMap;
import java.util.Map;

public class Weather {
	
	private String parkCode;        // Four letter code in uppercase
	private int forecastDayNumber;  // Numbers 1-5, where 1 represents today
	private int lowTemp;			// Degrees in Fahrenheit
	private int highTemp;			// Degrees in Fahrenheit
	private String forecast;		/* { "cloudy", 
									 *   "partly cloudy", 
									 *   "rain",
									 *   "snow",
									 *   "sunny",
									 *   "thunderstorms"
									 * }
									 */

  /********  PRIVATE CONSTANTS  *********/
	
	private static final String CELSIUS = "Celsius";
	
	private static final Map<String, String> FORECAST_RECOMMENDATION_MAP = new HashMap<String, String>() {
		{
			put("snow", "Pack snowshoes!");
			put("rain", "Pack rain gear, and wear waterproof shoes!");
			put("thunderstorms", "Seek shelter, and avoid hiking on exposed ridges!");
			put("sun", "Pack sunblock!");
		}
	};
	
	
  /********  PUBLIC HELPER METHODS  *********/
	
	public String getWeatherImageFilename() {
		String forecastImageName = forecast;
		if (forecast.equals("partly cloudy")) forecastImageName = "partlyCloudy";
		return forecastImageName + ".png";
	}
	
	public String getWeatherRecommendation() {
		return getForecastRecommendation() + "\n" + getTemperatureRecommendation();
	}
	
  /********  PRIVATE HELPER METHODS  *********/
	
	private String getForecastRecommendation() {
		String result = "";
		if (FORECAST_RECOMMENDATION_MAP.containsKey(forecast)) {
			result = FORECAST_RECOMMENDATION_MAP.get(forecast);
		}
		return result;
	}

	private String getTemperatureRecommendation() {
		String result = "";
		if (highTemp > 75) result = "Bring an extra gallon of water!\n";
			else if (lowTemp < 20) result = "DANGER! FRIGID TEMPERATURES!!\n";
		if (highTemp - lowTemp > 20) result += "Wear breathable layers!\n";
		return result;
	}
	
	private int convertToCelsius(int temp) {
		return ((temp - 32) * 5) / 9;
	}

	
  /*********  CUSTOM GETTER METHODS  *********/
	
	public int getLowTemp(String tempPreference) {
		int returnTemp = lowTemp;
		if (tempPreference.equals(CELSIUS)) {
			returnTemp = convertToCelsius(lowTemp);
		}
		return returnTemp;
	}
	public int getHighTemp(String tempPreference) {
		int returnTemp = highTemp;
		if (tempPreference.equals(CELSIUS)) {
			returnTemp = convertToCelsius(highTemp);
		}
		return returnTemp;
	}
	
	
  /*********  STANDARD GETTER METHODS  *********/
	
	public String getParkCode() {
		return parkCode;
	}
	public int getForecastDayNumber() {
		return forecastDayNumber;
	}
	public String getForecast() {
		return forecast;
	}
	
	
  /*********  STANDARD SETTER METHODS  *********/
	
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public void setForecastDayNumber(int forecastDayNumber) {
		this.forecastDayNumber = forecastDayNumber;
	}
	public void setLowTemp(int lowTemp) {
		this.lowTemp = lowTemp;
	}
	public void setHighTemp(int highTemp) {
		this.highTemp = highTemp;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

}
