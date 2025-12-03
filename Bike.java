// CHILD CLASS: Bike
// Usage: Light vehicles.
// Note: DOES NOT implement Taxable.
public class Bike extends Vehicle {

    // Attribute: Specific to Bike
    private int gearCount; // e.g., 1, 7, 21

    public Bike(String vehicleId, String brand, String model, double basePricePerDay, int gears) {
        // Pass common data to parent Vehicle
        super(vehicleId, brand, model, basePricePerDay);
       
        this.gearCount = gears;
    }

   
    public int getGearCount() {
        return this.gearCount;
    }

    // Abstract Method Implementation
    @Override
    public double calculateRentalCost(int days) {
        
        return this.basePricePerDay * days;
    }
}