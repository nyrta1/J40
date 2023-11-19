package notifyer;

import models.Users;

public interface CourseNotification {
    void registerStudent(SubscribedStudents user);
    void notifyStudents();
}
