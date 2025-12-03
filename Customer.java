// FILE: Customer.java
// ROLE: The Renter (Rents Vehicles).
import java.util.ArrayList;

public class Customer extends User {
    
    private double walletBalance; // Virtual money
    private ArrayList<Booking> rentalHistory; // History of rentals

    public Customer(String id, String name, String email, double balance) {
        super(id, name, email);
        this.walletBalance = balance;
        this.rentalHistory = new ArrayList<>(); // Logic: Initialize the empty list
    }

    @Override
    public void displayDashboard() {
        // Logic: Print options: [1] View Cars, [2] Rent Car, [3] Return Car.
    }

    // --- FINANCIAL METHODS (These were missing!) ---
    public void addFunds(double amount) {
        this.walletBalance += amount;
    }

    public boolean deductFunds(double amount) {
        if (this.walletBalance >= amount) {
            this.walletBalance -= amount;
            return true;
        }
        return false;
    }
    // -----------------------------------------------

    public void addBooking(Booking b) {
        rentalHistory.add(b);
    }

    public ArrayList<Booking> getHistory() {
        return rentalHistory; 
    }
}