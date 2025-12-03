// FILE: Booking.java
// ROLE: The "Receipt" that connects a User to a Vehicle.
import java.util.Date;

public class Booking {
    private String bookingId;
    private Date bookingDate;
    private User customer;
    private Vehicle vehicle;
    private Payment payment; // Links the payment record

    public Booking(User customer, Vehicle vehicle, Payment payment) {
        // Logic: Generate unique ID.
        // Logic: Set current date.
        // Logic: Link the objects (this.customer = customer, etc).
    }

    public void showBookingDetails() {
        // Logic: Print "Booking ID: ..."
        // Logic: Print Customer Name.
        // Logic: Print Vehicle Brand/Model.
        // Logic: Call payment.printReceipt().
    }
    
    // Getter for the vehicle (used when returning the car)
    public Vehicle getVehicle() {
        return vehicle; // placeholder
    }
}
