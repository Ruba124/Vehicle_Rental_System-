/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental_system;

public class Van extends Vehicle implements Taxable {
    private double loadCapacity; 

    public Van(String id, String brand, String model, double price, double load) {
        super(id, brand, model, price);
        this.loadCapacity = load;
    }

    @Override
    public double calculateRentalCost(int days) {
        double baseCost = this.basePricePerDay * days;
        double totalCost = baseCost + (calculateTax() * days);
        if (loadCapacity > 1000) {
            totalCost += 25 * days;
        }
        return totalCost;
    }

    @Override
    public double calculateTax() {
        return this.basePricePerDay * 0.15;
    }
    
    @Override
    public String getDetails() {
        return "Van | " + super.getDetails() + " | Load: " + this.loadCapacity + "kg";
    }
}