<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<style>

a,h1{
    
    text-align: center;
}
#centered {
    margin-left:600px;
    margin-right:200px;
    width:600px;
}
a:link, a:visited {
    background-color: #A9A9A9;
    color: white;
    padding: 14px 25px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
}


a:hover, a:active {
    background-color: BurlyWood;
}

body {
    background-image: url("${pageContext.servletContext.contextPath}/resources/fon.jpg");
}
</style>
</head>
<body>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="local" />
	<h1>
		<fmt:message key="language" />
	</h1>
	<div id=centered>
	
	<span> 
		<a href="${pageContext.servletContext.contextPath}/locale?locale=en"><fmt:message key="en"/></a> 
		<a href="${pageContext.servletContext.contextPath}/locale?locale=ru"><fmt:message key="ru"/></a>
		</span>
	</div>

</body>
</html>