<%@page import="com.jsp.shoppingcart.dto.Product"%>
<%@page import="java.util.List"%>

    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
body{
background-image: url("https://tse1.mm.bing.net/th?id=OIP.khdK03WP24-cLkV5rVQwoAHaE8&pid=Api&P=0&h=180");
}

</style>
<body>
<%
List<Product> products=(List<Product>)request.getAttribute("productlist");
%>


</h1>
<h1><a href="fetchitemsfromcart">view all cart</a></h1>
<table cellpadding="20px" border="1">
<th> id</th>
<th>brand</th>
<th>category</th>
<th>model</th>
<th>price</th>
<th>Add to cart</th>
<%
for(Product p:products){%>
<tr>
<td><%=p.getId()%></td>
<td><%=p.getBrand()%></td>
<td><%=p.getCatagory()%></td>
<td><%=p.getModel()%></td>
<td><%=p.getPrice()%></td>

<td><a href="additem?id=<%=p.getId()%>">Add</a></td>
</tr>
<%}
%>




</table>

</body>
</html>