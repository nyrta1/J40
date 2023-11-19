package db.adapter;

import db.DatabaseConnectionSingleton;

import java.sql.*;

public class SqlExecution {
    private Connection connection = DatabaseConnectionSingleton.getInstance().getConnection();

    protected void insert(String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void select(String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Print separator row
            for (int i = 1; i <= columnCount; i++) {
                System.out.print("+-----------------");
            }
            System.out.println("+");

            // Print column names
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                System.out.printf("| %-15.15s ", columnName);
            }
            System.out.println("|");

            // Print separator row
            for (int i = 1; i <= columnCount; i++) {
                System.out.print("+-----------------");
            }
            System.out.println("+");

            // Print rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = resultSet.getString(i);
                    System.out.printf("| %-15.15s ", columnValue);
                }
                System.out.println("|");
            }

            // Print separator row
            for (int i = 1; i <= columnCount; i++) {
                System.out.print("+-----------------");
            }
            System.out.println("+");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void update(String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void delete(String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected boolean exist(String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)){
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("custcount");
                    return count > 0;
                }
            }
            return false; // Return false if no result was found
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object[] getUserRole(String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new String[]{resultSet.getString("roleStatus")};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[]{};
    }
}
