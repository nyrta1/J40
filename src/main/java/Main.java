import db.DatabaseConnectionSingleton;
import factories.CourseElement;
import factories.CourseElementFactory;

public class Main {
    public static void main(String[] args) {
        CourseElementFactory factory = new CourseElementFactory();

        CourseElement lesson = factory.createLesson("GoodBye!");

        lesson.display();
    }
}