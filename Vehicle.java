// ABSTRACT PARENT: Vehicle
public abstract class Vehicle implements Comparable<Vehicle> {
    
    // Attributes
    protected String vehicleId;
    protected String brand;
    protected String model;
    protected double basePricePerDay;
    protected boolean isAvailable;

    // Constructor
    public Vehicle(String vehicleId, String brand, String model, double basePricePerDay) {
        this.vehicleId=vehicleId;
        this.brand=brand;
        this.model=model;
        this.basePricePerDay=basePricePerDay;
        this.isAvailable=true;
      
    }
     public String getVehicleId() {
        return this.vehicleId;
    }

    public abstract double calculateRentalCost(int days);

    // Concrete Method
    public void setAvailable(boolean status) {
        // Logic: Update the isAvailable boolean.
        this.isAvailable=status;
    }

    public boolean getAvailability() {
         return this.isAvailable;
    }

    public double getPrice() {
       return this.basePricePerDay;
    }

    // INTERFACE METHOD: Comparable
    @Override
    public int compareTo(Vehicle other) {
        if (this.basePricePerDay>other.basePricePerDay){
        return 1;
        }
        else if(this.basePricePerDay<other.basePricePerDay){
        return -1;
    } else{
        return 0;
        }
     
    }
    // Getters for display
    public String getDetails() {
        return this.vehicleId + "---" + this.brand+" " +this.model+"( $" +this.basePricePerDay +"/day)";
    }

}