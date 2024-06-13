<%@page import="com.jsp.shoppingcart.dto.Iteam"%>
<%@page import="java.util.List"%>
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
List<Iteam> items= (List<Iteam>) request.getAttribute("itemlist");
double totalprice=(double)   request.getAttribute("totalprice");
%>
<table cellpadding=20px; border=1;>
<th>Brand</th>
<th>Model</th>
<th>Category</th>
<th>Price</th>
<th>Quntity</th>
<%
for(Iteam i:items) {

%>
<tr>
<td><%=i.getBrand() %></td>
<td><%=i. getModel()%></td>
<td><%=i.getCatagory()%></td>
<td><%=i.getPrice() %></td>
<td><%=i.getQuantity() %></td>
</tr>

<%
}
%>

</table>
<h2>Totalprice:<%=totalprice %></h2>
<br>
<a  href="addorder">Bynow</a>

</body>
</html>