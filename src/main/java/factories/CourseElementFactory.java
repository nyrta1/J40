package factories;

import models.Lesson;
import models.Subject;

public class CourseElementFactory {
    public CourseElement createLesson(String belongsSubject, String title) {
        return new Lesson(belongsSubject, title);
    }

    public CourseElement createSubject(String title, Long price) {
        return new Subject(title, price);
    }
}
