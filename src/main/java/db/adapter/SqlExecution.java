package db.adapter;

import db.DatabaseConnectionSingleton;

import java.sql.Connection;

public class SqlExecution {
    private Connection connection = DatabaseConnectionSingleton.getInstance().getConnection();


}
