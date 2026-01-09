/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental_system;

public class Car extends Vehicle implements Taxable {
    
    private int numberOfSeats;
    private String fuelType;

    public Car(String id, String brand, String model, double price, int seats, String fuel) {
        super(id, brand, model, price);
        this.fuelType = fuel;
        this.numberOfSeats = seats;
    }

    @Override
    public double calculateRentalCost(int days) {
        double baseCost = this.basePricePerDay * days;
        return baseCost + (calculateTax() * days);
    }

    @Override
    public double calculateTax() {
        return this.basePricePerDay * 0.1;
    } 
    
    @Override
    public String getDetails() {
        return "Car | " + super.getDetails() + " | Seats: " + numberOfSeats + " | Fuel: " + fuelType;
    }
}