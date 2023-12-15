package Controller;

import Model.ClassMethod.ProductDateBase;
import Model.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 初始化变量以存储用户名
        String username = null;
        // 从请求中获取用户名
        request.getSession().getAttribute("username");

        // 在这里可以调用数据库获取商品列表
        List<Product> productList = getProductListFromDatabase();

        // 将商品列表设置为请求属性
        request.setAttribute("productList", productList);
        // 输出一些调试信息
        System.out.println("Product List Size: " + productList.size());

        // 转发到JSP页面
        try {
            request.getRequestDispatcher("/WEB-INF/view/homePage.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    // 示例的获取商品列表的方法
    private List<Product> getProductListFromDatabase() {
        //从数据库中获取商品列表
        return new ProductDateBase().getAllProducts();
    }
}

