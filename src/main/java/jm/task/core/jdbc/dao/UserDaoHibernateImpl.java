package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import jm.task.core.jdbc.util.Util.*;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Util util;
    private Session session;
    private Transaction transaction;

    public UserDaoHibernateImpl() {
        util = new Util();
    }


    @Override
    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS user (`id` BIGINT UNSIGNED AUTO_INCREMENT, `name` VARCHAR(20) NOT NULL,`lastName` VARCHAR(20) NOT NULL, `age` TINYINT UNSIGNED NOT NULL,  PRIMARY KEY (`id`))";
        try {
            session = util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(createTable)
                    .addEntity(User.class);
            query.executeUpdate();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public void dropUsersTable() {
        String dropTableUser = "DROP TABLE IF EXISTS user";
        try {
            session = util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(dropTableUser);
            query.executeUpdate();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            session = util.getSessionFactory().openSession();
            users = session.createQuery("SELECT a FROM User a", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE user";
        try {
            session = util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(cleanTable);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
