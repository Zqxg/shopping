package Controller;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user/*") // 拦截以"/user/"开头的URL
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取用户的Session
        HttpSession session = httpRequest.getSession();
        // 获取用户的身份信息，这里简化为从Session中获取
        Object user = session.getAttribute("username");

        // 如果用户未登录，重定向到登录页面
        if (user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        } else {
            // 用户已登录，继续执行请求
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // 销毁方法，可以在这里进行一些清理操作
    }
}
