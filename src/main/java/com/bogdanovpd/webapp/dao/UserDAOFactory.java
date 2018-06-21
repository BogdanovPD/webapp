package com.bogdanovpd.webapp.dao;

public interface UserDAOFactory {

    UserDAO createJdbcUserDAO();
    UserDAO createHibernateUserDAO();

}
