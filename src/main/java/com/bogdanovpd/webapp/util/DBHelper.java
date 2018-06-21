package com.bogdanovpd.webapp.util;

import com.bogdanovpd.webapp.model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

    public static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/web_app_db?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/web_app_db?serverTimezone=UTC";
            String name = "root";
            String pass = "root";

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void createTable(Connection conn) {
        try(Statement statement = conn.createStatement()) {
            statement.execute("create table if not exists users (id bigint auto_increment NOT NULL , login varchar(256) NOT NULL," +
                     " password varchar(256) NOT NULL, first_name varchar(256), second_name varchar(256), primary key (id));");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
