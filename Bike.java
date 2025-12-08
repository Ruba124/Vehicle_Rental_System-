class Bike extends Vehicle {

    // Attribute: Specific to Bike
    private final int gearCount; // e.g., 1, 7, 21

    public Bike(String vehicleId, String brand, String model, double basePricePerDay, int gears) {
        
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
      @Override
     public String getDetails() {
        return "Bike-" + super.getDetails()+ " Number of Gears:" +this.gearCount;
    }
}