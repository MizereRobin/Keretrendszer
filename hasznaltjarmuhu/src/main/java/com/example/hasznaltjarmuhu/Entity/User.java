package com.example.hasznaltjarmuhu.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private int id;
    private String name;
    private String password;
}
