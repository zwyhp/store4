<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/2/26
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="admin/user" method="post">
    <input type="text" name="username" />
    <input type="text" name="password" />
    <input type="email" name="email">
    <input type="submit" value="save"/>
</form>
<a href="?lang=zh_CN"><spring:message code="user.name.notnull.valid"/></a> &nbsp;&nbsp;&nbsp;
<a href="?lang=en_US"><spring:message code="user.name.notnull.valid"/></a>

<h1>
    <spring:message code="user.name.notnull.valid"/>
</h1>
</body>
</html>
