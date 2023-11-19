import db.DatabaseConnectionSingleton;
import db.adapter.Converter;
import db.adapter.SqlExecution;
import db.adapter.TxtReaderAdapter;
import factories.CourseElement;
import factories.CourseElementFactory;
import models.Users;
import notifyer.CourseNotificationSystem;
import notifyer.SubscribedStudents;

public class Main {
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
        Converter c = new TxtReaderAdapter(new SqlExecution());
        c.converterTxtToSql("select", new Object[0], "lesson");
    }
}