package com.tugasbesarpbo;

class ElectricCar extends Car {
    private int batteryLife;
    private int price = 25000000;

    public ElectricCar(String brand, String model, int year, int batteryLife) {
        super(brand, model, year);
        this.batteryLife = batteryLife;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Price\t: " + price + " Rupiah");
        System.out.println("Battery Life\t: " + batteryLife + " kWh");
    }

    public int getPrice() {
        return price;
    }
}

