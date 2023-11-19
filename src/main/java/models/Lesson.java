package models;

import factories.CourseElement;

public class Lesson implements CourseElement {
    private Long id;
    private Long lessonId;
    private Long userId;
    private Long subjectId;
    private String lessonTitle;

    public Lesson(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    @Override
    public void display() {
        System.out.println("Lesson: " + lessonTitle);
    }
}
