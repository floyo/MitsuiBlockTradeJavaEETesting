<%-- 
    Document   : error.jsp
    Created on : Mar 26, 2013, 2:01:50 PM
    Author     : Chris.Yu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML> 
<html> 
<head> 
<meta charset="utf-8" /> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
<title>Error</title> 
</head> 
<body> 
 <h1>Error</h1> 
 <p>Please contact administrator immediately!</p>
 <p>Error Message: &nbsp;<%= request.getAttribute("errorMsg") %></p> 
</body> 
</html>
