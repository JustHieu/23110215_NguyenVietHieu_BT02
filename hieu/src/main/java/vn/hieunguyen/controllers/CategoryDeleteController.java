package vn.hieunguyen.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hieunguyen.models.User;
import vn.hieunguyen.serviceimplement.CategoryServiceImpl;

@WebServlet(urlPatterns={"/admin/category/delete"})
public class CategoryDeleteController extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  private final CategoryServiceImpl service = new CategoryServiceImpl();

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession s = req.getSession(false);
    User u = (User) s.getAttribute("account");
    int id = Integer.parseInt(req.getParameter("id"));
    service.delete(id, u.getId());
    resp.sendRedirect(req.getContextPath() + "/admin/category/list");
  }
}
