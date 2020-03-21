<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
	<%-- header.jsp includes the NPGeek logo and navigation links --%>

<%-- The main content displays tiles for each park that has received
	 at least 1 vote.
	 Each tile includes the park image, park name and number of votes. --%>

<div class="main-content">
	<div class="favorites">
		
		<h1>Current Ranking of Parks!</h1>
		<c:url var="surveyLink" value="/survey" />
		<p>Click <a href="${surveyLink}">here</a> to submit your favorite!</p>
		
			<div>
			
			<c:forEach var="parkAndCount" items="${surveyParks}">
				<div class="favoriteParks">
					<c:set var="parkItem" value="${parkAndCount.key}"/>
					<c:set var="ranking" value="${parkAndCount.value}"/>
					<c:url value="/parkDetail" var="parkDetailLink">
						<c:param name="code" value="${parkItem.code}"/>
					</c:url>
					
					<div class="fave-park-image">
						<c:url var="rankingImgUrl" value="/img/parks/${parkItem.parkImageFilename}"/>
						<a href="${parkDetailLink}"><img src="${rankingImgUrl}"/></a>
					</div>
					<div class="park-rank-info">
						<h1><a href ="${parkDetailLink}">${parkItem.name}</a></h1>
						<p>Number of Votes:<span class="bold"> ${ranking}</span></p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />