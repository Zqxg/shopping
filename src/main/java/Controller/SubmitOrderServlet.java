package Controller;

import Model.ClassMethod.ProductDateBase;
import Model.domain.Product;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/user/SubmitOrderServlet")
public class SubmitOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //是否成功进入 Servlet
        System.out.println("Servlet doPost method called");

        // 获取购物车数据
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // 解析 JSON 数据为 List<Product>，需要根据你的数据结构进行修改
        List<Product> cartData = parseJsonToProductList(requestBody.toString());

        //订单商品总价格
        double totalPrice = calculateTotalPrice(cartData);




        // 订单处理逻辑
        boolean orderSuccess = false;

        // 判断订单处理是否成功
        if (!cartData.isEmpty() && totalPrice > 0) {
            // 存储数据到Request中
            orderSuccess = true;
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("cartData", cartData);

            // 创建 ProductDateBase 实例并更新数据库中的产品数量
            ProductDateBase productDateBase = new ProductDateBase();
            for (Product product : cartData) {
                int productId = product.getProductId();
                int quantity = product.getProductQuantity();
                productDateBase.reduceProductQuantity(productId, quantity);
            }

        }

        //返回页面
        if (orderSuccess) {
            System.out.println("111");
            // 订单处理成功，返回成功标志
            // 转发到 JSP 页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("./submitOrder.jsp");
            dispatcher.forward(request, response);

        } else {
            // 订单处理失败，返回错误信息
            response.getWriter().write("订单提交失败，请重试！");
        }
    }

    // 接受一个表示 JSON 数据的字符串作为参数，并返回一个 List<Product> 对象。
    private List<Product> parseJsonToProductList(String json) {
        List<Product> productList = new ArrayList<>();
        try {
            JSONArray jsonArray = JSONArray.parseArray(json);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int productId = jsonObject.getIntValue("productId");
                String productName = jsonObject.getString("productName");
                double productPrice = jsonObject.getDoubleValue("productPrice");
                int productQuantity = jsonObject.getIntValue("productQuantity");  // 添加获取商品数量的代码
                Product product = new Product(productId, productName, productPrice, productQuantity);
                productList.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productList;
    }

    // 计算订单商品总价格的方法
    private double calculateTotalPrice(List<Product> cartData) {
        double totalPrice = 0;
        for (Product product : cartData) {
            totalPrice += product.getProductPrice() * product.getProductQuantity();
        }
        return totalPrice;
    }

}



