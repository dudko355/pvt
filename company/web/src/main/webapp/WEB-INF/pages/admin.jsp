<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="local" />
<title><fmt:message key="aadminPage" /></title>
<style>
button,input[type=submit] {
    background-color: #CD853F
    border: none;
    color: #000000;
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
	<nav>

		<div id="tabl">
		<h1>
			<fmt:message key="aadminPage" />
		</h1>
		<a href="${pageContext.servletContext.contextPath}/admin/exit"><button>
				<fmt:message key="exit"/>
			</button></a>
		<a href="${pageContext.servletContext.contextPath}/admin/inf"><button>
				<fmt:message key="inf"/>
			</button></a>
	</div>
	</nav>
	
</body>
</html>