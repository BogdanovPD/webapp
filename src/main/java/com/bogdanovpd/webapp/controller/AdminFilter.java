package com.bogdanovpd.webapp.controller;

import com.bogdanovpd.webapp.model.User;
import com.bogdanovpd.webapp.model.UserRoles;
import com.bogdanovpd.webapp.util.Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = "AdminServlet")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = Utils.getAuthUser(servletRequest);
        if (user == null || !user.getRole().equals(UserRoles.admin)) {
            ((HttpServletResponse)servletResponse).sendError(403);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}