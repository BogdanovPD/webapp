package com.bogdanovpd.webapp.dao;

import com.bogdanovpd.webapp.model.User;
import com.bogdanovpd.webapp.model.UserRoles;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void addUser(String login, String password, String firstName, String lastName, UserRoles role);

    void updateUser(long id, String login, String password, String firstName, String lastName, UserRoles role);

    void deleteUser(long id);

    List<User> selectAllUsers();

    User getUserById(long id);

    User getUserByLogin(String login);
}
