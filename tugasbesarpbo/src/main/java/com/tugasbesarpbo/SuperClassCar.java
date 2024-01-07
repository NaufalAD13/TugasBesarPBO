package com.tugasbesarpbo;

// Super class (induk)
class Car {
    protected String brand;
    protected String model;
    protected int year;
    protected double price;

    // Constructor
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Method untuk menampilkan informasi mobil
    public void displayInfo() {
        System.out.println("Brand\t: " + brand);
        System.out.println("Model\t: " + model);
        System.out.println("Year\t: " + year);
    }
}