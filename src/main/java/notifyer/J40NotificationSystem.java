package notifyer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class J40NotificationSystem implements J40Notification {
    private Set<SubscribedStudents> students;
    private String courseNews;

    public J40NotificationSystem() {
        this.students = new HashSet<>();
    }

    public void setNews(String news) {
        this.courseNews = news;
        notifyStudents();
    }

    @Override
    public void registerStudent(SubscribedStudents user) {
        students.add(user);
    }

    @Override
    public void notifyStudents() {
        for (SubscribedStudents student : students) {
            student.update(courseNews);
        }
    }
}
