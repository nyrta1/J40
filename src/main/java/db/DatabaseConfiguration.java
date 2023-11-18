package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DatabaseConfiguration {
    private static Connection connection;
    public static void testingTheDatabase() {
        // We are trying to connect the postgres
        try(Connection connection = DriverManager.getConnection( DatabaseConstants.ACCESS_JDBC, DatabaseConstants.USERNAME, DatabaseConstants.PASSWORD)) {
            if (!checkTheDatabaseExists(connection)) {
                System.err.println("[ DatabaseConfiguration ] The database does not exist!");
                System.err.println("[ DatabaseConfiguration ] Attempt to create a database and a table...");

                createDatabase(connection);
                System.out.println("[ DatabaseConfiguration ] Database 'juz40' created successfully.");

                String scriptFilePath = "src/main/resources/script.sql";
                Connection connectionToDB = DriverManager.getConnection(
                        DatabaseConstants.ACCESS_DB,
                        DatabaseConstants.USERNAME,
                        DatabaseConstants.PASSWORD
                );
                executeScript(connectionToDB, scriptFilePath);
                System.out.println("[ DatabaseConfiguration ] Script executed successfully.");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("[ DatabaseConfiguration ] There's nothing wrong with the database");
    }

    private static boolean checkTheDatabaseExists(Connection connection) {
        String query = "SELECT datname FROM pg_database WHERE datname = 'juz40'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createDatabase(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE juz40");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeScript(Connection connection, String scriptFilePath) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

