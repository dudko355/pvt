<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="local" />
<title><fmt:message key="ccarBase" /></title>
<style>
button {
    background-color: #CD853F
    border: none;
    color: white;
    padding: 10px 20px;
    text-decoration: none;
    margin: 2px 1px;
    cursor: pointer;
}
#tabl {
    padding: 5px;
    text-align: center;
      
 }

body {
    background-image: url("${pageContext.servletContext.contextPath}/resources/fon.jpg");
}
</style>
</head>
<body>

	<div id="tabl">
	<h1>
			<fmt:message key="ccarBase" />
		</h1>
	
	<nav>
		<a href="${pageContext.servletContext.contextPath}/login"><button>
				<fmt:message key="login" />
			</button></a> <a href="${pageContext.servletContext.contextPath}/registration"><button>
				<fmt:message key="registration" />
			</button></a> <a href="${pageContext.servletContext.contextPath}/admin/admin"><button>
				<fmt:message key="admin" />
			</button></a>

	</nav>
	<h1></h1>
	
	<a href="${pageContext.servletContext.contextPath}/index"><fmt:message key="language" /></a>
			<br />
		
			<fmt:message key="hi" />
		
	</div>
	
</body>
</html>
