<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form action="saveorder" modelAttribute="orderobj">
enter name:<form:input path="name"/>
enter address:<form:input path="address"/>
enter mobilenumber:<form:input path="mobilenumber"/>
<input type="submit">

</form:form>
</body>
</html>