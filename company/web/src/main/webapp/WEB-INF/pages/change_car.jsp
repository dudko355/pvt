<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.Map"%>
<html>
<head>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="local" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

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
			<fmt:message key="changeCar" />
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
						<th><fmt:message key="idCar" /></th>
						<th><fmt:message key="mark" /></th>
						<th><fmt:message key="condition" /></th>
						<th><fmt:message key="changeConditCar" /></th>


					</tr>
				</thead>
				<tbody>
					<c:forEach var="car" items="${ALLCAR}">
						<tr>
							<td><c:out value="${car.idCar}" /></td>
							<td><c:out value="${car.mark}" /></td>

							<td><c:choose>
									<c:when test="${car.condition=='-1'}">
										<fmt:message key="carBroken" />

									</c:when>
									<c:when test="${car.condition=='0'}">
										<fmt:message key="carWork" />

									</c:when>
									<c:when test="${car.condition=='1'}">
										<fmt:message key="carWork" />

									</c:when>
									<c:otherwise>
										<fmt:message key="error" />

									</c:otherwise>
								</c:choose>
							</td >
							<td>
								<form action="${pageContext.servletContext.contextPath}/admin/car_cond" method="post">
									
										<select name="condition" required>
											<option></option>
											<option value="-1"><fmt:message key="carBroken" /></option>
											<option value="0"><fmt:message key="carWork" /></option>
											<option value="1"><fmt:message key="carTrip" /></option>
										</select>
										<input type="submit" value="<fmt:message key="apply"/> " />
										<input type="hidden" name="change_condition" value="${car.idCar}" />
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