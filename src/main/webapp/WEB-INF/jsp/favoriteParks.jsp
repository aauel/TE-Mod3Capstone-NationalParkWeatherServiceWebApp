<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
	<%-- header.jsp includes the NPGeek logo and navigation links --%>

<%-- The main content displays tiles for each park that has received
	 at least 1 vote.
	 Each tile includes the park image, park name and number of votes. --%>

<div class="main-content">
		
	<h3>Current Ranking of Parks!</h3>
	<c:url var="surveyLink" value="/survey" />
	
	<div id="park-flexbox">
		
		<c:forEach var="parkAndCount" items="${surveyParks}">
		
			<div class="card moves fav-park-div">
			
				<%----------- JSP SETUP ------------%>
				<div class="jsp-variables">
						<c:set var="parkItem" value="${parkAndCount.key}"/>
						<c:set var="ranking" value="${parkAndCount.value}"/>
						<c:url var="rankingImgUrl" value="/img/parks/${parkItem.parkImageFilename}"/>
						<c:url value="/parkDetail" var="parkDetailLink">
							<c:param name="code" value="${parkItem.code}"/>
						</c:url>
				</div>
				<%----------------------------------%>
				
				<div class="content">
				
					<%-- image links to /parkDetail?code=____--%>
					<a class="detail-link" href ="${parkDetailLink}"></a> 
					
					<img class="park-img" src="${rankingImgUrl}"/>
					<div class="fav-park-text-div">
						<h4 class="blue">${parkItem.name}</h4>
						<p>Number of Votes:<span class="bold"> ${ranking}</span></p>
					</div>
					
				</div><%-- content --%>
				
			</div><%-- fav-park-card-div --%>
			
		</c:forEach>
		
	</div> <%-- park-flexbox --%>
	
</div> <%-- main-content --%>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />