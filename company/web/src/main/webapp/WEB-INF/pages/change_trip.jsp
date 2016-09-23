<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.Map"%>
<html>
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="local" />
<title><fmt:message key="adminPage" /></title>
<style>
button,input[type="submit"],select {
    background-color: #CD853F
    border: none;
    color: white;
    padding: 10px 20px;
    text-decoration: none;
    margin: 2px 1px;
    cursor: pointer;
}
select{
color: black;
}
table, td, th {
    
    text-align: center;
}
h1{
    
    text-align: center;
}
table {
    border-collapse: collapse;
    width: 100%;
}
body {
   background-image: url("${pageContext.servletContext.contextPath}/resources/fon.jpg");
}

</style>
</head>
<body>
	<header>
		<h1>
			<fmt:message key="changeTrip" />
		</h1>
	</header>
	<nav>
		<a href="${pageContext.servletContext.contextPath}/admin/exit_inf"><button>
				<fmt:message key="adminPage" />
			</button></a>
	</nav>
	<section>
		<div>
			
			<table>
				<thead>
					<tr>
						<th><fmt:message key="idTrip" /></th>
						<th><fmt:message key="idCar" /></th>
						<th><fmt:message key="target" /></th>
						<th><fmt:message key="idClient" /></th>
						<th><fmt:message key="condition" /></th>
						<th><fmt:message key="changeCondiTtrip" /></th>


					</tr>
				</thead>
				<tbody>
					<c:forEach var="trip" items="${ALLTRIP}">
						<tr>
							<td><c:out value="${trip.idOrder}" /></td>
							<td><c:out value="${trip.car.idCar}" /></td>
							<td><c:out value="${trip.target}" /></td>
							<td><c:out value="${trip.idClient}" /></td>
							<td><c:choose>
									<c:when test="${trip.conditionTrip=='-1'}">
										<fmt:message key="tripNotStart" />

									</c:when>
									<c:when test="${trip.conditionTrip=='0'}">
										<fmt:message key="tripStart" />

									</c:when>
									<c:when test="${trip.conditionTrip=='1'}">
										<fmt:message key="tripEnded" />

									</c:when>
									<c:otherwise>
										<fmt:message key="error" />

									</c:otherwise>
								</c:choose>
							</td >
							<td>
								<form action="${pageContext.servletContext.contextPath}/admin/trip_cond" method="post">
									<p>
									<c:choose>
									<c:when test="${trip.conditionTrip=='-1'}">
										<select name="condition" required>
											<option ></option>
											<option value="-1"><fmt:message key="tripNotStart" /></option>
											<option value="0"><fmt:message key="tripStart" /></option>
											<option value="1"><fmt:message key="tripEnded" /></option>
											</select>
											<input type="submit" value="<fmt:message key="apply" />">
										    <input type="hidden" name="change_condition" value="${trip.idOrder}" />
											</c:when>
											<c:when test="${trip.conditionTrip=='0'}">
											<select name="condition" required>
											<option></option>
											<option value="0"><fmt:message key="tripStart" /></option>
											<option value="1"><fmt:message key="tripEnded" /></option>
											</select>
											<input type="submit" value="<fmt:message key="apply" />">
										    <input type="hidden" name="change_condition" value="${trip.idOrder}" />
											</c:when>
											<c:when test="${trip.conditionTrip=='1'}">
											<fmt:message key="tripEnded" />
											</c:when>
										</c:choose>
										
								</form>
							</td>


						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	</body>
</html>