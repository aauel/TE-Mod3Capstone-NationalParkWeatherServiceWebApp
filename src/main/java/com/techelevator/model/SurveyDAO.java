package com.techelevator.model;

import java.util.List;
import java.util.Map;

public interface SurveyDAO {

	Map<String, Integer> getAllParkRankings();

	void saveSurvey(Survey survey);


}
