/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental_system;

import java.util.ArrayList;
import java.util.Collections;

public class RentalSystem {
    
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
    
    public boolean removeVehicle(Vehicle v) {
        if (this.fleet.contains(v)) {
            this.fleet.remove(v);
            return true;
        }
        return false;
    }
    
    // --- RENTAL LOGIC (Returns String for GUI Alert) ---
    public String processRental(Customer customer, Vehicle vehicle, int days) {
        
        if (!vehicle.getAvailability()) {
            return "Error: Vehicle is already rented.";
        }

        double totalCost = vehicle.calculateRentalCost(days);

        if (customer.deductFunds(totalCost)) {
            Payment payment = new Payment(totalCost, "Wallet");
            payment.processPayment(); 

            Booking newBooking = new Booking(customer, vehicle, payment);
            
            this.bookings.add(newBooking);       
            customer.addBooking(newBooking);     
            
            return "Success! Rented " + vehicle.getModel() + " for $" + totalCost;
        } else {
            return "Error: Insufficient Funds. Cost: $" + totalCost;
        }
    }
    
    // --- HELPERS FOR GUI ---
    public ArrayList<Vehicle> getAvailableVehicles() {
        ArrayList<Vehicle> available = new ArrayList<>();
        for (Vehicle v : this.fleet) {
            if (v.getAvailability()) {
                available.add(v);
            }
        }
        Collections.sort(available);
        return available;
    }
    
    public ArrayList<Booking> getAllBookings() { return this.bookings; }
    
    public void addUser(User u) { this.users.add(u); }
    
    public User loginUser(String email) {
        for (User u : this.users) {
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        }
        return null;
    }
    
    public Vehicle findVehicleById(String id) {
        for (Vehicle v : this.fleet) {
            if (v.getVehicleId().equalsIgnoreCase(id)) return v;
        }
        return null; 
    }
    
     public ArrayList<Vehicle> getAllVehicles() {
          Collections.sort(this.fleet);
          return (this.fleet);
   
}
     public int get_user_count(){
         
         return ((this.users).size());
     }
}