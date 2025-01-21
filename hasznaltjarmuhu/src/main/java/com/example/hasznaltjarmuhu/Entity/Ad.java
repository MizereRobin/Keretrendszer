package com.example.hasznaltjarmuhu.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "carid")
    private int carId;

    @Column(name = "seller_id")
    private int sellerId;

    private int year;
    private double price;
    private int postTime;
}
