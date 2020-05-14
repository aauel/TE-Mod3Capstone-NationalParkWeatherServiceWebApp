package com.techelevator.model;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;


public class Survey {

	// parkCode will be translated from the Park Name chosen in the form
	private String parkCode;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String residenceState;
	@AssertTrue(message = "Select a state from the list")
	public boolean isStateValid() { return STATES.contains(residenceState); }
	
	@NotBlank
	private String activityLevel;
	@AssertTrue(message = "Select an activity level from the list")
	public boolean isActivityLevelValid() { return ACTIVITY_LEVELS.contains(activityLevel); }
	
	
	/********  PRIVATE CONSTANTS  *********/
	
	private static final List<String> ACTIVITY_LEVELS = Arrays.asList(
			new String[] {"Inactive", "Sedentary", "Active", "Extremely Active"});
	
	private static final List<String> STATES = Arrays.asList(
			new String[] {"AL","AK","AZ","AR","CA","CO","CT","DE","DC","FL",
					"GA","HI","ID","IL","IN","IA","KS","KY","LA","ME",
					"MD","MA","MI","MN","MS","MO","MT","NE","NV","NH",
					"NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI",
					"SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"});
	
	
	/********  PUBLIC HELPER METHODS  *********/

	// for populating drop-down options in survey form
	public List<String> getActivityLevelList() {
		return ACTIVITY_LEVELS;
	}
	
	// for populating drop-down options in survey form
	public List<String> getStateList() {
		return STATES;
	}
	
	
	/*********  STANDARD GETTER METHODS  *********/

	public String getParkCode() {
		return parkCode;
	}
	public String getEmail() {
		return email;
	}
	public String getResidenceState() {
		return residenceState;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	
	
	/*********  STANDARD SETTER METHODS  *********/

	public void setResidenceState(String residenceState) {
		this.residenceState = residenceState;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

}
