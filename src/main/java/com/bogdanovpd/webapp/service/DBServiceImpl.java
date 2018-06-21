package com.bogdanovpd.webapp.service;

import com.bogdanovpd.webapp.dao.UserDAO;
import com.bogdanovpd.webapp.dao.UserDAOFactoryImpl;
import com.bogdanovpd.webapp.model.User;
import com.bogdanovpd.webapp.model.UserRoles;

import java.util.List;

public class DBServiceImpl implements DBService {

    public static final DBServiceImpl INSTANCE = new DBServiceImpl();

    private UserDAO dao;

    private DBServiceImpl() {
        dao = new UserDAOFactoryImpl().getDao();
    }

    @Override
    public void addUser(String login, String pass, String firstName, String lastName, UserRoles role){
        dao.addUser(login, pass, firstName, lastName, role);
    }

    @Override
    public void updateUser(long id, String login, String pass, String firstName, String lastName, UserRoles role){
        dao.updateUser(id, login, pass, firstName, lastName, role);
    }

    @Override
    public void deleteUser(long id) {
        dao.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.selectAllUsers();
    }

    @Override
    public User getUserById(long id){
        return dao.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return dao.getUserByLogin(login);
    }
}
