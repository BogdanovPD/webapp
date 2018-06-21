package com.bogdanovpd.webapp.controller;

import com.bogdanovpd.webapp.service.DBService;
import com.bogdanovpd.webapp.service.DBServiceImpl;
import com.bogdanovpd.webapp.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteServlet extends HttpServlet {

    //private final DBService service = new DBServiceImpl();
    private final DBService service = DBServiceImpl.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Utils.getUserId(req.getParameter("userId"));
        if (userId != 0) {
            service.deleteUser(userId);
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect("/");
        } else {
            Utils.sendBadRedirectToMain(resp);
        }
    }
}
