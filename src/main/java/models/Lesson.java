package models;

import factories.CourseElement;

public class Lesson implements CourseElement {
    private Long id;
    private String subjectName;
    private String lessonTitle;

    public Lesson(String subjectName, String lessonTitle) {
        this.subjectName = subjectName;
        this.lessonTitle = lessonTitle;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public void display() {
        System.out.println("Lesson: " + lessonTitle);
    }
}
