<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="local" />
<title><fmt:message key="orders" /></title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/themes/sunny/jquery-ui.css">

<style type="text/css">
input {
	width: 200px;
	text-align: left
}
</style>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/themes/sunny/jquery-ui.css">
<script type="text/javascript">
	$(function() {

		$('#datep').datepicker({
			constrainInput : true,
			changeMonth : true,
			changeYear : true,
			yearRange : "-1:+2"

		});

	});
	$(function() {

		$('#datepic').datepicker({
			constrainInput : true,
			changeMonth : true,
			changeYear : true,
			yearRange : "-1:+2"
		});

	});
</script>
<style>
button {
	background-color: #CD853F border: none;
	color: #000000;
	padding: 10px 20px;
	text-decoration: none;
	margin: 2px 1px;
	cursor: pointer;
}

#filtr {
	background-color: #8B4513;
	padding: 20px;
	margin-left: 50px;
	margin-right: 800px;
	width: 400px;
}

#sort {
	background-color: #8B4513;
	padding: 20px;
	margin-left: 500px;
	margin-top: -190px;
	width: 700px;
}

table, td, th {
	text-align: center;
}

h1 {
	text-align: center;
}

table {
	border-collapse: collapse;
	width: 100%;
}

a {
	margin: 2px 1px;
	text-align: center;
}

body {
	background-image:
		url("${pageContext.servletContext.contextPath}/resources/fon.jpg");
}
</style>


</head>
<body>
	<div id="filtr">
		<p>
			<fmt:message key="filter" />
		</p>
		<form action="${pageContext.servletContext.contextPath}/trip/filtr"
			method="post">
			<input type="submit" value="<fmt:message key="true" />"><br>
			<fmt:message key="dateIf" />

			<br> <input
				value="<fmt:formatDate value="${filtr.dateStart}" pattern="MM/dd/yyyy" dateStyle="full" />"
				name="dateStart" readonly="readonly" id="datep" /> - <input
				value="<fmt:formatDate value="${filtr.dateFinish}" pattern="MM/dd/yyyy" dateStyle="full" />"
				name="dateFinish" readonly="readonly" id="datepic" /> <br>
			<fmt:message key="target" />
			<br> <input type="text" name="target"
				value="${filtr.tripTarget}">
			<c:out value="${messages['target']}" />
			<br> <br> <input type="hidden" name="page" value="ORDER_IF" />

			<ul class="pagination">
				<c:if test="${pagination.page!='1'}">
					<li><a
						href="${pageContext.servletContext.contextPath}/trip/next_page?next_page=0">«</a></li>
				</c:if>
				<c:if test="${page!='0'}">
					<li><a href="#">${pagination.page}</a></li>
				</c:if>
				<c:if test="${pagination.page!=pagination.allPage}">
					<li><a
						href="${pageContext.servletContext.contextPath}/trip/next_page?next_page=1">»</a></li>
				</c:if>
			</ul>
			<p>
				<fmt:message key="amount" />
			</p>
			<select class=big name='java-navigator'
				onchange='top.location.href = this.options[this.selectedIndex].value;'>
				<option value="${pagination.count}">${pagination.count}</option>
				<c:forEach var="i" begin="1" end="10">
					<option
						value='${pageContext.servletContext.contextPath}/trip/amount?element=${i}'>${i}</option>
				</c:forEach>
			</select>
		</form>
	</div>
	<div id="sort">
		<form action="${pageContext.servletContext.contextPath}/trip/sort"
			method="post">

			<h4>
				<fmt:message key="sort" />
			</h4>
			<table>
				<thead>
					<tr>
						<th><p>
								<fmt:message key="sort_3" />
							</p></th>
						<th><p>
								<fmt:message key="sort_4" />
							</p></th>
						<th><p>
								<fmt:message key="sort_5" />
							</p></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:choose>
							<c:when test="${source.orderColumn eq 'DESC'}">
								<th><input type="radio" name="order" value="ASC"></th>
								<th><input type="radio" name="order" value="DESC" checked></th>
							</c:when>
							<c:otherwise>
								<th><input type="radio" name="order" value="ASC" checked></th>
								<th><input type="radio" name="order" value="DESC"></th>
							</c:otherwise>
						</c:choose>
						<th><select name="source">
								<option value='${source.column}'>
									<c:if test="${source.column eq 'conditionTrip'}">
										<fmt:message key="sort_0" />
									</c:if>
									<c:if test="${source.column eq 'dateStart'}">
										<fmt:message key="sort_1" />
									</c:if>
									<c:if test="${source.column eq 'dateFinish'}">
										<fmt:message key="sort_2" />
									</c:if>
								</option>
								<option value='${source.column}'></option>
								<option value='conditionTrip'><fmt:message key="sort_0" /></option>
								<option value='dateStart'><fmt:message key="sort_1" /></option>
								<option value='dateFinish'><fmt:message key="sort_2" /></option>

						</select></th>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="page" value="SORT" /><br> <input
				type="submit" value="<fmt:message key="true" />">
		</form>
	</div>

	<h1>
		<fmt:message key="myOrders" />
	</h1>

	<nav>


		<div>

			<table>
				<thead>
					<tr>
						<th><fmt:message key="idOrder" /></th>
						<th><fmt:message key="target" /></th>
						<th><fmt:message key="dateBegin" /></th>
						<th><fmt:message key="dateFinish" /></th>
						<th><fmt:message key="changeCondiTtrip" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="trip" items="${trips}">
						<tr>
							<td><c:out value="${trip.idOrder}" /></td>
							<td><c:out value="${trip.tripTarget}" /></td>
							<td><fmt:formatDate value="${trip.dateStart}"
									dateStyle="full" /></td>
							<td><fmt:formatDate value="${trip.dateFinish}"
									dateStyle="full" /></td>
							<td><c:choose>
									<c:when test="${trip.conditionTrip=='-1'}">
										<form
											action="${pageContext.servletContext.contextPath}/client/delTrip"
											method="post">
											<input type="hidden" name="page" value="CLIENT_TRIP" /> <input
												type="submit" value="<fmt:message key="delete" />">
											<input type="hidden" name="delete_trip"
												value="${trip.idOrder}" />
										</form>
									</c:when>
									<c:when test="${trip.conditionTrip=='0'}">
										<fmt:message key="tripStart" />
									</c:when>
									<c:when test="${trip.conditionTrip=='1'}">
										<fmt:message key="tripEnded" />
									</c:when>

								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>






			<a href="${pageContext.servletContext.contextPath}/client/return"><button>
					<fmt:message key="backClient" />
				</button></a>
		</div>
	</nav>

</body>
</html>
