package notifyer;

import models.Users;

public class SubscribedStudents {
    private Users user;

    public SubscribedStudents(Users user, J40Notification notify) {
        this.user = user;
        notify.registerStudent(this);
    }

    public void update(String courseNews) {
        System.out.println("\u001B[38;5;208m" + "[ NOTIFICATION ] " + courseNews + "\u001B[0m");
    }
}
