
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06/01/2020
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<center><h2>User Registration form</h2>

    <form:form modelAttribute="userdata" action="processform" class="container" enctype="multipart/form-data" method="POST">
<table cellspacing="10px" cellpadding="10px">
    <tr><td>UserName:</td><td><form:input class="form-control" path="username"/></td></tr>
    <tr><td>Email:</td><td><form:input class="form-control" path="email"/></td></tr>
    <tr><td>Password:</td><td><form:password class="form-control" path="password"/></td></tr>
    <tr><td>Date of Birth:</td><td><form:input type="date" class="form-control" path="dateofbirth"/></td></tr>
    <tr><td>Select File:</td><td> <input type="file" class="form-control" name="profile"/></td></tr>
    <tr><td><input type="submit" value="Submit" class="btn btn-success"/></td><td><input type="reset" value="Clear" class="btn btn-secondary"/></td></tr>
</table>
</form:form>

    <br/>
    <a href="loginpage"><h5>Log In</h5></a>
</center>
</body>
</html>
