<%@page import="com.jsp.shoppingcart.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% Customer c=(Customer) session.getAttribute("customerinfo");
if(c!=null){%>
<h1>${msg}</h1>
<h1><a href="displayproducts">displayproducts</a></h1>
<h1><a href="brandbyproducts.jsp">display products by brand</a></h1>
<h1><a href="catagorybyproduct.jsp">display by category</a></h1>
<h1><a href="RangeByproduct.jsp">display products in range between</a></h1>



<%}else{ %>
<h1><a href="customerlogin.jsp">\login plzz..</a></h1>
<%} %>

</body>
</html>