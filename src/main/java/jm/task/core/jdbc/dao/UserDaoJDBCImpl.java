package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public UserDaoJDBCImpl() {
        try {
            connection = Util.getMySQLConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        String createTableUser = "CREATE TABLE IF NOT EXISTS user (" +
                "  `id` BIGINT UNSIGNED AUTO_INCREMENT," +
                "  `name` VARCHAR(20) NOT NULL," +
                "  `lastName` VARCHAR(20) NOT NULL," +
                "  `age` TINYINT UNSIGNED NOT NULL," +
                "  PRIMARY KEY (`id`));  ";

        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(createTableUser);
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {

            }
        } finally {
            try{
                if (statement != null)
                    connection.close();
            } catch(SQLException se){
            }// do nothing
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }
    }

    public void dropUsersTable() {
        String dropTableUser = "DROP TABLE IF EXISTS user";
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(dropTableUser);
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            try {
                connection.rollback();
            } catch (SQLException  | NullPointerException ignore) {

            }
        } finally {
            try{
                if (statement != null)
                    connection.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if (connection != null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO `pre-project`.`user` (`name`, `lastName`, `age`)  VALUES ('" +
        name + "','" + lastName + "', '" + age + "')";
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(saveUser);
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try{
                connection.setAutoCommit(true);
                if (statement != null)
                    connection.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if (connection != null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }
    }

    public void removeUserById(long id) {
        String deleteUser = "DELETE FROM `pre-project`.`user` WHERE (`id` = '" + id + "')";
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(deleteUser);
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try{
                connection.setAutoCommit(true);
                if (statement != null)
                    connection.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if (connection != null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String getAllUsers = "SELECT * FROM `pre-project`.user";
        try {
            connection = Util.getMySQLConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAllUsers);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try{
                if (statement != null)
                    connection.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if (connection != null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }
        return list;
    }

    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE user";
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(cleanTable);
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try{
                connection.setAutoCommit(true);
                if (statement != null)
                    connection.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if (connection != null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }
    }
}
