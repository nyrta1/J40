package DB;

import java.io.IOException;
import java.sql.*;

public class DatabaseConnectionSingleton {
    private static DatabaseConnectionSingleton instance;
    private Connection connection;

    private DatabaseConnectionSingleton()  {
        try {
            //  We are trying to verify in postgres that the database exists
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
