<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
	<%-- header.jsp includes the NPGeek logo and navigation links --%>

<%-- The main content displays all of the details for the chosen park,
	 in the following order:
	 - inspirational quote & quote source 
	 - park image (large) 
	 - park name, state & description 
	 - park facts includes all remaining fields
	 
	 This page also pulls from the weather table to show the 
	 forecast and temperature values for day 1, along with a
	 button providing the option to switch temperatures between
	 Celsius and Fahrenheit.
	 
	 Finally, there is a link to the extended forecast weather page.
--%>

<%----------- JSP SETUP ------------%>

<c:url value="/img/parks/${park.parkImageFilename}" var="parkImgUrl" />
<c:url var="weatherImgUrl" value="/img/weather/${weatherForToday.weatherImageFilename}" />

<%-- This block gets the temperature preference from the controller in order show
	 the correct character (C or F) for the high and low temperatures, as well as to
	 choose which radio button to mark as "checked" by default in the form below. --%>		 
<c:choose>
	<c:when test="${tempPreference.equals('Celsius')}">
		<c:set var="tempChar" value="C" />
		<c:set var="cSelected" value="checked" />
		<c:set var="fSelected" value="" />
	</c:when>
	<c:otherwise>
		<c:set var="tempChar" value="F" />
		<c:set var="cSelected" value="" />
		<c:set var="fSelected" value="checked" />
	</c:otherwise>
</c:choose>	

<c:set var="tempHigh" value="${weatherForToday.getHighTemp(tempPreference)}" />
<c:set var="tempLow" value="${weatherForToday.getLowTemp(tempPreference)}" />

<c:url value="/weatherDetail" var="weatherDetailLink">
	<c:param name="code" value="${park.code}" />
</c:url>

<%----------------------------------%>	 

<div class="main-content">

<div class="quote">
	<div id="fancyQuote">${park.inspirationalQuote}</div>
	<div id="quoteGiver">${park.quoteSource}</div>
</div>

<div class="parkDetail-coreInfo">
	<div class="park-image">
		<img src="${parkImgUrl}" alt="park image" />
	</div>
	<div class="detail-park-info">
		<h3>${park.name}</h3>
		<h4>${park.state}</h4>
		<p>${park.description}</p>
	</div>
</div> <%-- parkDetail-coreInfo --%>

<div class="extras">

	<div id="park-facts">
	
		<h4 class="bold">Park Facts:</h4>
		<ul>
			<li><span>Year Founded:</span> ${park.yearFounded}</li>
			<li><span>Annual Visitor Count:</span> ${park.annualVisitorCount}</li>
			<li><span>Park Entry Fee:</span> $${park.entryFee}</li>
			<li><span>Miles of Trail:</span> ${park.milesOfTrail}</li>
			<li><span>Number of Campsites:</span> ${park.numberOfCampsites}</li>
			<li><span>Climate:</span> ${park.climate}</li>
			<li><span>Acreage:</span> ${park.acreage}</li>
			<li><span>Elevation:</span> ${park.elevationInFeet} ft.</li>
			<li><span>Number of Animal Species:</span> ${park.numberOfAnimalSpecies}</li>
		</ul>
	</div> <%-- park-facts --%>
	
	<div class="weather">
	
		<h4 class="bold">Today's Weather:</h4>
		<img id="today-weather-image" src="${weatherImgUrl}" alt="forecast image for ${weatherForToday.forecast}" />
		<span>High ${tempHigh}&deg${tempChar} | Low ${tempLow}&deg${tempChar}</span>
		<a id="five-day-forecast" href="${weatherDetailLink}">Five Day Forecast</a>
			
		<div id="tempPreferenceForSession">					
			<form action="parkDetail" method="POST">
				<label for=Fahrenheit>Fahrenheit:</label>
				<input type="radio" name="tempPreference" value="Fahrenheit" ${fSelected} />
				<label for=Celsius>Celsius:</label>
				<input type="radio" name="tempPreference" value="Celsius" ${cSelected} />
				<input type="hidden" id="parkCode" name="parkCode" value="${park.code}" />
				<p><button type="submit" class="btn btn-secondary" id="tempButton">
					Change Temperature Preference
					</button></p>
			</form>
		</div> <%-- tempPreferenceForSession --%>
		
				
	</div> <%-- weather --%>
	
</div> <%-- extras --%>
	

</div> <%-- main-content --%>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />