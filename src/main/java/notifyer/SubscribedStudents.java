package notifyer;

import models.Users;

public class SubscribedStudents {
    private Users user;

    public SubscribedStudents(Users user, CourseNotification notify) {
        this.user = user;
        notify.registerStudent(this);
    }

    public void update(String courseNews) {
        System.out.println(user.getName() + ", you have new lesson on " + courseNews);
    }
}
