package com.bogdanovpd.webapp.dao;

import com.bogdanovpd.webapp.model.User;
import com.bogdanovpd.webapp.model.UserRoles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDAOHbImpl implements UserDAO {

    SessionFactory sessionFactory;

    public UserDAOHbImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(String login, String password, String firstName, String lastName, UserRoles role) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(login, password, firstName, lastName, role);
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void updateUser(long id, String login, String password, String firstName, String lastName, UserRoles role) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(id, login, password, firstName, lastName, role);
            session.update(user);
            transaction.commit();
        }
    }

    @Override
    public void deleteUser(long id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(getUserById(id));
            transaction.commit();
        }
    }

    @Override
    public List<User> selectAllUsers() {
        try(Session session = sessionFactory.openSession()) {
            Query<User> q = session.createQuery("FROM User");
            return q.getResultList();
        }
    }

    @Override
    public User getUserByLogin(String login) {
        try(Session session = sessionFactory.openSession()) {
            Query<User> q = session.createQuery("FROM User where login=:login");
            q.setParameter("login", login);
            List<User> list = q.getResultList();
            if (list.isEmpty()) {
                return null;
            }
            return q.getSingleResult();
        }
    }

    @Override
    public User getUserById(long id) {
        try(Session session = sessionFactory.openSession()) {
            return session.find(User.class, id);
        }
    }
}
