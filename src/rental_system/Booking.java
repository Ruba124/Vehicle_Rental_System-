/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental_system;

import java.util.Date;

public class Booking {
    private static int counter = 1;
    private String bookingId;
    private Date bookingDate;
    private User customer;
    private Vehicle vehicle;
    private Payment payment;

    public Booking(User customer, Vehicle vehicle, Payment payment) {
        this.bookingId = "BKG-" + counter++;
        this.bookingDate = new Date();
        this.customer = customer;
        this.vehicle = vehicle;
        this.payment = payment;

        vehicle.setAvailable(false);
    }

    //getters for GUI
  
    
     public String getBookingId() { return bookingId; }
     public java.util.Date getBookingDate() { return bookingDate; }
     public Payment getPayment() { return payment; }

    public Vehicle getVehicle() { return vehicle; }
}