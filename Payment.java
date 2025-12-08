// FILE: Payment.java
// ROLE: Handles the money verification.
import java.util.Date;

public class Payment {
    private static int counter = 1;   // Shared across all payments

    private String paymentId;
    private double amount;
    private String paymentMethod;
    private boolean isSuccessful;
    private Date date;

    public Payment(double amount, String method) {
        this.paymentId = "PAY-" + counter;  // e.g., PAY-1, PAY-2, ...
        counter++;                           // increment for next payment

        this.amount = amount;
        this.paymentMethod = method;
        this.date = new Date();
        this.isSuccessful = false;
    }

    public boolean processPayment() {
        System.out.println("Processing payment...");

        if (amount > 0) {
            isSuccessful = true;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    public double getAmount() {
    return amount;
    }

    public void printReceipt() {
        if (isSuccessful) {
            System.out.println("====================================");
            System.out.println("         PAYMENT RECEIPT");
            System.out.println("====================================");
            System.out.println("Payment ID: " + paymentId);
            System.out.println("Amount Paid: $" + amount);
            System.out.println("Method: " + paymentMethod);
            System.out.println("Date: " + date.toString());
            System.out.println("Status: SUCCESS");
            System.out.println("====================================");
        } else {
            System.out.println("Payment failed. No receipt available.");
        }
    }
}