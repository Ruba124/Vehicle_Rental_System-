import java.util.Date;

public class Booking {
    private static int counter = 1;  // For incremental booking IDs

    private String bookingId;
    private Date bookingDate;
    private User customer;
    private Vehicle vehicle;
    private Payment payment;

    public Booking(User customer, Vehicle vehicle, Payment payment) {
        this.bookingId = "BKG-" + counter;
        counter++;

        this.bookingDate = new Date();
        this.customer = customer;
        this.vehicle = vehicle;
        this.payment = payment;

        // Mark vehicle as unavailable when booked
        vehicle.setAvailable(false);
    }

    public void showBookingDetails() {
        System.out.println("====================================");
        System.out.println("          BOOKING DETAILS");
        System.out.println("====================================");

        System.out.println("Booking ID: " + bookingId);
        System.out.println("Date: " + bookingDate);

        // Customer info (User class must have getName())
        System.out.println("Customer: " + customer.getName());

        // Vehicle info using getDetails()
        System.out.println("Vehicle: " + vehicle.getDetails());

        // Payment amount
        System.out.println("Price: $" + payment.getAmount());

        System.out.println("\n--- Payment Info ---");
        payment.printReceipt();

        System.out.println("====================================\n");
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    
}
