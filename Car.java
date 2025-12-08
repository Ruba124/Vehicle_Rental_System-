class Car extends Vehicle implements Taxable {
    
    private final int numberOfSeats;
    private final String fuelType;

    public Car(String id, String brand, String model, double price, int seats, String fuel) {
        super(id, brand, model, price);
        this.fuelType= fuel;
        this.numberOfSeats=seats;
        
    }

    @Override
    public double calculateRentalCost(int days) {
        
      double baseCost = this.basePricePerDay * days;
      double totalCost = baseCost + (calculateTax()*days);
      return totalCost;
     
    }

    @Override
    public double calculateTax() {
        return this.basePricePerDay*0.1;
      
     } 
    @Override
    public String getDetails() {
        return "Car_" + super.getDetails() + " |Seats: " + numberOfSeats + " |Fuel: " + fuelType;
    }
}
