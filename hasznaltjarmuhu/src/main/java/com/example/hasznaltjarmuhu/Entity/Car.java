package com.example.hasznaltjarmuhu.Entity;

import jakarta.persistence.*;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@NotBlank(message = "Brand is required")
    private String manufacturer;

    //@NotBlank(message = "Model is required")
    private String model;

    //@ValidYear
    private int yearOfMade;

    //@Min(value = 0, message = "Price must be greater than zero!")
    private int price;


    public Car() {}


    public Car(String manufacturer, String model, int yearOfMade, int price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfMade = yearOfMade;
        this.price = price;
    }

    // Getterek
    public int getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfMade() {
        return yearOfMade;
    }

    public int getPrice() {
        return price;
    }

    // Setterek
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYearOfMade(int yearOfMade) {
        this.yearOfMade = yearOfMade;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
