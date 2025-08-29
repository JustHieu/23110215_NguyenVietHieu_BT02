package vn.hieunguyen.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import vn.hieunguyen.models.Category;
import vn.hieunguyen.models.User;
import vn.hieunguyen.serviceimplement.CategoryServiceImpl;
import vn.hieunguyen.utils.Constant;

@WebServlet(urlPatterns={"/admin/category/edit"})
@MultipartConfig
public class CategoryEditController extends HttpServlet {
    private final CategoryServiceImpl service = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User u = (User) session.getAttribute("account");
        int id = Integer.parseInt(req.getParameter("id"));
        Category c = service.get(id, u.getId());
        req.setAttribute("category", c);
        req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        User u = (User) session.getAttribute("account");

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");

        Part part = req.getPart("icon");
        String fileName = null;
        if (part != null && part.getSize() > 0) {
            fileName = System.currentTimeMillis() + "_" + part.getSubmittedFileName();
            File uploadDir = new File(Constant.DIR);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            Files.copy(part.getInputStream(), new File(uploadDir, fileName).toPath(),
                       StandardCopyOption.REPLACE_EXISTING);
        }

        Category c = new Category();
        c.setId(id);
        c.setName(name);
        c.setIcon(fileName); // nếu null thì giữ icon cũ trong DAO/service
        c.setUserId(u.getId());

        service.edit(c);
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}
