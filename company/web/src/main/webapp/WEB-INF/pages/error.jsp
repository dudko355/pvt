<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Map"%>
<html>
<head>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="local" />
<title><fmt:message key="error" /></title>
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
		<h1>
			<fmt:message key="error" />
		</h1>
	</header>
<!--  	<nav>
		<a href="${pageContext.servletContext.contextPath}/${URL}"><button>
				<fmt:message key="back" />
			</button></a>
	</nav>-->
		<div>
			<h2>
			   <fmt:message key="${ERROR}" />
   			</h2>
		</div>
</body>
</html>
