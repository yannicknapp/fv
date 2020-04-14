<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>
<%@page import="article.DBManager" %>
<%@page import="java.sql.SQLException" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Postings</title>
</head>
<body>


<h1>Postings</h1>

  <%
try {
	DBManager inst = DBManager.getInstance();
	Connection con=inst.getConnection();
	String sql = "select * from article";
	Statement stm= con.createStatement();
	ResultSet rs = stm.executeQuery(sql);
	
	while (rs.next()){
%>
<form action="Comments" method="post">
<input type="hidden" name="bt" value="<%=rs.getString("articleID")%>">
 <input type="submit" value="<%=rs.getString("articleTitel")%>" />
 </form>
 </br>
  
  <%
}
}catch(Exception e)
{
	e.printStackTrace();
}%>

</body>
</html>