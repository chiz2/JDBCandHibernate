package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final Session session;

    public UserDaoHibernateImpl(Session session) {
        this.session = session;
    }


    @Override
    public void createUsersTable() {

       // Query query = session.createQuery(createTableUser);
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
