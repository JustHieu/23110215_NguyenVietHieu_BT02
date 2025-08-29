package vn.hieunguyen.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  private static final String COOKIE_REMEMBER = "remember";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // 1) Hủy session
    HttpSession session = req.getSession(false);
    if (session != null) session.invalidate();

    // 2) Xóa cookie remember
    Cookie c = new Cookie(COOKIE_REMEMBER, "");
    c.setPath("/");
    c.setMaxAge(0);
    c.setHttpOnly(true);
    // c.setSecure(true); // bật nếu dùng HTTPS
    resp.addCookie(c);

    // (Tùy chọn) xóa JSESSIONID để back không dùng lại phiên cũ
    Cookie js = new Cookie("JSESSIONID", "");
    js.setPath("/");
    js.setMaxAge(0);
    resp.addCookie(js);

    // 3) Về trang login
    resp.sendRedirect(req.getContextPath() + "/login");
  }
}
