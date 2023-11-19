package db.adapter;

public interface Converter {
    void converterTxtToSql(String method, Object[] inputs, String tableNam);
}
