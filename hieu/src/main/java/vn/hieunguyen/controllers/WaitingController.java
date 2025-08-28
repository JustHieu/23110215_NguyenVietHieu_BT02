package vn.hieunguyen.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.hieunguyen.models.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User u = (User) session.getAttribute("account");
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Có thể set attribute để JSP hiển thị chào mừng
        req.setAttribute("username", u.getUserName());

        if (u.getRoleid() == 1) {
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        } else if (u.getRoleid() == 2) {
            resp.sendRedirect(req.getContextPath() + "/manager/home");
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}
