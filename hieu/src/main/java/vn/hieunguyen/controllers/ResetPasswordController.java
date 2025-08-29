package vn.hieunguyen.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hieunguyen.dao.ResetTokenDao;
import vn.hieunguyen.dao.UserDao;
import vn.hieunguyen.daoimplement.ResetTokenDaoImpl;
import vn.hieunguyen.daoimplement.UserDaoImpl;

@WebServlet(urlPatterns = "/reset")
public class ResetPasswordController extends HttpServlet {
    private final ResetTokenDao tokenDao = new ResetTokenDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String token = req.getParameter("token");
        Timestamp exp = tokenDao.findExpiryByToken(token);
        if (token == null || exp == null || exp.before(Timestamp.from(Instant.now()))) {
            req.setAttribute("error", "Liên kết không hợp lệ hoặc đã hết hạn.");
        }
        req.getRequestDispatcher("/views/reset.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String token = req.getParameter("token");
        String pass  = req.getParameter("password");
        String re    = req.getParameter("retype");

        Timestamp exp = tokenDao.findExpiryByToken(token);
        if (token == null || exp == null || exp.before(Timestamp.from(Instant.now()))) {
            req.setAttribute("error", "Liên kết không hợp lệ hoặc đã hết hạn.");
            req.getRequestDispatcher("/views/reset.jsp").forward(req, resp);
            return;
        }
        if (pass == null || !pass.equals(re) || pass.length() < 6) {
            req.setAttribute("error", "Mật khẩu không hợp lệ hoặc không khớp.");
            req.getRequestDispatcher("/views/reset.jsp?token="+token).forward(req, resp);
            return;
        }

        Integer userId = tokenDao.findUserIdByToken(token);
        String hashed = pass;
        userDao.updatePasswordById(userId, hashed);
        tokenDao.delete(token);

        resp.sendRedirect(req.getContextPath()+"/login?reset=success");
    }
}
