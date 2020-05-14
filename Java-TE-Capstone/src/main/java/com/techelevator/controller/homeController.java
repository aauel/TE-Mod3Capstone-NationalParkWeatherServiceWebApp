package com.techelevator.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Park;
import com.techelevator.model.ParkDAO;
import com.techelevator.model.Survey;
import com.techelevator.model.SurveyDAO;
import com.techelevator.model.WeatherDAO;

@Controller
public class homeController {
	
	@Autowired
	ParkDAO parkDAO;
	@Autowired
	WeatherDAO weatherDAO;
	@Autowired
	SurveyDAO surveyDAO;
	
	/**
	 * GET - provides a list of all Park objects in the database to the home page
	 */
	@RequestMapping(method=RequestMethod.GET, path={"/", "/home"})
    public String homePage(ModelMap modelMap) {
		modelMap.put("parks", parkDAO.getAllParks());
        return "home";
    }
	
	/**
	 * GET - provides a Park object to the parkDetail page based on the park that was clicked,
	 * 	          along with a single Weather object for the chosen park on day 1
	 * 
	 * @param String code - received as a query string in order to provide the Park object
	 * @param HttpSession session - to keep track of the temperature preference across pages
	 */
	@RequestMapping(method=RequestMethod.GET, path= "/parkDetail")
	public String detailPage(ModelMap modelMap, @RequestParam String code, HttpSession session) {
		Park p = parkDAO.getParkByCode(code);
		modelMap.put("park", p);
		
		// if user hasn't yet selected a temperature preference, set to Fahrenheit by default
		if(session.getAttribute("tempPreference")== null) {
			session.setAttribute("tempPreference", "Fahrenheit");  //make boolean, such as prefersFahrenheit
		}
		String tempPref = (String)session.getAttribute("tempPreference");
		// single Weather object for day 1 (aka today) with the temperature values set by tempPref
		modelMap.put("weatherForToday", weatherDAO.getWeatherForToday(code, tempPref));  
		
		return "parkDetail";
	}
	
	/**
	 * POST - handles the change in temperature preference when form is submitted from the parkDetail page
	 * 
	 * @param String tempPreference - either Celsius or Fahrenheit
	 * @param String hiddenParkCode - for RedirectAttributes
	 * @param RedirectAttributes ra - for passing parkCode to the parkDetail GET redirect
	 * @param HttpSession session - to keep track of the temperature preference across pages
	 */
	@RequestMapping(method=RequestMethod.POST, path="/parkDetail")
	public String temperatureToggle(ModelMap modelMap, @RequestParam String tempPreference, 
																@RequestParam String parkCode, RedirectAttributes ra, HttpSession session) {
		session.setAttribute("tempPreference", tempPreference);
		ra.addAttribute("code", parkCode);
		return "redirect:/parkDetail";
	}
	
	/**
	 * GET - provides a map of 5 Weather objects for the selected park
	 * 
	 * @param String code - received as a query string in order to provide the Park object
	 * @param HttpSession session - to keep track of the temperature preference across pages
	 */
	@RequestMapping(method=RequestMethod.GET, path="/weatherDetail")
	public String weatherDetailPage(ModelMap modelMap, @RequestParam String code, HttpSession session) {

		String tempPref = (String)session.getAttribute("tempPreference");
		// List of five Weather objects for the extended forecast with the temperature values set by tempPref
		modelMap.put("fiveDayWeather", weatherDAO.getFiveDayWeatherByParkCode(code, tempPref));
		return "weatherDetail";
	}
	
	/**
	 * GET - survey form to select a favorite park
	 */
	@RequestMapping(method=RequestMethod.GET, path="/survey")
	public String surveyPage(ModelMap modelMap) {
		// for validation rules in the POST method
        if( ! modelMap.containsAttribute("survey")){
        	modelMap.put("survey", new Survey());
        	modelMap.put("parkNamesMap", parkDAO.getAllParkCodesWithParkNames());  // to provide dropdown of Park Names
        }
        return "survey";
	}
	
	/**
	 * POST - survey form to select a favorite park
	 * 
	 * @param Survey survey - contains user's form entries in order to add to database
	 */
	 @RequestMapping(path="/survey", method=RequestMethod.POST)
	    public String submitSurvey( @Valid @ModelAttribute Survey survey,
									BindingResult result, RedirectAttributes flash) {
		 	// form validation 
	        if(result.hasErrors() || parkDAO.getParkByCode(survey.getParkCode()) == null) {
	            flash.addFlashAttribute("survey", survey);
	            flash.addFlashAttribute("parkNamesMap", parkDAO.getAllParkCodesWithParkNames());
	            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
	            flash.addFlashAttribute("message", "Please fix the following errors:");
	             return "redirect:/survey";
	        }
	        // if no form errors, save survey to database
	        surveyDAO.saveSurvey(survey);
	        return "redirect:/favoriteParks";
	    }
	 
	 /**
	 * GET - Provides a map of Park objects with the number of surveys for that park
	 */
	 @RequestMapping(path="/favoriteParks", method=RequestMethod.GET)
	 public String showFavoriteParks(ModelMap modelMap) {
		 	// map of parkNames with the count of surveys for each
	        Map<String, Integer> currentSurveys = surveyDAO.getAllParkRankings();  //change to map of parks to surveynum
	        
	        // translate map of parkNames and counts to map of Park objects and counts
	        // so that the favoriteParks page will have each park name, count of surveys,
	        // and the park code in order to link to park image ('parkCode.jpg')
	        Map<Park, Integer> surveyParks = new LinkedHashMap<>();
	        for(String s : currentSurveys.keySet()) {
	        	Park p = parkDAO.getParkByParkName(s);
	        	Integer count = currentSurveys.get(s);
	        	surveyParks.put(p, count);
	        }
	        modelMap.put("surveyParks", surveyParks);
		 
		 return "favoriteParks";
	 }
	
	
}
