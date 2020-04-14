<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String msg= (String) request.getAttribute("msg");
if(msg!= null)
{
	out.append("<p>"+msg+"</p>");
}
%>
<div>
    <form action="Registration" method="post">
        <p>
            <label>Username:</label>
            <input type="text" name="username" class="form-control" value="" />
        </p>
        <p>
            <label>Passwort:</label>
            <input type="password" name="password1"/>
        </p>
        <p>
            <label>Re-Type Passwort:</label>
            <input type="password" name="password2"/>
        </p>
        <input type="submit" name="submit" value="Register"/>

    </form>
</div>
</body>
</html>