<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="local" />
<title><fmt:message key="inf" /></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("#tabl").click(function(){
        $("#carRepair").slideToggle("slow");
    });
});
$(document).ready(function(){
    $("#car").click(function(){
        $("#cars").slideToggle("slow");
    });
});
$(document).ready(function(){
    $("#tablDriver").click(function(){
        $("#driver").slideToggle("slow");
    });
});
$(document).ready(function(){
    $("#trip").click(function(){
        $("#tripTabl").slideToggle("slow");
    });
});
</script>

<style>
button {
    background-color: #CD853F
    border: none;
    color: #000000;
    padding: 10px 20px;
    text-decoration: none;
    margin: 2px 1px;
    cursor: pointer;
}
#carRepair, #tabl,#driver,#tablDriver,#tripTabl,#trip,#car,#cars {
     padding: 5px;
    text-align: center;
    background-color: #e5eecc;
    border: solid 1px #c3c3c3; 
 }
 #driver,#carRepair,#tripTabl,#cars {
    padding: 50px;
    display: none;
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
#tab {
    padding: 5px;
    text-align: center;
      
 }
</style>
</head>
<body>

	<div id="tab">
		<h1>
			<fmt:message key="inf" />
		</h1>
	
	<nav>
		 <a href="${pageContext.servletContext.contextPath}/admin/trip">
		 <button><fmt:message key="changeTrip" /></button>
		 </a>
		 <a href="${pageContext.servletContext.contextPath}/admin/car">
		 <button><fmt:message key="changeCar" /></button>
		 </a> 
		 <a href="${pageContext.servletContext.contextPath}/admin/exit_inf">
		 <button><fmt:message key="exit" /></button>
		 </a>
		 
	</nav>
	</div>
	<h4><fmt:message key="clickInf" /></h4>
	<div id="tabl" ><fmt:message key="ccarRepain" /></div>
		<div id="carRepair">
			<table>
				<thead>
					<tr>
						<th><fmt:message key="idCar" /></th>
						<th><fmt:message key="mark" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="car" items="${CARS}">
						<tr>
							<td><c:out value="${car.idCar}" /></td>
							<td><c:out value="${car.mark}" /></td>
			     		</tr>
					</c:forEach>
				</tbody>
			</table>
          </div>
          <div id="trip"><fmt:message key="ttrip" /></div>
	 		<div id="tripTabl">
			<table>
				<thead>
					<tr>
						<th><fmt:message key="idTrip" /></th>
						<th><fmt:message key="target" /></th>
						<th><fmt:message key="idCar" /></th>
						<th><fmt:message key="idClient" /></th>
						<th><fmt:message key="condition" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="trip" items="${ALLTRIP}">
						<tr>
							<td><c:out value="${trip.idTrip}" /></td>
							<td><c:out value="${trip.tripTarget}" /></td>
							<td><c:out value="${trip.car.idCar}" /></td>
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
							<td />
						</tr>
					</c:forEach>
				</tbody>
			</table>
           </div>
            <div id="car"><fmt:message key="carTrip" /></div>
			<div id="cars">
			<table>
				<thead>
					<tr>
						<th><fmt:message key="idCar" /></th>
						<th><fmt:message key="mark" /></th>
						<th><fmt:message key="driver" /></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="car" items="${CARTRIP}">
						<tr>
							<td><c:out value="${car.idCar}" /></td>
							<td><c:out value="${car.mark}" /></td>
							<td><c:forEach var="driver" items="${car.driver}"> 
							<c:out value="${driver.idDriver}" />
							</c:forEach>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table> 
		</div>   
			<div id="tablDriver"><fmt:message key="ddriver" /></div>
          <div id="driver">
			
			<table>
				<thead>
					<tr>
						<th><fmt:message key="idDriver" /></th>
						<th><fmt:message key="name" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="driver" items="${DRIVER}">
						<tr>
							<td><c:out value="${driver.idDriver}" /></td>
							<td><c:out value="${driver.name}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	<footer>
	</footer>
</body>
</html>
