package Controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.ServletContext;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class OnlineUserCounter implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();

        // 检查用户信息是否存在于 Session 中
        if (session.getAttribute("username") != null) {
            // 用户信息存在，可能是页面刷新，不进行计数
            return;
        }

        // 在线人数加1
        incrementOnlineUsers(se.getSession().getServletContext());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 会话销毁时，减少在线人数计数
        ServletContext servletContext = se.getSession().getServletContext();
        decrementOnlineUsers(servletContext);
    }

    private void incrementOnlineUsers(ServletContext servletContext) {
        AtomicInteger onlineUsers = (AtomicInteger) servletContext.getAttribute("onlineUsers");
        if (onlineUsers == null) {
            onlineUsers = new AtomicInteger(-1);//本身创建的时候就+1了，不知道啥bug
        }
        onlineUsers.incrementAndGet();
        servletContext.setAttribute("onlineUsers", onlineUsers);
    }

    private void decrementOnlineUsers(ServletContext servletContext) {
        AtomicInteger onlineUsers = (AtomicInteger) servletContext.getAttribute("onlineUsers");
        if (onlineUsers != null && onlineUsers.get() > 0) {
            onlineUsers.decrementAndGet();
            servletContext.setAttribute("onlineUsers", onlineUsers);
        }
    }
}
