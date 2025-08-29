package vn.hieunguyen.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hieunguyen.dao.ResetTokenDao;
import vn.hieunguyen.dao.UserDao;
import vn.hieunguyen.daoimplement.ResetTokenDaoImpl;
import vn.hieunguyen.daoimplement.UserDaoImpl;

@WebServlet(urlPatterns = "/forgot")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final long EXP_MIN = 30;

    private final UserDao userDao = new UserDaoImpl();
    private final ResetTokenDao tokenDao = new ResetTokenDaoImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");

        Integer userId = userDao.findUserIdByEmail(email);
        String msg = "Nếu email tồn tại, liên kết đặt lại đã được gửi.";

        if (userId != null) {
            String token = UUID.randomUUID().toString().replace("-", "");
            Timestamp exp = Timestamp.from(Instant.now().plusSeconds(EXP_MIN * 60));
            tokenDao.save(userId, token, exp);

            String link = buildBaseUrl(req) + "/reset?token=" + token;

            try {
                vn.hieunguyen.utils.MailUtil.send(
                        email,
                        "Đặt lại mật khẩu",
                        "<p>Nhấn vào liên kết (hiệu lực " + EXP_MIN + " phút):</p>"
                        + "<p><a href='"+link+"'>"+link+"</a></p>");
            } catch (Exception e) {
                e.printStackTrace();
                msg = "Không gửi được email: " + e.getMessage();
            }
        }

        req.setAttribute("msg", msg);
        req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
    }

    private String buildBaseUrl(HttpServletRequest req) {
        String scheme = req.getScheme(), host = req.getServerName(), ctx = req.getContextPath();
        int port = req.getServerPort();
        boolean def = ("http".equalsIgnoreCase(scheme) && port == 80)
                   || ("https".equalsIgnoreCase(scheme) && port == 443);
        return scheme + "://" + host + (def ? "" : ":" + port) + ctx;
    }
}
