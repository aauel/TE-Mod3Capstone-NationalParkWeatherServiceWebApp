package com.techelevator.model;

import java.util.List;
import java.util.Map;

public interface ParkDAO {
	
	public List<Park> getAllParks();

	public Park getParkByCode(String code);

	List<String> getAllParkNames();

	String getCodeByParkName(String parkName);

	Park getParkByParkName(String parkName);

	Map<String, String> getAllParkCodesWithParkNames();

}
