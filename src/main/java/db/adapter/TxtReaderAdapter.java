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
            case "select":
                sql.append("SELECT * FROM ").append(tableName);
                execute.select(sql.toString());
                break;
            case "insert":
                break;
            case "update":
                break;
            case "delete":
                break;
            default:
                System.err.println("[ TxtReaderAdapter ] Invalid method");
        }
    }
}
