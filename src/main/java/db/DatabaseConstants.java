package db;

public class DatabaseConstants {
    protected final static String ACCESS_JDBC = "jdbc:postgresql://localhost:5432/";
    protected final static String ACCESS_DB = "jdbc:postgresql://localhost:5432/juz40";
    protected final static String USERNAME = "postgres";
    protected final static String PASSWORD = "nurik05";

    public static String tableValues(String tableName) {
        switch (tableName) {
            case "users" -> {
                return " (name, surname, username, password) ";
            }
            case "userrolemapping" -> {
                return " (username, roleId) ";
            }
            case "subject" -> {
                return " (owner, subjectName , costSubject) ";
            }
            case "lesson" -> {
                return " (subjectName, lessonTitle) ";
            }
            default -> {
                return null;
            }
        }
    }
}
