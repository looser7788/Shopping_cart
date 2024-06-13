<%@page import="com.jsp.shoppingcart.dto.Merchent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="color: green;">
<% Merchent m=(Merchent) session.getAttribute("merchantinfo"); %>
<%if(m!=null){ %>
<h1>${msg}</h1>
<h1>
<a href="addproduct">addproduct</a>
</h1>
<h1>
<a href="viewAllproducts.jsp">view all products</a>
</h1>
<%} else {%><h1><a href="merchantloginform.jsp">plese login first</a></h1><%} %>
</body>
</html>