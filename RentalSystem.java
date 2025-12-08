 // MAKE SURE THIS MATCHES YOUR PACKAGE

import java.util.ArrayList;
import java.util.Collections;

public class RentalSystem {
    
    // DATABASE LISTS
    private ArrayList<Vehicle> fleet;
    private ArrayList<User> users;
    private ArrayList<Booking> bookings; 
    
    public RentalSystem() {
        this.fleet = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }
    
    // --- MANAGE VEHICLES ---
    public void addVehicle(Vehicle v) {
        this.fleet.add(v);
    }
    
    // [FIX] ADDED THIS MISSING METHOD FOR ADMIN
    public void removeVehicle(Vehicle v) {
        if (this.fleet.contains(v)) {
            this.fleet.remove(v);
            System.out.println("Vehicle removed from fleet.");
        } else {
            System.out.println("Error: Vehicle not found.");
        }
    }
    
    // --- THE RENTAL LOGIC ---
    public void processRental(Customer customer, Vehicle vehicle, int days) {
        
        // 1. Check Availability
        if (!vehicle.getAvailability()) {
            System.out.println("Transaction Denied: Vehicle is already rented.");
            return;
        }

        // 2. Calculate Costs
        double totalCost = vehicle.calculateRentalCost(days);
        System.out.println("Calculated Cost: $" + totalCost);

        // 3. Process Payment
        if (customer.deductFunds(totalCost)) {
            
            // Payment Success
            Payment payment = new Payment(totalCost, "Wallet");
            payment.processPayment(); 

            // 4. Create Booking
            // [CHECK] This constructor must match Booking.java exactly
            Booking newBooking = new Booking(customer, vehicle, payment);
            
            // 5. Update Records
            this.bookings.add(newBooking);       
            customer.addBooking(newBooking);     
            
            System.out.println("Success! Vehicle rented to " + customer.getName());
            
        } else {
            System.out.println("Transaction Failed: Insufficient funds.");
        }
    }
    
    // --- SEARCH & SORT ---
    public void displayAvailableVehicles() {
        ArrayList<Vehicle> available = new ArrayList<>();
        for (Vehicle v : this.fleet) {
            if (v.getAvailability()) {
                available.add(v);
            }
        }
        Collections.sort(available);
        
        if (available.isEmpty()) System.out.println("No vehicles available.");
        System.out.println("-----sorted by price per day--------");
        for (Vehicle v : available) {
            System.out.println(v.getDetails());
        }
    }
    
    // --- USER MANAGEMENT ---
    public void addUser(User u) { this.users.add(u); }
    
    public User loginUser(String email) {
        for (User u : this.users) {
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        }
        return null;
    }

    public void displayAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings in system.");
        } else {
            for (Booking b : bookings) {
                b.showBookingDetails();
            }
        }
    }
    
    public Vehicle findVehicleById(String id) {
        // Logic: Loop through every vehicle in the fleet
        for (Vehicle v : this.fleet) {
            
            // Logic: Compare the IDs (IgnoreCase ensures "v-101" works same as "V-101")
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                return v; // Found it! Return the object.
            }
        }
        
        // Logic: If loop finishes and nothing is found
        return null; 
    }
}