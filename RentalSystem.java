import java.util.ArrayList;
import java.util.Collections; // Required for sorting

public class RentalSystem {
    
    // DATABASE LISTS
    private ArrayList<Vehicle> fleet;
    private ArrayList<User> users;
  private ArrayList<Booking> bookings; 

    public RentalSystem() {
        // Logic: Initialize the ArrayLists (fleet = new ArrayList... etc).
    }
    // --- NEW: THE SEARCH MACHINE ---
    public User loginUser(String email) {
        // LOGIC: Loop through every user in the database
        for (User u : this.users) {
            // LOGIC: If the email matches...
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u; // FOUND THEM! Return their existing object (with history)
            }
        }
        return null; // NOT FOUND (Return null so Main knows to create a new one)
    }

    public void addUser(User u) {
        this.users.add(u);
    }
    // --- MANAGE VEHICLES ---

    public void addVehicle(Vehicle v) {
        // Logic: Add v to the 'fleet' list.
    }

    public void removeVehicle(Vehicle v) {
        // Logic: Remove v from the 'fleet' list.
    }

    // --- SEARCH & SORT ---

    public void displayAvailableVehicles() {
        // Logic: Step 1 - Create a temp list of only vehicles where isAvailable == true.
        // Logic: Step 2 - Collections.sort(tempList) <-- This uses your Comparable Interface!
        // Logic: Step 3 - Loop and print details of each sorted vehicle.
    }

    // --- THE CORE BUSINESS LOGIC ---

    public void processRental(Customer customer, Vehicle vehicle, int days) {
        // Logic: Step 1 - Check if vehicle.getAvailability() is true.
        // Logic: Step 2 - Calculate total cost = vehicle.calculateRentalCost(days).
        // Logic: Step 3 - Check customer.deductFunds(totalCost).
        // Logic: Step 4 - If payment success:
        //          -> Set vehicle.setAvailable(false).
        //          -> Create a Booking record (optional).
        //          -> Print "Rental Successful".
        // Logic: Step 5 - If payment fails, print "Transaction Denied".
    }

    public void returnVehicle(Vehicle vehicle) {
        // Logic: Find vehicle in list.
        // Logic: Set vehicle.setAvailable(true).
        // Logic: Print "Vehicle Returned".
    }
    public void displayAllBookings() {
    // Logic: Loop through the 'bookings' ArrayList.
    // Logic: Call booking.showBookingDetails() for each one.
}
}