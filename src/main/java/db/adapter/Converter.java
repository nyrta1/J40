package db.adapter;

public interface Converter {
    Object[] converterTxtToSql(String method, Object[] inputs, String tableNam);
}
