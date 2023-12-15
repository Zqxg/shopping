<%@ page import="Model.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.ClassMethod.ProductDateBase" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商城首页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/homePage_style.css">
    <script src="${pageContext.request.contextPath}/js/homePage.js" charset="GBK"></script>
</head>
<body>

<div class="header-container">
    <!-- 商城logo -->
    <div class="logo-container">
        <img src="${pageContext.request.contextPath}/img/home_logo.png" alt="商城Logo">
    </div>

    <!-- 搜索框和搜索按钮 -->
    <div class="search-container">
        <form>
            <input type="text" placeholder="请输入关键字">
            <button type="submit">搜索</button>
        </form>
    </div>

    <!-- 用户图标和显示用户名 -->
    <div class="user-container">
        <img src="${pageContext.request.contextPath}/img/user_logo.png" alt="用户">
        <span>Hi! ${sessionScope.username}</span> <!-- 显示用户名称的地方，使用后端传递的用户名 -->
    </div>

</div>

<!-- 分类和轮播图 -->
<div class="category-container">
    <!-- 分类内容 -->
    <div class="category">
        <div class="category-item">女装</div>
        <div class="category-item">男装</div>
        <div class="category-item">运动</div>
        <div class="category-item">数码</div>
        <div class="category-item">手机</div>
        <div class="category-item">百货</div>
        <div class="category-item">家装</div>
        <div class="category-item">生鲜</div>
        <div class="category-item">医药</div>
        <div class="category-item">电器</div>
        <div class="category-item">车品</div>
        <div class="category-item">箱包</div>
        <div class="category-item">女鞋</div>
        <div class="category-item">男鞋</div>
    </div>


    <div class="carousel-container">
        <!-- 轮播图 -->
        <div class="carousel">
            <!-- 轮播图内容 -->
            <div class="carousel-item"><img src="https://img0.baidu.com/it/u=2257065295,224548003&fm=253&fmt=auto&app=138&f=JPEG?w=714&h=500" alt="轮播图1"></div>
            <div class="carousel-item"><img src="https://photo.16pic.com/00/61/34/16pic_6134865_b.jpg" alt="轮播图2"></div>
            <div class="carousel-item"><img src="https://picnew13.photophoto.cn/20181123/shuangshierdianshanghaibao-31014753_1.jpg" alt="轮播图3"></div>
            <!-- 其他轮播图项... -->
        </div>
    </div>
</div>

<!-- 猜你喜欢 -->
<div class="recommendation-container">
    <h2>猜你喜欢</h2>
    <div class="product-container-wrapper">
        <c:forEach var="product" items="${productList}">
            <div class="product-container">
                <img src="${product.productImage}" alt="${product.productName}">
                <div class="text-container">
                    <h3>${product.productName}</h3>
                    <p class="price">价格: $ ${product.productPrice}</p>
                    <p class="quantity">库存: ${product.productQuantity}</p>
                    <button class="add-to-cart-btn" onclick="addToCart('${product.productId}', '${product.productName}', ${product.productPrice})">+</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- 购物车容器 -->
<div id="cart-container" class="cart-container">
    <div id="cart-text" class="cart-text">
        <!-- 购物车内容将在此显示 -->
    </div>

    <div>
        <!-- 提交按钮 -->
        <button id="submit-btn" class="submit-btn" onclick="submitOrder()">提交</button>
        <!-- 清空按钮 -->
        <button id="clear-btn" class="clear-btn" onclick="clearCart()">清空</button>

    </div>
</div>

<!-- 购物车按钮 -->
<button id="cart-btn" class="cart-btn" onclick="toggleCart()">
    <img src="${pageContext.request.contextPath}/img/shop_logo.png" alt="购物车">
</button>

<!-- 在线聊天按钮 -->
<button class="chat-btn" onclick="openChatPage()">
    <img src="${pageContext.request.contextPath}/img/chat.png" alt="聊天">
</button>

</body>
</html>
