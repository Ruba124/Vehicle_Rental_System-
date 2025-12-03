// CHILD CLASS: Van
// Usage: Heavy vehicles. IMPLEMENTS Taxable.
public class Van extends Vehicle implements Taxable {
    
    private double loadCapacity; // in kg

    public Van(String id, String brand, String model, double price, double load) {
        super(id, brand, model, price);
        // Logic: Initialize Van attributes.
    }

    @Override
    public double calculateRentalCost(int days) {
        // Logic: Calculate base cost (price * days).
        // Logic: Add calculateTax().
        // Logic: Maybe add extra fee for heavy load?
        return 0.0; // placeholder
    }

    @Override
    public double calculateTax() {
        // Logic: Return basePricePerDay * 0.15 (15% tax for commercial vans).
        return 0.0; // placeholder
    }
}