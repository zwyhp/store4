<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/4
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="test18">123456</a>

<form action="updateimg" method="post" enctype="multipart/form-data">
    <input type="hidden" value="1" name="id" />
    <input type="file" name="file" />  <input type="submit" value="更新图片">
</form>
</body>
</html>
