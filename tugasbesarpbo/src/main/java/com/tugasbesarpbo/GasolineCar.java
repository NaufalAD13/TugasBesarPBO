package com.tugasbesarpbo;

class GasolineCar extends Car {
    private int fuelCapacity; 
    private int price = 20000000;

    public GasolineCar(String brand, String model, int year, int fuelCapacity) {
        super(brand, model, year); 
        this.fuelCapacity = fuelCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); 
        System.out.println("Price\t: " + price + " Rupiah");
        System.out.println("Fuel Capacity\t: " + fuelCapacity + " liters");
    }
    public int getPrice() {
        return price;
    }
}