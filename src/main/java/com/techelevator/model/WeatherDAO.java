package com.techelevator.model;

import java.util.List;
import java.util.Map;

public interface WeatherDAO {

	Weather getWeatherForToday(String parkCode, String tempPreference);

	List<Weather> getFiveDayWeatherByParkCode(String parkCode, String tempPreference);

}
