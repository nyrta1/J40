package notifyer;

import models.User;

public class SubscribedStudents {
    private User user;

    public SubscribedStudents(User user, CourseNotification notify) {
        this.user = user;
        notify.registerStudent(this);
    }

    public void update(String courseNews) {
        System.out.println(user.getName() + ", you have new lesson on " + courseNews);
    }
}
