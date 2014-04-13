package ru.kalcho.auction.model;

/**
 *
 */
public class User {
    private int id;
    private String name;
    private String email;
    private boolean overbidNotifications;

    public User() {
    }

    public User(int id, String name, String email, boolean overbidNotifications) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.overbidNotifications = overbidNotifications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOverbidNotifications() {
        return overbidNotifications;
    }

    public void setOverbidNotifications(boolean overbidNotifications) {
        this.overbidNotifications = overbidNotifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
