<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="local" />
<title><fmt:message key="rregistration" /></title>
<style>
input[type=text], input[type=password] {
    width: 20%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 28%;
    background-color: #D2B48C;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

h1{
    
    text-align: center;
}

#tabl {
    background-color: #8B4513;
    padding: 20px;
    margin-left:400px;
    margin-right:200px;
    width:600px;
}

body {
    background-image: url("${pageContext.servletContext.contextPath}/resources/fon.jpg");
}
</style>
</head>
<body>
	<section>
		<div id="tabl">
		<h1>
			<fmt:message key="rregistration" />
		</h1>
			<s:form name= "log" action="${pageContext.servletContext.contextPath}/user/registr" onsubmit="return validateForm()" modelAttribute="clientDto" method="post">
				<fieldset>
			    <label for="login"><fmt:message key="login" /> :</label>
				<s:input id="login" type="text" size="20" maxlength="20" path="login"/>
				<br> <br>
				<label for="password"><fmt:message key="password" /> :</label>
				<s:input id="password" type="password" size="20" maxlength="20" path="password"/>
				<br> <br> 
				<input type="submit" value="<fmt:message key="login" />"> 
				</fieldset>
				<input type="hidden" name="page" value="enter" />
			</s:form>
			<p id="txt"></p>
		</div>
	</section>
	<footer>
		<p><fmt:formatDate value="${now}" dateStyle="full"/> 
		<p>
	</footer>
	<script>
    function validateForm() {
    var x = document.forms["log"]["login"].value;
    var y = document.forms["log"]["password"].value;
    if (x == null || x == ""||y==null||y=="") {
    	document.getElementById("txt").innerHTML=("<fmt:message key="dannieUser" />");
        return false;
    }
}
</script>
</body>
</html>
