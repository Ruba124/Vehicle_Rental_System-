/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rental_system;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RentalSystemTest2 {

    private RentalSystem system;
    private Customer customer;
    private Vehicle car;

    @Before
    public void setUp() {
        system = new RentalSystem();

        customer = new Customer(
                "C1",
                "Ahmed",
                "ahmed@test.com",
                1000.0,
                "user.png"
        );

        car = new Car(
                "V1",
                "Toyota",
                "Corolla",
                100.0,
                5,
                "Petrol"
        );

        system.addUser(customer);
        system.addVehicle(car);
    }

    // -------- VEHICLE MANAGEMENT --------

    @Test
    public void testAddVehicle() {
        assertEquals(1, system.getAllVehicles().size());
    }

    @Test
    public void testRemoveVehicleSuccess() {
        boolean removed = system.removeVehicle(car);
        assertTrue(removed);
        assertEquals(0, system.getAllVehicles().size());
    }

    @Test
    public void testRemoveVehicleFailure() {
        Vehicle fake = new Bike("B1", "Giant", "X", 20, 6);
        assertFalse(system.removeVehicle(fake));
    }

    // -------- USER MANAGEMENT --------

    @Test
    public void testUserCount() {
        assertEquals(1, system.get_user_count());
    }

    @Test
    public void testLoginUserSuccess() {
        User u = system.loginUser("ahmed@test.com");
        assertNotNull(u);
        assertEquals("Ahmed", u.getName());
    }

    @Test
    public void testLoginUserFailure() {
        assertNull(system.loginUser("notfound@test.com"));
    }

    // -------- RENTAL PROCESS --------

    @Test
    public void testSuccessfulRental() {
        String result = system.processRental(customer, car, 3);

        assertTrue(result.startsWith("Success"));
        assertFalse(car.getAvailability());
        assertEquals(1, system.getAllBookings().size());
    }

    @Test
    public void testRentalInsufficientFunds() {
        Customer poorCustomer = new Customer(
                "C2",
                "Ali",
                "ali@test.com",
                50.0,
                "user.png"
        );

        system.addUser(poorCustomer);

        String result = system.processRental(poorCustomer, car, 3);

        assertTrue(result.contains("Insufficient Funds"));
        assertTrue(car.getAvailability());
        assertEquals(0, system.getAllBookings().size());
    }

    @Test
    public void testRentUnavailableVehicle() {
        system.processRental(customer, car, 2);

        String result = system.processRental(customer, car, 1);

        assertEquals("Error: Vehicle is already rented.", result);
    }

    // -------- SEARCH --------

    @Test
    public void testFindVehicleByIdSuccess() {
        Vehicle found = system.findVehicleById("V1");
        assertNotNull(found);
        assertEquals("Toyota", found.getBrand());
    }

    @Test
    public void testFindVehicleByIdFailure() {
        assertNull(system.findVehicleById("INVALID"));
    }

    // -------- AVAILABLE VEHICLES --------

    @Test
    public void testGetAvailableVehicles() {
        assertEquals(1, system.getAvailableVehicles().size());
        system.processRental(customer, car, 1);
        assertEquals(0, system.getAvailableVehicles().size());
    }
}

