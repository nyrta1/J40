import DB.DatabaseConnectionSingleton;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectionSingleton db = DatabaseConnectionSingleton.getInstance();
    }
}