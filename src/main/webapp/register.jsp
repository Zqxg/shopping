<%@ page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="./css/register_style.css">
</head>

<body>
<!-- 注册列表 -->
<div id="register-container">
    <h1>注册</h1>
    <img src="./img/logo.png" alt="Logo">
    <form action="/shopping/RegisterServlet" method="post">
        <label for="newUsername">新用户名：</label>
        <input type="text" id="newUsername" name="newUsername" required>
        <label for="newPassword">新密码：</label>
        <input type="password" id="newPassword" name="newPassword" required>
        <button type="submit">注册</button>
        <div class="link">
            <a href="login.jsp">返回登录</a>
        </div>
    </form>
</div>
</body>

</html>
