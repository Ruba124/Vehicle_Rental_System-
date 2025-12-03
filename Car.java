// CHILD CLASS: Car
// Usage: Represents standard cars. IMPLEMENTS Taxable.
public class Car extends Vehicle implements Taxable {
    
    private int numberOfSeats;
    private String fuelType;

    public Car(String id, String brand, String model, double price, int seats, String fuel) {
        super(id, brand, model, price);
        // Logic: Initialize specific Car attributes.
    }

    @Override
    public double calculateRentalCost(int days) {
        // Logic: Calculate base cost (price * days).
        // Logic: Add results from calculateTax().
        // Logic: Return total.
        return 0.0; // placeholder
    }

    @Override
    public double calculateTax() {
        // Logic: Return basePricePerDay * 0.10 (10% tax for cars).
        return 0.0; // placeholder
    }
}
