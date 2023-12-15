package Controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.ClassMethod.UserDateBase;
import Model.domain.User;

@WebServlet(urlPatterns = {"/LoginServlet", "/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应内容类型
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取文本框和密码框的值 “记住密码”复选框的值
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean remember = request.getParameter("remember") != null;


        // 在这里，进行用户名和密码的验证逻辑
        if (isValidUser(username, password)) {
            // 用户名和密码验证通过

            // 存储用户名到Session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            if (remember) {
                // 获取所有的Cookie
                Cookie[] cookies = request.getCookies();

                // 检查是否已经有相应用户的Cookie
                boolean userCookieExists = false;

                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals(username)) {
                            userCookieExists = true;
                            break;
                        }
                    }
                }

                // 只有当相应的Cookie不存在时才创建新的Cookie
                if (!userCookieExists) {
                    // 创建以编码后的用户名为名称的Cookie
                    String encodedUsername = URLEncoder.encode(username, "UTF-8");
                    Cookie usernameCookie = new Cookie(encodedUsername, username);
                    Cookie passwordCookie = new Cookie(encodedUsername + "_password", password);

                    // 设置Cookie的有效期为7天（以秒为单位）
                    int oneWeekInSeconds = 7 * 24 * 60 * 60;
                    usernameCookie.setMaxAge(oneWeekInSeconds);
                    passwordCookie.setMaxAge(oneWeekInSeconds);

                    // 将Cookie添加到响应
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                }

            }

            // 进一步处理登录成功逻辑，例如重定向到购物车商城主页
            response.sendRedirect("/shopping/user/ProductServlet");

        } else {
            // 用户名和密码验证失败，可以进行适当的错误处理，例如返回错误消息
            response.getWriter().println("登录失败，用户名或密码无效");
        }
    }

    // 用户验证逻辑，根据数据库中的信息进行验证
    private boolean isValidUser(String username, String password) {
        // 使用 UserDateBase 类中的 findUser 方法查询用户信息
        User user = new UserDateBase().findUser(username);

        // 如果找到用户，并且输入的密码与数据库中存储的密码匹配，则返回 true
        return user != null && user.getPassword().equals(password);
    }

}
