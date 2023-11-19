package db.adapter;

public class TxtReaderAdapter implements Converter {
    private final SqlExecution execute;

    public TxtReaderAdapter(SqlExecution execute) {
        this.execute = execute;
    }

    @Override
    public void converterTxtToSql(String method, Object[] inputs, String tableName) {
        StringBuilder sql = new StringBuilder();
        switch (method) {
            case "select" -> {
                if (!tableName.isEmpty()) {
                    sql.append("SELECT * FROM ").append(tableName);
                    execute.select(sql.toString());
                } else {
                    System.err.println("[ TxtReaderAdapter ] TableName is empty");
                }
            }
            case "insert" -> {
                if (inputs != null && inputs.length > 0) {
                    sql.append("INSERT INTO ").append(tableName).append(" VALUES (");
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
                    execute.insert(sql.toString());
                } else {
                    System.err.println("[ TxtReaderAdapter ] No values provided for insertion");
                }
            }
            case "update" -> {
                if (inputs != null && inputs.length > 0) {
                    System.err.println("[ TxtReaderAdapter ] ERROR 500! The update method is temporarily unavailable.");
                } else {
                    System.err.println("[ TxtReaderAdapter ] No values provided for update");
                }
            }
            case "delete" -> {
                if (inputs != null && inputs.length > 0) {
                    // Assuming inputs[0] is the column name for the condition and inputs[1] is the condition value
                    sql.append("DELETE FROM ").append(tableName)
                            .append(" WHERE ").append("id").append(" = ");

                    if (inputs[0] instanceof Integer) {
                        sql.append(inputs[0]);
                    }

                    execute.delete(sql.toString());
                } else {
                    System.err.println("[ TxtReaderAdapter ] No conditions provided for delete");
                }
            }
            default -> System.err.println("[ TxtReaderAdapter ] Invalid method");
        }
    }
}
