package com.techelevator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDAO implements WeatherDAO {

	private JdbcTemplate jdbcTemplate;

	private static final int TODAY_DAY_VALUE = 1;
	
	@Autowired
	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getFiveDayWeatherByParkCode(String parkCode, String tempPreference) {

		List<Weather> fiveDayWeather = new ArrayList<Weather>();
		String sql = "SELECT parkcode, fivedayforecastvalue, low, high, forecast " 
				+ "FROM weather "
				+ "WHERE parkcode = ?; ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);
		while (results.next()) {
			fiveDayWeather.add(mapRowToWeather(results));
		}
		return fiveDayWeather;
	}

	@Override
	public Weather getWeatherForToday(String parkCode, String tempPreference) {

		Weather todayWeather = new Weather();
		String sql = "SELECT parkcode, fivedayforecastvalue, low, high, forecast " + "FROM weather "
				+ "WHERE parkcode = ? AND fivedayforecastvalue = ?; ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode, TODAY_DAY_VALUE);
		while (results.next()) {
			todayWeather = (mapRowToWeather(results));
		}
		return todayWeather;
	}


	private Weather mapRowToWeather(SqlRowSet row) {
		
		Weather w = new Weather();
		w.setParkCode(row.getString("parkCode"));
		w.setForecastDayNumber(row.getInt("fivedayforecastvalue"));
		w.setLowTemp(row.getInt("low"));
		w.setHighTemp(row.getInt("high"));
		w.setForecast(row.getString("forecast")); //

		return w;
	}

}
