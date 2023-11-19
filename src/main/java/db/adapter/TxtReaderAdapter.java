package db.adapter;

import db.DatabaseConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TxtReaderAdapter implements Converter {
    private final SqlExecution execute;

    public TxtReaderAdapter(SqlExecution execute) {
        this.execute = execute;
    }

    @Override
    public Object[] converterTxtToSql(String method, Object[] inputs, String tableName) {
        StringBuilder sql = new StringBuilder();
        switch (method) {
            case "select" -> {
                if (!tableName.isEmpty()) {
                    sql.append("SELECT * FROM ").append(tableName);
                    execute.select(sql.toString());
                } else {
                    System.err.println("[ TxtReaderAdapter ] TableName is empty");
                }
                return new Object[]{};
            }
            case "insert" -> {
                if (inputs != null && inputs.length > 0) {
                    sql.append("INSERT INTO ").append(tableName).append(DatabaseConstants.tableValues(tableName)).append(" VALUES (");
                    for (int i = 0; i < inputs.length; i++) {
                        if (inputs[i] instanceof String) {
                            sql.append("'").append(inputs[i]).append("'");
                        } else {
                            sql.append(inputs[i]);
                        }
                        if (i < inputs.length - 1) {
                            sql.append(", ");
                        }
                    }
                    sql.append(")");
                    System.err.println(sql.toString());
                    execute.insert(sql.toString());
                } else {
                    System.err.println("[ TxtReaderAdapter ] No values provided for insertion");
                }
                return new Object[]{};
            }
            case "update" -> {
                if (inputs != null && inputs.length > 0) {
                    System.err.println("[ TxtReaderAdapter ] ERROR 500! The update method is temporarily unavailable.");
                } else {
                    System.err.println("[ TxtReaderAdapter ] No values provided for update");
                }
                return new Object[]{};
            }
            case "delete" -> {
                if (inputs != null && inputs.length > 0) {
                    sql.append("DELETE FROM ").append(tableName)
                            .append(" WHERE ").append("id").append(" = ");

                    if (inputs[0] instanceof Integer) {
                        sql.append(inputs[0]);
                    }
                    execute.delete(sql.toString());
                } else {
                    System.err.println("[ TxtReaderAdapter ] No conditions provided for delete");
                }
                return new Object[]{};
            }
            case "existUser" -> {
                String query = "SELECT COUNT(*) AS custcount FROM Users WHERE username = '" +
                        inputs[0] +
                        "' AND password = '" +
                        inputs[1] +
                        "'";
                return new Object[]{execute.exist(query)};
            }
            case "getUserRole" -> {
                String query = "SELECT r.roleStatus " +
                        "FROM users u " +
                        "INNER JOIN userrolemapping urm ON u.username = urm.username " +
                        "INNER JOIN role r ON urm.roleId = r.id " +
                        "WHERE u.username = '" + inputs[0] + "'";
                return new Object[]{execute.getUserRole(query)};
            }
            case "contentOfSubject" -> {
                String query = "SELECT * FROM " + tableName + " WHERE subjectName = '" + inputs[0] + "'";
                execute.select(query);
                return new Object[]{};
            }
            case "bankCardExists" -> {
                String query = "SELECT COUNT(*) AS custcount FROM BankCard WHERE username = '" +
                        inputs[0] +
                        "'";
                return new Object[]{execute.exist(query)};
            }
            default -> System.err.println("[ TxtReaderAdapter ] Invalid method");
        }
        return new Object[]{};
    }
}
