package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/ChatServlet")
public class ChatServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取用户输入的聊天信息
        String message = request.getParameter("message");

        // 获取当前用户的用户名（在用户登录时设置了用户名到 Session 中）
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // 获取全局的聊天记录
        List<String> chatRecords = getChatRecords(request);

        // 将用户名和聊天信息添加到聊天记录中
        String chatEntry = username + ": " + message;
        chatRecords.add(chatEntry);

        // 更新 Servlet 上下文中的聊天记录
        setChatRecords(request, chatRecords);

        // 重定向回聊天室页面
        response.sendRedirect("chatPage.jsp");
    }

    private List<String> getChatRecords(HttpServletRequest request) {
        // 获取 Servlet 上下文中的聊天记录
        List<String> chatRecords = (List<String>) getServletContext().getAttribute("chatRecords");

        // 如果不存在，创建一个新的聊天记录列表
        if (chatRecords == null) {
            chatRecords = new ArrayList<>();
            setChatRecords(request, chatRecords);
        }

        return chatRecords;
    }

    private void setChatRecords(HttpServletRequest request, List<String> chatRecords) {
        // 设置 Servlet 上下文中的聊天记录
        getServletContext().setAttribute("chatRecords", chatRecords);
    }
}
