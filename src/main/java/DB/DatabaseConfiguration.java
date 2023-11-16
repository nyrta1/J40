package DB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DatabaseConfiguration {
    private static Connection connection;
    public static void testingTheDatabase() throws SQLException, IOException {
        // We are trying to connect the postgres
        connection = DriverManager.getConnection(
                DatabaseConstants.ACCESS_JDBC,
                DatabaseConstants.USERNAME,
                DatabaseConstants.PASSWORD
        );

        if (!checkTheDatabaseExists()) {
            System.err.println("[ DatabaseConfiguration ] The database does not exist!");
            System.err.println("[ DatabaseConfiguration ] Attempt to create a database and a table...");

            createDatabase();
            System.out.println("[ DatabaseConfiguration ] Database 'juz40' created successfully.");

            String scriptFilePath = "src/main/resources/script.sql";
            connection = DriverManager.getConnection(
                    DatabaseConstants.ACCESS_DB,
                    DatabaseConstants.USERNAME,
                    DatabaseConstants.PASSWORD
            );
            executeScript(scriptFilePath);
            System.out.println("[ DatabaseConfiguration ] Script executed successfully.");
        }
        System.out.println("[ DatabaseConfiguration ] There's nothing wrong with the database");
        connection.close();
    }

    private static boolean checkTheDatabaseExists() throws SQLException {
        String query = "SELECT datname FROM pg_database WHERE datname = 'juz40'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            return resultSet.next();
        }
    }

    private static void createDatabase() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE juz40");
        }
    }

    private static void executeScript(String scriptFilePath) throws IOException, SQLException {
        StringBuilder sqlStatement = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sqlStatement.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlStatement.toString());
        }
    }
}

