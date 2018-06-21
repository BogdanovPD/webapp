package com.bogdanovpd.webapp.service;

import com.bogdanovpd.webapp.model.User;
import com.bogdanovpd.webapp.model.UserRoles;

import java.sql.Connection;
import java.util.List;

public interface DBService {
    void addUser(String login, String pass, String firstName, String lastName, UserRoles role);

    void updateUser(long id, String login, String pass, String firstName, String lastName, UserRoles role);

    void deleteUser(long id);

    List<User> getAllUsers();

    User getUserById(long id);

    User getUserByLogin(String login);

}
