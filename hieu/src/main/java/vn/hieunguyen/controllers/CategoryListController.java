package vn.hieunguyen.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.hieunguyen.models.User;
import vn.hieunguyen.serviceimplement.CategoryServiceImpl;

@WebServlet(urlPatterns={"/admin/category/list"})
public class CategoryListController extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  private final CategoryServiceImpl service = new CategoryServiceImpl();

  private Integer currentUserId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    HttpSession s = req.getSession(false);
    if (s == null || s.getAttribute("account") == null) {
      resp.sendRedirect(req.getContextPath() + "/login"); return null;
    }
    return ((User)s.getAttribute("account")).getId();
  }

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Integer uid = currentUserId(req, resp); if (uid == null) return;
    req.setAttribute("cateList", service.getAll(uid));
    req.getRequestDispatcher("/views/list-category.jsp").forward(req, resp);
  }
}
