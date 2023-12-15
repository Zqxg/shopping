

let currentIndex = 0;

function showSlide(index) {
    const slides = document.querySelectorAll('.carousel-item');
    slides.forEach((slide, i) => {
        slide.style.display = i === index ? 'block' : 'none';
    });
}

function nextSlide() {
    const slides = document.querySelectorAll('.carousel-item');
    currentIndex = (currentIndex + 1) % slides.length;
    showSlide(currentIndex);
}

function prevSlide() {
    const slides = document.querySelectorAll('.carousel-item');
    currentIndex = (currentIndex - 1 + slides.length) % slides.length;
    showSlide(currentIndex);
}

// 初始显示第一张轮播图
showSlide(currentIndex);

// 每隔一定时间切换轮播图（例如，每 3 秒切换一次）
setInterval(nextSlide, 3000);


// 存储已添加到购物车的商品
let cartItems = [];

// 添加到购物车的函数
function addToCart(productId, productName, productPrice) {
    // 检查购物车中是否已存在相同的商品
    const existingItem = cartItems.find(item => item.productId === productId);

    if (existingItem) {
        // 商品已存在，增加数量
        existingItem.productQuantity++;
    } else {
        // 商品不存在，创建新的购物车项并添加到数组中
        const newItem = { productId, productName, productPrice, productQuantity: 1 };
        cartItems.push(newItem);
    }

    // 更新购物车显示
    updateCart();
}

// 更新购物车内容的函数
function updateCart() {
    const cartText = document.getElementById('cart-text');
    cartText.innerHTML = '';

    cartItems.forEach(item => {
        const cartItem = document.createElement('div');
        cartItem.innerHTML = `${item.productName} : $ ${item.productPrice} x ${item.productQuantity}`;
        cartText.appendChild(cartItem);  // 使用 cartText
    });
}



// 切换显示/隐藏购物车的函数
function toggleCart() {
    const cartContainer = document.getElementById('cart-container');

    // 获取计算后的样式
    const cartContainerStyle = window.getComputedStyle(cartContainer);

    if (cartContainerStyle.display === 'block') {
        cartContainer.style.display = 'none'; // 隐藏购物车容器
    } else {
        cartContainer.style.display = 'block'; // 显示购物车容器
    }
}

// 提交按钮点击事件处理函数
function submitOrder() {
    // 获取购物车数据
    var cartData = getCartData();

    // 判断购物车是否为空
    if (cartData.length === 0) {
        alert("购物车没有商品，请先添加商品到购物车！");
        return;
    }

    // 发送 AJAX 请求到后端 Servlet
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/shopping/user/SubmitOrderServlet', true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // 请求成功
                console.log("后端返回的内容：" + xhr.responseText);

                // 判断响应是否包含特定的 HTML 标签
                if (xhr.responseText.includes('<html>')) {
                    alert("提交购物车成功！");

                    // 创建一个新的窗口或标签
                    var newWindow = window.open("/shopping/submitOrder.jsp", "");
                    // 将 JSP 返回的内容写入新的窗口或标签
                    newWindow.document.write(xhr.responseText);
                    // 关闭新窗口的自动导入的文档，以保留你写入的内容
                    newWindow.document.close();

                    // 刷新页面或者更新购物车容器的显示
                    // location.reload();

                } else {
                    // 处理其他情况
                    alert("提交购物车失败，请重试！");
                }
            } else {
                // 请求失败，处理错误情况
                alert("提交购物车失败，请重试！");
            }
        }
    };

    // 将购物车数据转为 JSON 字符串并发送
    xhr.send(JSON.stringify(cartData));

}


// 获取购物车数据的函数，你需要根据实际情况实现
function getCartData() {
    // 这里假设购物车数据保存在名为 cartItems 的变量中
    return cartItems;
}


// 清空按钮点击事件处理函数
function clearCart() {
    // 清空购物车数据的逻辑，这里假设有一个clearCartData函数用于清空购物车
    clearCartData();

    // 刷新页面或者更新购物车容器的显示
    location.reload();
    // 或者更新购物车容器的显示
    // updateCartDisplay();
}


// 清空购物车数据的函数，你需要根据实际情况实现
function clearCartData() {
    // 将购物车数组清空
    cartItems = [];
    // 更新购物车显示
    updateCart();
}

function openChatPage() {
    // 通过跳转到聊天室页面的URL
    window.location.href = './chatPage.jsp';
}
