package factories;

import models.Lesson;
import models.Subject;

public class CourseElementFactory {
    public CourseElement createLesson(String title) {
        return new Lesson(title);
    }

    public CourseElement createSubject(String title) {
        return new Subject(title);
    }
}
