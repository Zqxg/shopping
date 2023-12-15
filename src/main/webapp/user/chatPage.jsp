<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>聊天室</title>
    <link rel="stylesheet" type="text/css" href="../css/chat_style.css">

</head>

<body>
<%--雪花--%>
<canvas id="myCanvas"></canvas>

<!-- 在线人数显示区域 -->
<div>
    <p>当前在线人数：<%= application.getAttribute("onlineUsers") %></p>

</div>

<!-- 聊天记录显示区域 -->
<div class="chat">
    <textarea rows="10" cols="50" readonly>
        <% List<String> chatRecords = (List<String>) application.getAttribute("chatRecords");
            if (chatRecords != null) {
                for (String record : chatRecords) {
        %>
                    <%= record %>
        <%
                }
            }
        %>
    </textarea>

    <!-- 信息输入表单 -->
    <form action="ChatServlet" method="post">
        <label for="message">输入消息：</label>
        <div class="input-container">
            <input type="text" id="message" name="message" required>
            <button type="submit">发送</button>
        </div>
    </form>
</div>

</body>

<script src="../js/chatPage.js" charset="UTF-8"></script>
</html>
