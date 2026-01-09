/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental_system;

public class Bike extends Vehicle {
    private int gearCount;

    public Bike(String vehicleId, String brand, String model, double basePricePerDay, int gears) {
        super(vehicleId, brand, model, basePricePerDay);
        this.gearCount = gears;
    }

    public int getGearCount() { return this.gearCount; }

    @Override
    public double calculateRentalCost(int days) {
        return this.basePricePerDay * days;
    }
    
    @Override
    public String getDetails() {
        return "Bike | " + super.getDetails() + " | Gears: " + this.gearCount;
    }
}
