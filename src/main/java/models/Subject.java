package models;

import factories.CourseElement;

public class Subject implements CourseElement {
    private Long id;
    private Long subjectId;
    private String subjectName;
    private Long costSubject;

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public void display() {
        System.out.println("Subject: " + subjectName);
    }
}
