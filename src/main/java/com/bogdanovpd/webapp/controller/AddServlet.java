package com.bogdanovpd.webapp.controller;

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
import java.util.Arrays;

@WebServlet("/addUser")
public class AddServlet extends HttpServlet {

    private final DBService service = DBServiceImpl.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", UserRoles.values());
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        String role = req.getParameter("role");
        boolean parametersPassed = Utils.checkParameters(login, password, role);
        if (parametersPassed) {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            service.addUser(login, password, firstName, lastName, UserRoles.valueOf(role));
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect("/admin");
        } else {
            req.getRequestDispatcher("add.jsp").forward(req, resp);
        }
    }
}
