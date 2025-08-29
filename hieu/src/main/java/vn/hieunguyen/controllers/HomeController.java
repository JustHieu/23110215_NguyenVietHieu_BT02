package vn.hieunguyen.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/admin/home")
public class HomeController extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession s = req.getSession(false);
    if (s == null || s.getAttribute("account") == null) {
      resp.sendRedirect(req.getContextPath() + "/login");
      return;
    }
    req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
  }
}
