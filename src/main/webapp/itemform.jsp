<%@page import="com.jsp.shoppingcart.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Product p=(Product)request.getAttribute("prodobj");
%>
<form action="additemtocart">
<input type="hidden" name="id" value=<%=p.getId() %>><br>

Brand:<input type="text" name="brand" value=<%=p.getBrand() %>><br><br>
Model:<input type="text" name="model" value=<%=p.getModel()%>><br><br>
Category:<input type="text" name="category"  value=<%=p.getCatagory() %>><br><br>
Price:<input type="number" name="price" value=<%=p.getPrice() %>><br><br>
Quantity:<input type="number" name="quantity" ><br><br>
<input type="submit" value="Add To Cart"><br>

</form>

</body>
</html>