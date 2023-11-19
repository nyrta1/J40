import db.DatabaseConnectionSingleton;
import db.adapter.Converter;
import db.adapter.SqlExecution;
import db.adapter.TxtReaderAdapter;
import factories.CourseElement;
import factories.CourseElementFactory;
import models.Users;
import notifyer.CourseNotificationSystem;
import notifyer.SubscribedStudents;
import privacyguard.DecryptStringProvider;
import privacyguard.EncryptStringProvider;
import privacyguard.PlainStringProvider;
import privacyguard.StringProvider;

import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        DatabaseConnectionSingleton db = DatabaseConnectionSingleton.getInstance();
//        CourseElementFactory factory = new CourseElementFactory();
//
//        CourseElement lesson = factory.createSubject("GoodBye!");
//
//        lesson.display();
//
//        User nurtai = new User("nurtai", "turlubekov");
//
//        CourseNotificationSystem notify = new CourseNotificationSystem();
//
//        new SubscribedStudents(nurtai, notify);
//
//        notify.setNews("Test message!");
//        Converter c = new TxtReaderAdapter(new SqlExecution());
//        String method = scanner.next();
//        c.converterTxtToSql(method, new Object[]{}, "users");
//        String originalValue = "this is a test";
//
//        // Encrypting the string
//        StringProvider encryptedValue = new EncryptStringProvider(new PlainStringProvider(originalValue));
//        System.out.println("Encrypted value: " + encryptedValue.getStringValue());
//
//        // Decrypting the string
//        StringProvider decryptedValue = new DecryptStringProvider(encryptedValue);
//        System.out.println("Decrypted value: " + decryptedValue.getStringValue());
    }
}