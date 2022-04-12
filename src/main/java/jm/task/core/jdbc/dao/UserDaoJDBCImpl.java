package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Util util = new Util();
        try {
            util.getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, nam varchar (64), lastname varchar (100), age int);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Util util = new Util();
        try {
            util.getConnection().createStatement().executeUpdate("DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        try {
            PreparedStatement preparedStatement = util.getConnection().prepareStatement("INSERT INTO users (nam, lastname, age) VALUES (?,?,?);");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.printf("User с именем %s добавлен в базу данных", name);
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Util util = new Util();
        try {
            PreparedStatement preparedStatement = util.getConnection().prepareStatement("DELETE FROM users WHERE id=?;");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Util util = new Util();
        User user = new User();
        try {
            ResultSet resultSet = util.getConnection().createStatement().executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("nam"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        Util util = new Util();
        try {
            util.getConnection().createStatement().executeUpdate("DELETE FROM users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
