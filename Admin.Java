// FILE: Admin.java
// ROLE: The Owner (Manages Vehicles).
public class Admin extends User {
    
    private String adminCode;

    public Admin(String id, String name, String email, String code) {
        super(id, name, email);
        // Logic: Store adminCode.
    }

    @Override
    public void displayDashboard() {
        // Logic: Print options: [1] Add Car, [2] Remove Car, [3] View History.
    }

    public void addNewVehicle(RentalSystem system, Vehicle v) {
        // Logic: Call system.addVehicle(v).
        // Logic: Print "Vehicle added successfully".
    }
    public void removeExistingVehicle(RentalSystem system, Vehicle v) {
        // Logic: Call system.removeVehicle(v).
        // Logic: Print "Vehicle removed successfully".
    }
    public void viewAllBookings(RentalSystem system) {
        // Logic: Call system.displayAllBookings().
        // Logic: This prints the master list of who rented what.
    }
    
}