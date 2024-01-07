package com.tugasbesarpbo;

class HybridCar extends Car {
    private int batteryLife; 
    private int fuelCapacity; 
    private int price = 30000000;

    public HybridCar(String brand, String model, int year, int batteryLife, int fuelCapacity) {
        super(brand, model, year); 
        this.batteryLife = batteryLife;
        this.fuelCapacity = fuelCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); 
        System.out.println("Price\t: " + price + " Rupiah");
        System.out.println("Battery Life\t: " + batteryLife + " kWh");
        System.out.println("Fuel Capacity\t: " + fuelCapacity + " liters");
    }
    public int getPrice() {
        return price;
    }
}