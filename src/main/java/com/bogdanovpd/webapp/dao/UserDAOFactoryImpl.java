package com.bogdanovpd.webapp.dao;

import com.bogdanovpd.webapp.util.DBHelper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.*;
import java.sql.Connection;
import java.util.Properties;

public class UserDAOFactoryImpl implements  UserDAOFactory {

    private static final String JDBC = "jdbc";
    private static final String HIBERNATE = "hibernate";

    @Override
    public UserDAO createJdbcUserDAO() {
        Connection conn = DBHelper.getConnection();
        return new UserDAOJdbcImpl(conn);
    }

    @Override
    public UserDAO createHibernateUserDAO() {
        Configuration configuration = DBHelper.getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return new UserDAOHbImpl(sessionFactory);
    }

    public UserDAO getDao(){
        Properties properties = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("db.property")){
            properties.load(in);
            String value = properties.getProperty("db", JDBC);
            switch (value.toLowerCase()){
                case JDBC:
                    return createJdbcUserDAO();
                case HIBERNATE:
                    return createHibernateUserDAO();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
