<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单提交</title>
</head>
<body>

<h2>订单详情</h2>

<script>
    // JavaScript 函数，用于在订单提交页面获取购物车数据并显示
    document.addEventListener('DOMContentLoaded', function () {
        // 从 URL 中获取购物车数据参数
        var cartDataParam = new URLSearchParams(window.location.search).get('cartData');

        // 检查是否有购物车数据参数
        if (cartDataParam) {
            // 解码 JSON 字符串
            var cartData = JSON.parse(decodeURIComponent(cartDataParam));

            // 遍历购物车数据并显示在页面上（这里只是简单示例，具体应根据页面结构进行调整）
            cartData.forEach(function (product) {
                document.body.innerHTML += '<p>' + product.productName + ' - $' + product.productPrice + '</p>';
            });
        } else {
            document.body.innerHTML += '<p>没有购物车数据。</p>';
        }
    });
</script>

</body>
</html>
