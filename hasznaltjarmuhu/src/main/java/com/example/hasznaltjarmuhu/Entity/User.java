package com.example.hasznaltjarmuhu.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private boolean auth;

    private String role; // user / manager / admin

    public String GetRole(){
        return role;
    }
    public void SetRole(String value){
        if(value == "user" || value == "manager" || value == "admin"){
        this.role = value;
        }
        else{
            this.role="user";
        }
    }

public boolean isAuth(){
    return auth;
}
public void setAuth(boolean value){
    this.auth = value;
}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public User() {

    }
    public static User AddUser(String username, String password) {
        User newUser = new User(username, password);
        return newUser;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
