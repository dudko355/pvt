<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="local" />
<title><fmt:message key="my" /></title>
<style>
button ,input[type=submit]{
    background-color: #CD853F
    border: none;
    color: #000000;
    padding: 10px 20px;
    text-decoration: none;
    
    cursor: pointer;
     
     width:600px;
}

body {
    background-image: url("${pageContext.servletContext.contextPath}/resources/fon.jpg");
}
#tabl {
    text-align: center;
    background-color: #8B4513;
    padding: 20px;
    margin-left:400px;
    margin-right:200px;
    width:600px;
}
</style>
</head>
<body>
	
	<nav>
	<div id="tabl">
	<h1>
			<fmt:message key="my" />
		</h1>
		
			
			<a href="${pageContext.servletContext.contextPath}/client/orders"><button>
				<fmt:message key="orders" />
			</button></a>
			 <a href="${pageContext.servletContext.contextPath}/client/order"><button>
				<fmt:message key="make" />
			</button></a>
			<a href="${pageContext.servletContext.contextPath}/client/exit"><button>
				<fmt:message key="logout" />
			</button></a>
			<a href="${pageContext.servletContext.contextPath}/client/delete"><button>
				<fmt:message key="deleteAcount" />
			</button></a>
			
			
		</div>
	</nav>
	<section>
		<br />
	</section>
	
</body>
</html>
