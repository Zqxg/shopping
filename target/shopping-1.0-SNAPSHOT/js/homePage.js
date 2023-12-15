

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

// ��ʼ��ʾ��һ���ֲ�ͼ
showSlide(currentIndex);

// ÿ��һ��ʱ���л��ֲ�ͼ�����磬ÿ 3 ���л�һ�Σ�
setInterval(nextSlide, 3000);


// �洢����ӵ����ﳵ����Ʒ
let cartItems = [];

// ��ӵ����ﳵ�ĺ���
function addToCart(productId, productName, productPrice) {
    // ��鹺�ﳵ���Ƿ��Ѵ�����ͬ����Ʒ
    const existingItem = cartItems.find(item => item.productId === productId);

    if (existingItem) {
        // ��Ʒ�Ѵ��ڣ���������
        existingItem.productQuantity++;
    } else {
        // ��Ʒ�����ڣ������µĹ��ﳵ���ӵ�������
        const newItem = { productId, productName, productPrice, productQuantity: 1 };
        cartItems.push(newItem);
    }

    // ���¹��ﳵ��ʾ
    updateCart();
}

// ���¹��ﳵ���ݵĺ���
function updateCart() {
    const cartText = document.getElementById('cart-text');
    cartText.innerHTML = '';

    cartItems.forEach(item => {
        const cartItem = document.createElement('div');
        cartItem.innerHTML = `${item.productName} : $ ${item.productPrice} x ${item.productQuantity}`;
        cartText.appendChild(cartItem);  // ʹ�� cartText
    });
}



// �л���ʾ/���ع��ﳵ�ĺ���
function toggleCart() {
    const cartContainer = document.getElementById('cart-container');

    // ��ȡ��������ʽ
    const cartContainerStyle = window.getComputedStyle(cartContainer);

    if (cartContainerStyle.display === 'block') {
        cartContainer.style.display = 'none'; // ���ع��ﳵ����
    } else {
        cartContainer.style.display = 'block'; // ��ʾ���ﳵ����
    }
}

// �ύ��ť����¼�������
function submitOrder() {
    // ��ȡ���ﳵ����
    var cartData = getCartData();

    // �жϹ��ﳵ�Ƿ�Ϊ��
    if (cartData.length === 0) {
        alert("���ﳵû����Ʒ�����������Ʒ�����ﳵ��");
        return;
    }

    // ���� AJAX ���󵽺�� Servlet
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/shopping/user/SubmitOrderServlet', true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // ����ɹ�
                console.log("��˷��ص����ݣ�" + xhr.responseText);

                // �ж���Ӧ�Ƿ�����ض��� HTML ��ǩ
                if (xhr.responseText.includes('<html>')) {
                    alert("�ύ���ﳵ�ɹ���");

                    // ����һ���µĴ��ڻ��ǩ
                    var newWindow = window.open("/shopping/submitOrder.jsp", "");
                    // �� JSP ���ص�����д���µĴ��ڻ��ǩ
                    newWindow.document.write(xhr.responseText);
                    // �ر��´��ڵ��Զ�������ĵ����Ա�����д�������
                    newWindow.document.close();

                    // ˢ��ҳ����߸��¹��ﳵ��������ʾ
                    // location.reload();

                } else {
                    // �����������
                    alert("�ύ���ﳵʧ�ܣ������ԣ�");
                }
            } else {
                // ����ʧ�ܣ�����������
                alert("�ύ���ﳵʧ�ܣ������ԣ�");
            }
        }
    };

    // �����ﳵ����תΪ JSON �ַ���������
    xhr.send(JSON.stringify(cartData));

}


// ��ȡ���ﳵ���ݵĺ���������Ҫ����ʵ�����ʵ��
function getCartData() {
    // ������蹺�ﳵ���ݱ�������Ϊ cartItems �ı�����
    return cartItems;
}


// ��հ�ť����¼�������
function clearCart() {
    // ��չ��ﳵ���ݵ��߼������������һ��clearCartData����������չ��ﳵ
    clearCartData();

    // ˢ��ҳ����߸��¹��ﳵ��������ʾ
    location.reload();
    // ���߸��¹��ﳵ��������ʾ
    // updateCartDisplay();
}


// ��չ��ﳵ���ݵĺ���������Ҫ����ʵ�����ʵ��
function clearCartData() {
    // �����ﳵ�������
    cartItems = [];
    // ���¹��ﳵ��ʾ
    updateCart();
}

function openChatPage() {
    // ͨ����ת��������ҳ���URL
    window.location.href = './chatPage.jsp';
}
