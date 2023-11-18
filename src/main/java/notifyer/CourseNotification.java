package notifyer;

import models.User;

public interface CourseNotification {
    void registerStudent(SubscribedStudents user);
    void notifyStudents();
}
