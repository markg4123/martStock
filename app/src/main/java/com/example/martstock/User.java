package com.example.martstock;

public class User {
    String name, email, username, password, id;
    User(){

    }
    User(String name, String email, String username, String password, String id){
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
