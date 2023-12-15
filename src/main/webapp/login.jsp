<%-- 在登录页面中获取Cookie的用户名和密码 --%>
<%@ page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="./css/login_style.css">
</head>


<body>
<%
    String username = "";
    String password = "";
    // 获取当前站点的所有Cookie
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.endsWith("_password")) {
                // 根据"_password"的Cookie名称找到对应的用户名Cookie
                String encodedUsername = cookieName.replace("_password", "");
                username = URLDecoder.decode(encodedUsername, "UTF-8");
                password = cookie.getValue();
            }
        }
    }

%>

<!-- 登录列表 -->
<div id="login-container">
    <h1>登录</h1>
    <img src="./img/logo.png" alt="Logo">
    <form action="/shopping/LoginServlet" method="post">
        <label for="username">用户名：</label>
        <input type="text" id="username" name="username" value="<%=username%>" required>
        <label for="password">密码：</label>
        <input type="password" id="password" name="password" value="<%=password%>" required>
        <div class="remember-me">
            <input type="checkbox" id="remember" name="remember">
            <label for="remember">记住密码</label>
        </div>
        <button type="submit">登录</button>
        <button type="reset">重置</button>
        <div class="link">
            <a href="https://www.scuec.edu.cn/jky/" target="_blank">计科学院主页</a>
            <a href="register.jsp">注册账号</a>
        </div>
    </form>
</div>
</body>
</html>
