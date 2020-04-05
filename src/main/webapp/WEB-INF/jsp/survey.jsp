<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="main-content">

	<h1>Favorite Park Survey</h1>
	<p>Fill out the details below to vote for your favorite park!</p>
	<div id="surveyForm">
		<c:url var="submitSurveyUrl" value="/survey" />
		<form:form modelAttribute="survey" action="${submitSurveyUrl}" method="POST">
			<div class="form-group">
				<label for="parkCode">Favorite National Park:</label>
				<form:select class="form-control" path="parkCode">
	                <form:option value="-" label="--Select Park"/>
	                <form:options items="${parkNamesMap}" />
	            </form:select>
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<form:input type="email" class="form-control" path="email"/>
				<form:errors path="email" class="bg-danger"/>
			</div>
			<div class="form-group">
				<label for="state">State of Residence:</label>
				<form:select class="form-control" path="residenceState">
					<form:option value="-" label="--Select State"/>
					<form:options items="${survey.stateList}"/>
				</form:select>
				<form:errors path="stateValid" class="bg-danger"/>
			</div>
			<div class="form-group">
				<label for="activityLevel">Activity Level:</label>
				<form:select class="form-control" path="activityLevel">
	                <form:option value="-" label="--Select Activity Level"/>				
					<form:options items="${survey.activityLevelList}" />
				</form:select>
				<form:errors path="activityLevelValid" class="bg-danger"/>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>

</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />