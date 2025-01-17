package com.example.hasznaltjarmuhu.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    private int id;
    private int car_id;
    private int seller_id;
    private int postTime;
}
