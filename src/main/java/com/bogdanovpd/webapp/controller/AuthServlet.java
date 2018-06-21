package com.bogdanovpd.webapp.controller;

import com.bogdanovpd.webapp.model.User;
import com.bogdanovpd.webapp.model.UserRoles;
import com.bogdanovpd.webapp.service.DBService;
import com.bogdanovpd.webapp.service.DBServiceImpl;
import com.bogdanovpd.webapp.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Auth", urlPatterns = {"/", "/auth"})
public class AuthServlet extends HttpServlet {

    private final DBService service = DBServiceImpl.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        boolean parametersPassed = Utils.checkParameters(login, password);
        if (parametersPassed) {
            User user = service.getUserByLogin(login);
            if (user == null || !password.equals(user.getPassword())){
                req.setAttribute("userNotFound", "User is not found. Login or password is incorrect!");
                req.getRequestDispatcher("auth.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("authUser", user);
                if (user.getRole().equals(UserRoles.admin)) {
                    resp.sendRedirect("/admin");
                }
                if (user.getRole().equals(UserRoles.user)) {
                    resp.sendRedirect("/user");
                }
            }
        } else {
            req.getRequestDispatcher("auth.jsp").forward(req, resp);
        }
    }
}
