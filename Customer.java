

import java.util.ArrayList;

/**
 *
 * @author Mohammed Abkam
 */
public class Customer extends User {

    private double walletBalance;
    private ArrayList<Booking> rentalHistory;

    public Customer(String id, String name, String email, double balance) {
        super(id, name, email);
        this.walletBalance = balance;
        this.rentalHistory = new ArrayList<>();
    }

    @Override
    public void displayDashboard() {
        System.out.println("=== CUSTOMER DASHBOARD ===");
        System.out.println("1. View Available Vehicles");
        System.out.println("2. Rent Vehicle");
        System.out.println("3. My History");
        System.out.println("4. Add Funds");
        
        // Note: The logic for these choices happens in Main.java
    }

    // --- FINANCIAL METHODS (Called by RentalSystem) ---
    public void addFunds(double amount) {
        this.walletBalance += amount;
        System.out.println("Funds added. New Balance: $" + this.walletBalance);
    }

    public boolean deductFunds(double amount) {
        // Logic: RentalSystem calls this to ask for money
        if (this.walletBalance >= amount) {
            this.walletBalance -= amount;
            return true; // Success
        }
        return false; // Failed
    }

    // --- HISTORY METHODS (Called by RentalSystem) ---
    public void addBooking(Booking b) {
        // Logic: RentalSystem gives us the receipt to save
        this.rentalHistory.add(b);
    }
    
    public ArrayList<Booking> getHistory() {
        return this.rentalHistory;
    }
    
    public double getBalance() {
        return this.walletBalance; // Used for displaying status
    }
}
