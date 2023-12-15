package Controller;

import Model.ClassMethod.UserDateBase;
import Model.domain.User;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/RegisterServlet", "/register"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应内容类型
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取注册表单的用户名和密码
        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");

        // 在这里，进行用户名和密码的验证逻辑
        if (isValidUsernameFormat(newUsername) && isValidRegistration(newUsername)) {
            // 进行注册
            addUserToDatabase(newUsername, newPassword);
            // 发送JavaScript代码以显示警示窗口 重定向到登录页面
            response.getWriter().println("<script>alert('注册成功'); window.location='./login.jsp';</script>");

        } else {
            // 注册验证失败，可以进行适当的错误处理，例如返回错误消息
            response.getWriter().println("注册失败，用户名已存在或格式不正确");
        }
    }
    private boolean isValidUsernameFormat(String newUsername) {
        // 在这里添加对用户名格式的验证逻辑，例如长度、字符等条件
        // 返回 true 表示格式正确，返回 false 表示格式不正确
        // 这里的示例逻辑为用户名长度至少为3个字符
        return newUsername != null && newUsername.length() >= 3;
    }

    // 注册验证逻辑
    private boolean isValidRegistration(String newUsername) {
        // 使用 UserDateBase 类中的 findUser 方法查询用户信息
        User user = new UserDateBase().findUser(newUsername);

        // 如果找不到用户，说明用户没有注册过 返回 true
        if (user.getUsername() == null){
            return true;
        }
        else return false ;
    }

    // 示例的将用户信息添加到数据库的逻辑
    private void addUserToDatabase(String newUsername, String newPassword) {
        // 使用 UserDateBase 类中的 findUser 方法查询用户信息
        new UserDateBase().addUser(newUsername,newPassword);
    }
}
