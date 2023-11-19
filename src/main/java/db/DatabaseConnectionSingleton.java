package db;

import java.sql.*;

public class DatabaseConnectionSingleton {
    private static DatabaseConnectionSingleton instance;
    private Connection connection;
    private DatabaseConnectionSingleton()  {
        try {
            DatabaseConfiguration.testingTheDatabase();

            this.connection = DriverManager.getConnection(
                    DatabaseConstants.ACCESS_DB,
                    DatabaseConstants.USERNAME,
                    DatabaseConstants.PASSWORD
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnectionSingleton getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionSingleton.class) {
                if (instance == null) {
                    instance = new DatabaseConnectionSingleton();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
