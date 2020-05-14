<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--  --%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />  
	<%-- header.jsp includes the NPGeek logo and navigation links --%>

<%-- The main content displays tiles for each park in the database.
	 Each tile includes the park image one the left, and on the right
	 is the park name, state, and description. --%>

<div class="main-content">

	<h3>Select a park below for more details!</h3>
	<div id="park-flexbox">

	<c:forEach var="park" items="${parks}">
		<%-- ${parks} passed from homePage() in homeController --%>
		
		<div class="card moves home-park-div">
		
			<c:url value="/parkDetail" var="parkDetailLink">
				<c:param name="code" value="${park.code}"/> 
			</c:url>
			<c:url value="/img/parks/${park.parkImageFilename}" var="parkImgUrl" />
				<%-- park images are named by the 4 character park code --%>
			
			<a class="detail-link" href ="${parkDetailLink}"></a> <%-- image links to /parkDetail?code=____--%>
			<img class="park-img" src="${parkImgUrl}" alt="park image" />
			
			<div class="park-text-div">
				<h4 class="blue">${park.name}</h4>
					<%-- park name links to /parkDetail?code=____--%>
				<h5 class="state">${park.state}</h5>
				
			</div>
			
		</div> <%-- all-park-info --%>
		
	</c:forEach>
	
	</div> <%-- park-flexbox --%>
	
</div> <%-- main-content --%>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />