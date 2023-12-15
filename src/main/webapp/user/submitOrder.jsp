<%@ page import="Model.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单提交</title>
    <style>
        /* 全局样式 */
        body {
            background-color: #fff;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h2 {
            color: #333;
        }

        table {
            width: 80%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #ff8c00;
        }

        c\:forEach {
            display: block;
            margin-top: 10px;
            border: 1px solid  #333333;

        }

        p {
            margin-top: 20px;
            font-weight: bold;
            color: #333;
        }

        /* 其他样式 ... */
    </style>
</head>

<body>
<h2>你的订单</h2>


<table border="1">
    <tr>
        <th>商品名称</th>
        <th>数量</th>
        <th>价格</th>
    </tr>

    <%
        List<Product> cartData = (List<Product>) request.getAttribute("cartData");

        if (cartData != null) {
            for (Product product : cartData) {
                System.out.println("Product: " + product.getProductId() + ", " + product.getProductName() + ", " + product.getProductQuantity() + ", " + product.getProductPrice());
            }
        } else {
            System.out.println("Cart Data is null");
        }
    %>



    <c:forEach var="product" items="${cartData}">
        <tr>
            <td>${product.productName}</td>
            <td>${product.productQuantity}</td>
            <td>${product.productPrice}</td>
        </tr>
    </c:forEach>
</table>

<p>商品总价: $${totalPrice}</p>

<script>
    // 处理完订单后刷新原始页面
    window.opener.location.reload();
</script>
</body>
</html>
