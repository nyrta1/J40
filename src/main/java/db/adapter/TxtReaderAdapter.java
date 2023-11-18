package db.adapter;

public class TxtReaderAdapter implements Converter {
    private SqlExecution execute;

    public TxtReaderAdapter(SqlExecution execute) {
        this.execute = execute;
    }

    @Override
    public void converterTxtToSql() {

    }
}
