<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06/01/2020
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<center>
    <br/><br/>
    <h2>Login page</h2>
    <br/><br/>
<form  method="POST" action="processlogin">
<table cellspacing="10px" cellpadding="10px">
    <tr><td>Username:</td><td><input type="text" class="form-control" name="txtuser"/></td></tr>
    <tr><td>Password:</td><td><input type="password" class="form-control" name="txtpassword"></td></tr>
    <tr><td><input type="submit" class="btn btn-success" value="Submit"></td></tr>
</table>
</form>
<br/><br/>
    <h5 style="color: red">${message}</h5>
<br/><br/>
    <a href="signuppage">Sign Up</a>
</center>
</body>
</html>
