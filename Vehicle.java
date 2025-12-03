// ABSTRACT PARENT: Vehicle
// Usage: The base for all items. Implements Comparable for sorting.
public abstract class Vehicle implements Comparable<Vehicle> {
    
    // Attributes
    protected String vehicleId;
    protected String brand;
    protected String model;
    protected double basePricePerDay;
    protected boolean isAvailable;

    // Constructor
    public Vehicle(String vehicleId, String brand, String model, double basePricePerDay) {
        // Logic: Initialize all attributes.
        // Logic: Set isAvailable = true by default.
    }

    // Abstract Method (Polymorphism)
    // Logic: Each child must define its own final cost (e.g., adding tax or helmet fees).
    public abstract double calculateRentalCost(int days);

    // Concrete Method
    public void setAvailable(boolean status) {
        // Logic: Update the isAvailable boolean.
    }

    public boolean getAvailability() {
        // Logic: Return the current status.
        return false; // placeholder
    }

    public double getPrice() {
        // Logic: Return basePricePerDay.
        return 0.0; // placeholder
    }

    // INTERFACE METHOD: Comparable
    @Override
    public int compareTo(Vehicle other) {
        // Logic: Compare this.basePricePerDay with other.basePricePerDay.
        // Logic: Return -1 if this is cheaper, 1 if more expensive, 0 if equal.
        // Logic: This enables Collections.sort(list) to work.
        return 0; // placeholder
    }
    // Getters for display
    public String getDetails() {
        // Logic: Return string like "Toyota Camry ($50/day)".
        return ""; // placeholder
    }
}