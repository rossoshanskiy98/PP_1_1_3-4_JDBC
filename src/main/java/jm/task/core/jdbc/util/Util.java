package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Util instance;
    private final String URL = "jdbc:mysql://localhost:3306/dbtest";
    private final String USERNAME = "root64";
    private final String PASSWORD = "root64";
    private Connection connection;

    private Util() {
        try  {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static Util getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Util();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
