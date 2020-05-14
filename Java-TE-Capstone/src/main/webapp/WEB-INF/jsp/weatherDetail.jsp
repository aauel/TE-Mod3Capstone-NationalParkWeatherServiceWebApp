<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<%----------- JSP SETUP ------------%>


<%-- This block gets the temperature preference from the controller in order show
	 the correct character (C or F) for the high and low temperatures. --%>		 
<c:choose>
	<c:when test="${tempPreference.equals('Celsius')}">
		<c:set var="tempChar" value="C" />
	</c:when>
	<c:otherwise>
		<c:set var="tempChar" value="F" />
	</c:otherwise>
</c:choose>	


<%----------------------------------%>	 


<div class="weather-group">

	<c:forEach var="dailyWeather" items="${fiveDayWeather}">

		<c:url var="weatherImgUrl" value="/img/weather/${dailyWeather.weatherImageFilename}" />
		<c:set var="tempHigh" value="${dailyWeather.getHighTemp(tempPreference)}" />
		<c:set var="tempLow" value="${dailyWeather.getLowTemp(tempPreference)}" />		
		
		
		<div class="five-day-weather">
		
			<h5>Day ${dailyWeather.forecastDayNumber}</h5>
			<img id="daily-weather-image" src="${weatherImgUrl}" alt="forecast image for ${dailyWeather.forecast}" />
			<span>High ${tempHigh}&deg${tempChar} | Low ${tempLow}&deg${tempChar}</span>
			<br/>
			<p class="recommendations">${dailyWeather.weatherRecommendation}</p>
			
		</div> <%-- five-day-weather --%>
		
	</c:forEach>
	
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />