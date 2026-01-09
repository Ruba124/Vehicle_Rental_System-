/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental_system;

import java.util.Date;

public class Payment {
    private static int counter = 1; 
    private String paymentId;
    private double amount;
    private String paymentMethod;
    private boolean isSuccessful;
    private Date date;

    public Payment(double amount, String method) {
        this.paymentId = "PAY-" + counter++;
        this.amount = amount;
        this.paymentMethod = method;
        this.date = new Date();
        this.isSuccessful = false;
    }

    public boolean processPayment() {
        if (amount > 0) {
            isSuccessful = true;
        }
        return isSuccessful;
    }

    public double getAmount() { return amount; }
    
public String getPaymentId() { return paymentId; }
}