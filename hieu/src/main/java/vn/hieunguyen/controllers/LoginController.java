package vn.hieunguyen.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.hieunguyen.services.UserService;
import vn.hieunguyen.serviceimplement.UserServiceImpl;
import vn.hieunguyen.models.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private static final String COOKIE_REMEMBER = "remember";
    private static final int REMEMBER_MAX_AGE = 30 * 24 * 60 * 60; // 30 ngày

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Đã đăng nhập?
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
        	resp.sendRedirect(req.getContextPath() + "/admin/home");
            return;
        }

        // Thử khôi phục từ cookie "remember"
        User userFromCookie = getUserFromRememberCookie(req);
        if (userFromCookie != null) {
            session = req.getSession(true);
            session.setAttribute("account", userFromCookie);
            resp.sendRedirect(req.getContextPath() + "/admin/home");
            return;
        }

        // Chưa login -> mở trang login
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = trim(req.getParameter("username"));
        String password = trim(req.getParameter("password"));
        boolean isRememberMe = "on".equals(req.getParameter("remember"));

        if (username.isEmpty() || password.isEmpty()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        User user = userService.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);

            if (isRememberMe) {
                saveRememberMe(resp, username);
            } else {
                clearRememberMe(resp); // nếu bỏ tick, xóa cookie cũ (nếu có)
            }

            resp.sendRedirect(req.getContextPath() + "/admin/home");
            } else {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    private void saveRememberMe(HttpServletResponse resp, String username) {
        Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
        cookie.setHttpOnly(true);
        cookie.setPath("/");                 // để mọi URL đều đọc được
        cookie.setMaxAge(REMEMBER_MAX_AGE);  // 30 ngày
        resp.addCookie(cookie);
    }

    private void clearRememberMe(HttpServletResponse resp) {
        Cookie cookie = new Cookie(COOKIE_REMEMBER, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }

    private User getUserFromRememberCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        for (Cookie c : cookies) {
            if (COOKIE_REMEMBER.equals(c.getName())) {
                String username = trim(c.getValue());
                if (!username.isEmpty()) {
                    // Lấy user theo username để set vào session
                    return userService.get(username);
                }
            }
        }
        return null;
    }

    private static String trim(String s) {
        return (s == null) ? "" : s.trim();
    }
}
