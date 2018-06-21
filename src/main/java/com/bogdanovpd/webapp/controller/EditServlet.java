package com.bogdanovpd.webapp.controller;

import com.bogdanovpd.webapp.model.UserRoles;
import com.bogdanovpd.webapp.service.DBService;
import com.bogdanovpd.webapp.model.User;
import com.bogdanovpd.webapp.service.DBServiceImpl;
import com.bogdanovpd.webapp.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateUser")
public class EditServlet extends HttpServlet {

    private final DBService service = DBServiceImpl.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdStr = req.getParameter("userId");
        long userId = Utils.getUserId(userIdStr);
        if (userId != 0){
            User user = service.getUserById(userId);
            req.setAttribute("user", user);
            req.setAttribute("roles", UserRoles.values());
            req.setAttribute("selectedRole", user.getRole());
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
        } else {
            Utils.sendBadRedirectToMain(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Utils.getUserId(req.getParameter("userId"));
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String role = req.getParameter("role");
        boolean parametersPassed = Utils.checkParameters(login, pass,role);
        if (userId != 0 && parametersPassed) {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            service.updateUser(userId, login, pass, firstName, lastName,UserRoles.valueOf(role));
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect("/admin");
        } else {
            Utils.sendBadRedirectToMain(resp);
        }
    }
}
