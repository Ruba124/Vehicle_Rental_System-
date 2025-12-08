class Van extends Vehicle implements Taxable {
    
    private final double loadCapacity; // in kg

    public Van(String id, String brand, String model, double price, double load) {
        super(id, brand, model, price);
        this.loadCapacity=load;
    }

    @Override
    public double calculateRentalCost(int days) {
        double baseCost = this.basePricePerDay *days;
        double totalCost= baseCost + (calculateTax()*days);
        if (loadCapacity > 1000) {
            totalCost += 25 * days;
        }
        return totalCost;
     
       
    }

    @Override
    public double calculateTax() {
        return this.basePricePerDay * 0.15;
        // Logic: Return basePricePerDay * 0.15 (15% tax for commercial vans).
      
    }
    @Override
     public String getDetails() {
        return "Van-" + super.getDetails()+ " load capacity:" +this.loadCapacity;
    }
    
}
