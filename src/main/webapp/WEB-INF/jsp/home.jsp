<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--  --%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />  
	<%-- header.jsp includes the NPGeek logo and navigation links --%>

<%-- The main content displays tiles for each park in the database.
	 Each tile includes the park image one the left, and on the right
	 is the park name, state, and description. --%>

<div class="main-content">

	<c:forEach var="park" items="${parks}">
		<%-- ${parks} passed from homePage() in homeController --%>
		
		<div class="all-park-info">
		
			<c:url value="/parkDetail" var="parkDetailLink">
				<c:param name="code" value="${park.code}"/> 
			</c:url>
			<c:url value="/img/parks/${park.parkImageFilename}" var="parkImgUrl" />
				<%-- park images are named by the 4 character park code --%>
			
			<div class="park-image">
				<a href ="${parkDetailLink}"> <%-- image links to /parkDetail?code=____--%>
				<img src="${parkImgUrl}" alt="park image" /></a>
			</div>
			
			<div class="park-info">
				<h1><a href ="${parkDetailLink}">${park.name}</a></h1>
					<%-- park name links to /parkDetail?code=____--%>
				<h2>${park.state}</h2>
				<p>${park.description}</p>
				
			</div>
			
		</div> <%-- all-park-info --%>
		
	</c:forEach>
	
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />