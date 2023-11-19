package models;

import java.util.Set;

public class Users {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private Set<Role> roles;

    public Users(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Users(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
}
