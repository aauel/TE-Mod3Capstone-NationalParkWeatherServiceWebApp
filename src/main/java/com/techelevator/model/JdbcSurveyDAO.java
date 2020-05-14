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
public class JdbcSurveyDAO implements SurveyDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcSurveyDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public Map<String, Integer> getAllParkRankings(){
		Map<String, Integer> parkRankings = new LinkedHashMap<String, Integer>();
		String sql = "SELECT COUNT(*), park.parkname "
					+ "FROM survey_result "
					+ "JOIN park ON survey_result.parkcode = park.parkcode "
					+ "GROUP BY park.parkname "
					+ "ORDER BY COUNT(*) DESC, park.parkname;";
				
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			String parkName = results.getString("parkname");
			Integer count = results.getInt("count");
			parkRankings.put(parkName, count);
		}
		return parkRankings;
	}
	
	@Override
	public void saveSurvey(Survey survey) {
		
		String sql = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) "
				+ "VALUES (?,?,?,?); ";
		jdbcTemplate.update(sql, 
							survey.getParkCode(), 
							survey.getEmail(), 
							survey.getResidenceState(), 
							survey.getActivityLevel());
	}

}
