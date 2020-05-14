package com.techelevator.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCParkDAO implements ParkDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCParkDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public Map<String, String> getAllParkCodesWithParkNames() {
		Map<String, String> allParkCodes = new LinkedHashMap<>();
		String sql = "SELECT parkcode, parkname "
					+ "FROM park "
					+ "ORDER BY parkname;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			String code = results.getString("parkcode");
			String name = results.getString("parkname");
			allParkCodes.put(code, name);
		}
		return allParkCodes;	
	}
	
	@Override
	public List<String> getAllParkNames() {
		List<String> allParkNames = new ArrayList<>();
		String sql = "SELECT parkname "
					+ "FROM park "
					+ "ORDER BY parkname;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			allParkNames.add(results.getString("parkname"));
		}
		return allParkNames;	
	}
	
	@Override
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<>();
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, "
					+ "milesoftrail, numberofcampsites, climate, yearfounded, "
					+ "annualvisitorcount, inspirationalquote, inspirationalquotesource, "
					+ "parkdescription, entryfee, numberofanimalspecies "
					+ "FROM park "
					+ "ORDER BY parkname;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			allParks.add(mapRowToPark(results));
		}
		return allParks;
	}
	
	@Override
	public Park getParkByCode(String code) {
		Park p = new Park();
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, "
					+ "milesoftrail, numberofcampsites, climate, yearfounded, "
					+ "annualvisitorcount, inspirationalquote, inspirationalquotesource, "
					+ "parkdescription, entryfee, numberofanimalspecies "
					+ "FROM park "
					+ "WHERE parkcode = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, code);
		while(results.next()) {
			p = mapRowToPark(results);
		}
		return p;
	}
	
	@Override
	public String getCodeByParkName(String parkName) {
		String code = "";
		String sql = "SELECT parkcode "
					+ "FROM park "
					+ "WHERE parkname = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkName);
		while(result.next()) {
			code = result.getString("parkcode");
		}
		return code;
	}
	
	@Override
	public Park getParkByParkName(String parkName) {
		Park p = new Park();
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, "
					+ "milesoftrail, numberofcampsites, climate, yearfounded, "
					+ "annualvisitorcount, inspirationalquote, inspirationalquotesource, "
					+ "parkdescription, entryfee, numberofanimalspecies "
					+ "FROM park "
					+ "WHERE parkname = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkName);
		
		while(results.next()) {
			p = mapRowToPark(results);
		}
		return p;
	}
	
	
	private Park mapRowToPark (SqlRowSet row) {

		Park p = new Park();
		
		p.setCode(row.getString("parkcode"));
		p.setName(row.getString("parkname"));
		p.setState(row.getString("state"));
		p.setAcreage(row.getInt("acreage"));
		p.setElevationInFeet(row.getInt("elevationinfeet"));
		p.setMilesOfTrail(row.getDouble("milesoftrail"));
		p.setNumberOfCampsites(row.getInt("numberofcampsites"));
		p.setClimate(row.getString("climate"));
		p.setYearFounded(row.getInt("yearfounded"));
		p.setAnnualVisitorCount(row.getInt("annualvisitorcount"));
		p.setInspirationalQuote(row.getString("inspirationalquote"));
		p.setQuoteSource(row.getString("inspirationalquotesource"));
		p.setDescription(row.getString("parkdescription"));
		p.setEntryFee(row.getInt("entryfee"));
		p.setNumberOfAnimalSpecies(row.getInt("numberofanimalspecies"));
		
		return p;
	}

}
