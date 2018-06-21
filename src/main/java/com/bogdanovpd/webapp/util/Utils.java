package com.bogdanovpd.webapp.util;

import com.bogdanovpd.webapp.model.User;
import com.bogdanovpd.webapp.service.DBService;
import com.bogdanovpd.webapp.service.DBServiceImpl;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Utils {

    public static boolean checkParameters(String ... args) {
        for (String arg:args) {
            if (arg == null || arg.isEmpty()){
                return false;
            }
        }
        return true;
    }

    public static long getUserId(String userIdStr) {
        long userId = 0;
        try {
            userId = Long.parseLong(userIdStr);
        } catch (NumberFormatException e){
            return userId;
        }
        return userId;
    }

    public static void sendBadRedirectToMain(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.sendRedirect("/");
    }

    public static User getAuthUser(ServletRequest req) {
        Object userObj =  ((HttpServletRequest)req).getSession().getAttribute("authUser");
        if (userObj == null){
            return  null;
        }
        return (User)userObj;
    }

}
