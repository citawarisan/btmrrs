package com.model;

public class User extends Generic {
    private String username, password, name, phone, email;
    private int type;

    public User() {
        super("User", new String[]{}); // not sure whether to do column check or not
    }

    public User(String username, String password, String name, String phone, String email, int type) {
        this();
        setUsername(username);
        setPassword(password);
        setName(name);
        setPhone(phone);
        setEmail(email);
        setType(type);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
