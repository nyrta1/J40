package models;

import java.util.Set;

public class User {
    private Long ID;
    private String name;
    private String surname;
    private String username;
    private String password;
    private Set<Role> roles;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }
}
