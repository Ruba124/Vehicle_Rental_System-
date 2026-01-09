package rental_system;

import java.util.ArrayList;

public class Customer extends User {
    private double walletBalance;
    private ArrayList<Booking> rentalHistory;

    // Updated Constructor to accept imagePath
    public Customer(String id, String name, String email, double balance, String imagePath) {
        super(id, name, email, imagePath);
        this.walletBalance = balance;
        this.rentalHistory = new ArrayList<>();
    }

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

    public void addBooking(Booking b) { this.rentalHistory.add(b); }
    public ArrayList<Booking> getHistory() { return this.rentalHistory; }
    public double getBalance() { return this.walletBalance; }
}