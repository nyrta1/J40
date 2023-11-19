package models;

import factories.CourseElement;

public class Subject implements CourseElement {
    private Long id;
    private String owner;
    private String subjectName;
    private Long costSubject;

    public Subject(String subjectName, Long costSubject) {
        this.subjectName = subjectName;
        this.costSubject = costSubject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Long getCostSubject() {
        return costSubject;
    }

    @Override
    public void display() {
        System.out.println("Subject: " + subjectName);
    }
}
