<%--
  Created by IntelliJ IDEA.
  User: Great
  Date: 2017/2/7
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
    <form action="login.action" method="post">
      Login<br>
      Username:<input type="text" name="user.username">
      Password:<input type="text" name="user.password">
      <input type="submit" value="login">
    </form>
  </body>
</html>
