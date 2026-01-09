/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental_system;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class RentalSystemTest {

    // ---------- VEHICLE MANAGEMENT ----------

    @Test
    public void addVehicle_vehicleIsAdded() {
        RentalSystem system = new RentalSystem();
        Vehicle car = new Car("C1", "Toyota", "Corolla", 100, 5, "Petrol");
        system.addVehicle(car);
        assertEquals(1, system.getAllVehicles().size());
    }

    @Test
    public void removeVehicle_existingVehicle_returnsTrue() {
        RentalSystem system = new RentalSystem();
        Vehicle car = new Car("C2", "BMW", "X5", 200, 5, "Diesel");
        system.addVehicle(car);
        assertTrue(system.removeVehicle(car));
        assertEquals(0, system.getAllVehicles().size());
    }

    @Test
    public void removeVehicle_notExistingVehicle_returnsFalse() {
        RentalSystem system = new RentalSystem();
        Vehicle car = new Car("C3", "Audi", "A4", 150, 5, "Petrol");
        assertFalse(system.removeVehicle(car));
    }

    // ---------- USER MANAGEMENT ----------

    @Test
    public void addUser_userCountIncreases() {
        RentalSystem system = new RentalSystem();
        User admin = new Admin("A1", "Admin", "admin@test.com", "1234");

        system.addUser(admin);

        assertEquals(1, system.get_user_count());
    }

    @Test
    public void loginUser_existingEmail_returnsUser() {
        RentalSystem system = new RentalSystem();
        User admin = new Admin("A2", "Admin", "admin@test.com", "999");

        system.addUser(admin);

        User loggedIn = system.loginUser("admin@test.com");

        assertNotNull(loggedIn);
        assertEquals("Admin", loggedIn.getName());
    }

    @Test
    public void loginUser_wrongEmail_returnsNull() {
        RentalSystem system = new RentalSystem();
        assertNull(system.loginUser("notfound@test.com"));
    }

    // ---------- VEHICLE SEARCH ----------

    @Test
    public void findVehicleById_existingId_returnsVehicle() {
        RentalSystem system = new RentalSystem();
        Vehicle bike = new Bike("B1", "Giant", "Escape", 50, 21);

        system.addVehicle(bike);

        Vehicle found = system.findVehicleById("B1");

        assertNotNull(found);
        assertEquals("Giant", found.getBrand());
    }

    @Test
    public void findVehicleById_wrongId_returnsNull() {
        RentalSystem system = new RentalSystem();
        assertNull(system.findVehicleById("XXX"));
    }

    // ---------- RENTAL PROCESS ----------

    @Test
    public void processRental_successfulRental_createsBooking() {
        RentalSystem system = new RentalSystem();

        Customer customer = new Customer(
                "CU1", "Ali", "ali@test.com", 1000, "icon.png"
        );

        Vehicle car = new Car("C10", "Honda", "Civic", 100, 5, "Petrol");
        system.addVehicle(car);

        String result = system.processRental(customer, car, 2);

        assertTrue(result.startsWith("Success"));
        assertEquals(1, system.getAllBookings().size());
        assertFalse(car.getAvailability());
    }

    @Test
    public void processRental_insufficientFunds_noBookingCreated() {
        RentalSystem system = new RentalSystem();

        Customer customer = new Customer(
                "CU2", "Omar", "omar@test.com", 50, "icon.png"
        );

        Vehicle car = new Car("C11", "Mazda", "3", 200, 5, "Petrol");
        system.addVehicle(car);

        String result = system.processRental(customer, car, 1);

        assertTrue(result.contains("Insufficient Funds"));
        assertEquals(0, system.getAllBookings().size());
        assertTrue(car.getAvailability());
    }

    @Test
    public void processRental_vehicleAlreadyRented_returnsError() {
        RentalSystem system = new RentalSystem();

        Customer customer = new Customer(
                "CU3", "Sara", "sara@test.com", 1000, "icon.png"
        );

        Vehicle van = new Van("V1", "Ford", "Transit", 150, 1200);
        system.addVehicle(van);

        system.processRental(customer, van, 1);
        String result = system.processRental(customer, van, 1);

        assertTrue(result.contains("already rented"));
    }

    // ---------- AVAILABLE VEHICLES ----------

    @Test
    public void getAvailableVehicles_returnsOnlyAvailable() {
        RentalSystem system = new RentalSystem();

        Vehicle car1 = new Car("C20", "Kia", "Rio", 80, 5, "Petrol");
        Vehicle car2 = new Car("C21", "Tesla", "Model 3", 300, 5, "Electric");

        system.addVehicle(car1);
        system.addVehicle(car2);

        Customer customer = new Customer(
                "CU4", "Mona", "mona@test.com", 1000, "icon.png"
        );

        system.processRental(customer, car1, 1);

        ArrayList<Vehicle> available = system.getAvailableVehicles();

        assertEquals(1, available.size());
        assertEquals("Tesla", available.get(0).getBrand());
    }
}
