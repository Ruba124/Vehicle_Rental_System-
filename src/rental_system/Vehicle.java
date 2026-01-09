/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental_system;

public abstract class Vehicle implements Comparable<Vehicle> {
    
    protected String vehicleId;
    protected String brand;
    protected String model;
    protected double basePricePerDay;
    protected boolean isAvailable;

    public Vehicle(String vehicleId, String brand, String model, double basePricePerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }
    
    public String getVehicleId() { return this.vehicleId; }
    public String getBrand() { return this.brand; }
    public String getModel() { return this.model; }
    public double getPrice() { return this.basePricePerDay; }

    public abstract double calculateRentalCost(int days);

    public void setAvailable(boolean status) { this.isAvailable = status; }
    public boolean getAvailability() { return this.isAvailable; }

    @Override
    public int compareTo(Vehicle other) {
        if (this.basePricePerDay > other.basePricePerDay) return 1;
        else if(this.basePricePerDay < other.basePricePerDay) return -1;
        else return 0;
    }
    
    public String getDetails() {
        return this.vehicleId + " - " + this.brand + " " + this.model + " ($" + this.basePricePerDay + "/day)";
    }
}