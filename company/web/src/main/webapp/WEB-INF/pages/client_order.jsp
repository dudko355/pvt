<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Map"%>
<html>
<head>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="local" />
<style>
body {
    background-image: url("${pageContext.servletContext.contextPath}/resources/fon.jpg");
     text-align: center;
}
button ,input[type=submit]{
    background-color: #CD853F
    border: none;
    color: #000000;
    padding: 10px 20px;
    text-decoration: none;
     cursor: pointer;
h2,h1{
    
    text-align: center;
}
</style>
</head>
<body>
<header>
<h1><fmt:message key="successfullyOrder" /></h1>
</header>
  	<nav>
		<a href="${pageContext.servletContext.contextPath}/client/orders"><button>
				<fmt:message key="orders" />
			</button></a>
				<a href="${pageContext.servletContext.contextPath}/client/return"><button>
					<fmt:message key="backClient" />
				</button></a>
	</nav>
		<div>
		<p><fmt:message key="car" /> :<c:out value="${car.mark}" /> </p><br>
		<p><fmt:message key="driver" /> :<c:out value="${driver}" /></p><br>
		</div>
</body>
</html>
