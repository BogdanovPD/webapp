package com.bogdanovpd.webapp.dao;

import com.bogdanovpd.webapp.model.User;
import com.bogdanovpd.webapp.model.UserRoles;
import com.bogdanovpd.webapp.util.DBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDAOJdbcImpl implements UserDAO {

    private final Connection conn;

    public UserDAOJdbcImpl(Connection conn) {
        this.conn = conn;
        DBHelper.createTable(conn);
    }

    @Override
    public void addUser(String login, String password, String firstName, String lastName, UserRoles role) {
        try (Statement statement = conn.createStatement()) {
            conn.setAutoCommit(true);
            statement.execute("insert into users (login, password, first_name, second_name, role) values ('"
                    + login + "', '" + password + "', '" + firstName
                    + "', '" + lastName + "', '" + role + "')");
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    @Override
    public void updateUser(long id, String login, String password, String firstName, String lastName, UserRoles role) {
        try (Statement statement = conn.createStatement()) {
            conn.setAutoCommit(true);
            statement.execute("update users set login = '" + login + "', password = '" + password
                    + "', first_name = '" + firstName + "', second_name = '" + lastName
                    + "', role = '" + role + "' where id = " + id);
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    @Override
    public void deleteUser(long id) {
        try (Statement statement = conn.createStatement()) {
            conn.setAutoCommit(true);
            statement.execute("delete from users where id=" + id);
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new LinkedList<>();
        try (Statement statement = conn.createStatement()) {
            statement.execute("select * from users");
            ResultSet rs = statement.getResultSet();
            while (rs.next()){
                User user = new User(rs.getLong("id"), rs.getString("login"),
                        rs.getString("password"), rs.getString("first_name"),
                        rs.getString("second_name"), UserRoles.valueOf(rs.getString("role")));
                users.add(user);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public User getUserById(long id) {
        try(Statement statement = conn.createStatement()){
            statement.execute("select *from users where id=" + id);
            ResultSet rs = statement.getResultSet();
            if (!rs.next()){
                return null;
            }
            return new User(id, rs.getString("login"), rs.getString("password"),
                    rs.getString("first_name"), rs.getString("second_name"), UserRoles.valueOf(rs.getString("role")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  null;
    }

    @Override
    public User getUserByLogin(String login) {
        try(Statement statement = conn.createStatement()){
            statement.execute("select *from users where login='" + login + "'");
            ResultSet rs = statement.getResultSet();
            if (!rs.next()){
                return null;
            }
            return new User(rs.getLong("id"), login, rs.getString("password"),
                    rs.getString("first_name"), rs.getString("second_name"),
                    UserRoles.valueOf(rs.getString("role")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  null;
    }
}
